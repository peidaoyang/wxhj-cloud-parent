/**
 * @fileName: SummaryAttendanceHandle.java
 * @author: pjf
 * @date: 2019年12月26日 下午3:19:22
 */

package com.wxhj.cloud.business.runnable;

import com.wxhj.cloud.business.controller.attendance.AttendanceDayController;
import com.wxhj.cloud.business.domain.AttendanceSummaryDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.dto.response.AttendanceSummaryDTO;
import com.wxhj.cloud.business.service.AttendanceSummaryService;
import com.wxhj.cloud.business.service.CurrentAccountAuthorityService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.DayWorkTypeEnum;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.core.utils.MathUtil;
import com.wxhj.cloud.feignClient.business.dto.CurrentAttendanceDayRecDTO;
import com.wxhj.cloud.feignClient.business.dto.DurationDTO;
import com.wxhj.cloud.feignClient.business.dto.GetAttendanceDaysDTO;
import com.wxhj.cloud.feignClient.business.vo.GetAttendanceDaysVO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pjf
 * @className SummaryAttendanceHandle.java
 * @date 2019年12月26日 下午3:19:22
 */

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SummaryAttendanceRunnable implements Runnable {

    @Resource
    CurrentAccountAuthorityService currentAccountAuthorityService;
    @Resource
    AttendanceDayController attendanceDayController;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    AttendanceSummaryService attendanceSummaryService;

    private int[] minuteArr = new int[OtherStaticClass.TWO_DAY__MINUTE];

    public SummaryAttendanceRunnable() {
        initMinuteArr();
    }

    @Override
    public void run() {
        // 获取前一天的考勤规则
        Date beforeDate = DateUtil.growDateIgnoreHMS(new Date(), -1);
        run(beforeDate);
    }


    public void run(Date beforeDate) {
        // 获取当前用户权限组的全部数据
        List<CurrentAccountAuthorityDO> currentAccountAuthorities = currentAccountAuthorityService.listAll();
        // 获取前一天的考勤规则
//        Date beforeDate = DateUtil.growDateIgnoreHMS(new Date(), -1);
        GetAttendanceDaysDTO getAttendanceDaysDTO = new GetAttendanceDaysDTO();
        getAttendanceDaysDTO.setBeginTime(beforeDate);
        getAttendanceDaysDTO.setEndTime(beforeDate);

        List<AttendanceSummaryDO> summaryDOList = currentAccountAuthorities.stream().map(item -> {
            getAttendanceDaysDTO.setAccountId(item.getAccountId());
            List<GetAttendanceDaysVO> getAttendanceDaysVOs = attendanceDayController.getGetAttendanceDaysVOS(getAttendanceDaysDTO, item);
            // 插入到attendanceSummary表中
            return revert2AttendanceSummary(getAttendanceDaysVOs.get(0));
        }).collect(Collectors.toList());

        try {
            summaryDOList = (List<AttendanceSummaryDO>) accessedRemotelyService.accessedAccountOrganizeList(summaryDOList);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            wuXiHuaJieFeignError.printStackTrace();
            return;
        }

        // 过滤错误数据
        summaryDOList = summaryDOList.stream().filter(q -> q.getOrganizeId() != null).collect(Collectors.toList());
        // 写入之前先删除当天数据
        summaryDOList.forEach(item -> attendanceSummaryService.delete(item.getDatetime()));
        // 写入数据库
//        attendanceSummaryService.insertList(summaryDOList);
        summaryDOList.forEach(q -> {
            try {
                attendanceSummaryService.insert(q);
            } catch (Exception e) {
                return;
            }
        });
        return;
    }

    /**
     * 初始化数组
     *
     * @return void
     * @author daxiong
     * @date 2020/4/17 9:29 上午
     */
    public void initMinuteArr() {
        int status = DayWorkTypeEnum.NO_ATTENDANCE.getCode();
        for (int i = 0; i < OtherStaticClass.TWO_DAY__MINUTE; i++) {
            minuteArr[i] = status;
        }
    }


    /**
     * 将getAttendanceDaysVO实体转换成AttendanceSummaryDO实体
     *
     * @param getAttendanceDaysVO
     * @return com.wxhj.cloud.business.domain.AttendanceSummaryDO
     * @author daxiong
     * @date 2020/4/15 1:20 下午
     */
    public AttendanceSummaryDO revert2AttendanceSummary(GetAttendanceDaysVO getAttendanceDaysVO) {
        AttendanceSummaryDO attendanceSummaryDO = new AttendanceSummaryDO();
        attendanceSummaryDO.setDatetime(getAttendanceDaysVO.getDayInfo());
        attendanceSummaryDO.setAuthorityGroupId(getAttendanceDaysVO.getGroupId());
        attendanceSummaryDO.setAttendanceType(getAttendanceDaysVO.getType());
        attendanceSummaryDO.setAccountId(getAttendanceDaysVO.getAccountId());
        attendanceSummaryDO.setAccountName(getAttendanceDaysVO.getAccountName());
        attendanceSummaryDO.setAuthorityGroupName(getAttendanceDaysVO.getGroupName());

        // 如果是休息，则跳过
        if (attendanceSummaryDO.getAttendanceType() != DayWorkTypeEnum.OFF_WORK.getCode()) {
            List<DurationDTO> durationList = getAttendanceDaysVO.getDurationList();
            // 考勤详细规则
            Map<Integer, CurrentAttendanceDayRecDTO> currentAttendanceDayRecMap = getAttendanceDaysVO.getCurrentAttendanceDayRecMap();
            boolean needInit = false;
            for (int i = 1; i <= 3; i++) {
                CurrentAttendanceDayRecDTO currentAttendanceDayRec = currentAttendanceDayRecMap.get(i);
                if (currentAttendanceDayRec != null) {
                    AttendanceSummaryDTO upAndDownTimeDTO = getUpAndDownTime(durationList, currentAttendanceDayRec);
                    if (i == 1) {
                        attendanceSummaryDO.setSequence1(i);
                        attendanceSummaryDO.setUpTime1(upAndDownTimeDTO.getUpTime());
                        attendanceSummaryDO.setDownTime1(upAndDownTimeDTO.getDownTime());
                        attendanceSummaryDO.setLeaveTime1(upAndDownTimeDTO.getLeaveTime());
                        attendanceSummaryDO.setTravelTime1(upAndDownTimeDTO.getTravelTime());
                        attendanceSummaryDO.setWorkTime1(upAndDownTimeDTO.getWorkTime());
                        attendanceSummaryDO.setTimeState1(upAndDownTimeDTO.getTimeState());
                    } else if (i == 2) {
                        attendanceSummaryDO.setSequence2(i);
                        attendanceSummaryDO.setUpTime2(upAndDownTimeDTO.getUpTime());
                        attendanceSummaryDO.setDownTime2(upAndDownTimeDTO.getDownTime());
                        attendanceSummaryDO.setLeaveTime2(upAndDownTimeDTO.getLeaveTime());
                        attendanceSummaryDO.setTravelTime2(upAndDownTimeDTO.getTravelTime());
                        attendanceSummaryDO.setWorkTime2(upAndDownTimeDTO.getWorkTime());
                        attendanceSummaryDO.setTimeState2(upAndDownTimeDTO.getTimeState());
                    } else if (i == 3) {
                        attendanceSummaryDO.setSequence3(i);
                        attendanceSummaryDO.setUpTime3(upAndDownTimeDTO.getUpTime());
                        attendanceSummaryDO.setDownTime3(upAndDownTimeDTO.getDownTime());
                        attendanceSummaryDO.setLeaveTime3(upAndDownTimeDTO.getLeaveTime());
                        attendanceSummaryDO.setTravelTime3(upAndDownTimeDTO.getTravelTime());
                        attendanceSummaryDO.setWorkTime3(upAndDownTimeDTO.getWorkTime());
                        attendanceSummaryDO.setTimeState3(upAndDownTimeDTO.getTimeState());
                    }
                    needInit |= upAndDownTimeDTO.getNeedInit();
                }
            }
            Integer leaveTotal = MathUtil.add(attendanceSummaryDO.getLeaveTime1(), attendanceSummaryDO.getLeaveTime2(), attendanceSummaryDO.getLeaveTime3());
            attendanceSummaryDO.setLeaveTimeTotal(leaveTotal);
            Integer travelTotal = MathUtil.add(attendanceSummaryDO.getTravelTime1(), attendanceSummaryDO.getTravelTime2(), attendanceSummaryDO.getTravelTime3());
            attendanceSummaryDO.setTravelTimeTotal(travelTotal);
            Integer workTotal = MathUtil.add(attendanceSummaryDO.getWorkTime1(), attendanceSummaryDO.getWorkTime2(), attendanceSummaryDO.getWorkTime3());
            attendanceSummaryDO.setWorkTimeTotal(workTotal);

            // 重新初始化数组
            if (needInit) {
                initMinuteArr();
            }
        }
        return attendanceSummaryDO;
    }

    public AttendanceSummaryDTO getUpAndDownTime(List<DurationDTO> durationList, CurrentAttendanceDayRecDTO currentAttendanceDayRec) {
        Integer upTime = currentAttendanceDayRec.getUpTime();
        Integer downTime = currentAttendanceDayRec.getDownTime();
        Integer beginIndex = upTime, endIndex = downTime;
        Integer workStatus = DayWorkTypeEnum.ON_WORK.getCode();
        Integer leaveStatus = DayWorkTypeEnum.ASK_FOR_LEAVE.getCode();
        Integer travelStatus = DayWorkTypeEnum.ON_BUSINESS.getCode();
        Integer realStatus = workStatus;

        Integer workTime = downTime - upTime;
        Integer leaveTime = 0;
        Integer travelTime = 0;
        boolean needFor = false;
        boolean needInit = false;
        if (durationList != null) {
            for (int i = upTime; i <= downTime; i++) {
                minuteArr[i] = workStatus;
            }
            needInit = true;
            // durationList按创建时间升序排序
            Collections.sort(durationList, (o1, o2) -> o1.getCreateTime().compareTo(o2.getCreateTime()));
            for (DurationDTO duration : durationList) {
                Integer dUpTime = duration.getBeginTime();
                Integer dDownTime = duration.getEndTime();
                Integer type = duration.getType();
                if (dUpTime >= downTime || dDownTime <= upTime) {
                    continue;
                }
                needFor = true;
                Integer latestTimeMinute = Math.min(downTime, dDownTime);
                Integer earliestTimeMinute = Math.max(upTime, dUpTime);
                for (int i = earliestTimeMinute; i <= latestTimeMinute; i++) {
                    minuteArr[i] = type;
                    realStatus = type;
                }
            }
        }
        if (needFor) {
            workTime = 0;
            endIndex = -1;
            beginIndex = -1;
            boolean beginFlag = true;
            // 统计时间
            for (int i = upTime; i <= downTime; i++) {
                if (workStatus.equals(minuteArr[i])) {
                    endIndex = i;
                    if (beginFlag) {
                        beginIndex = i;
                        beginFlag = false;
                    }
                    workTime++;
                } else if (leaveStatus.equals(minuteArr[i])) {
                    leaveTime++;
                } else if (travelStatus.equals(minuteArr[i])) {
                    travelTime++;
                }
            }
        }
        AttendanceSummaryDTO attendanceSummaryDTO = AttendanceSummaryDTO.builder()
                .upTime(beginIndex).downTime(endIndex).leaveTime(leaveTime)
                .travelTime(travelTime).workTime(workTime).timeState(realStatus).needInit(needInit).build();
        return attendanceSummaryDTO;
    }


}
