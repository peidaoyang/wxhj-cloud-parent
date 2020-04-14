package com.wxhj.cloud.business.attenance.helper;

import com.wxhj.cloud.business.attenance.filter.AbstractAttendanceDayFilter;
import com.wxhj.cloud.business.domain.CurrentAttendanceDayDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceDayRecDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;
import com.wxhj.cloud.core.enums.DayWorkTypeEnum;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.feignClient.dto.AttendanceDoFilterDTO;
import com.wxhj.cloud.feignClient.dto.CurrentAttendanceDayRecDTO;
import com.wxhj.cloud.feignClient.dto.DurationDTO;
import com.wxhj.cloud.feignClient.business.vo.GetAttendanceDaysVO;
import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author daxiong
 * @date 2020-04-10 08:40
 */
@Data
@Component
public class AttendanceDayFilterHelper {

    @Resource
    DozerBeanMapper dozerBeanMapper;

    private TreeMap<Integer, List<AbstractAttendanceDayFilter>> map = new TreeMap<>();
    private ThreadLocal<Map<String, GetAttendanceDaysVO>> bucket = new ThreadLocal<>();

    private CurrentAttendanceGroupDO currentAttendanceGroup;
    private Map<Integer, CurrentAttendanceGroupRecDO> currentAttendanceGroupRecMap;
    private Map<String, CurrentAttendanceDayDO> currentAttendanceDayMap;
    private Map<String, List<CurrentAttendanceDayRecDO>> currentAttendanceDayRecMap;
    private Date beginTime;
    private Date endTime;
    private String accountId;

    public List<GetAttendanceDaysVO> initAndFilter() {
        bucket.set(new LinkedHashMap<>(SystemStaticClass.INIT_CAPACITY));

        Integer groupType = currentAttendanceGroup.getGroupType();
        int termDays = DateUtil.getTermDays(beginTime, endTime);
        // 构造返回VO
        List<GetAttendanceDaysVO> attendanceDaysList = new ArrayList<>(termDays);
        Date date = beginTime;
        for (int i = 0; i <= termDays; i++) {
            GetAttendanceDaysVO getAttendanceDaysVO = new GetAttendanceDaysVO();

            if (i == 0) {
                getAttendanceDaysVO.setDayInfo(beginTime);
                // 将数据放入桶中
                putByDate(beginTime, getAttendanceDaysVO);
            } else {
                // 日期加1天
                Date newDate = DateUtil.growDate(date, 1);
                getAttendanceDaysVO.setDayInfo(newDate);
                date = newDate;
                putByDate(newDate, getAttendanceDaysVO);
            }
            // 判断是否工作
            int serialNumber = groupType == 0 ? DateUtil.getDateNumber(date, Calendar.DAY_OF_WEEK) - 1
                    : DateUtil.getDateNumber(beginTime, Calendar.DAY_OF_MONTH);
            if (groupType == 0 && serialNumber == 0) {
                // 日期减一会产生0，与数据库中不对应，手动改为7
                serialNumber = 7;
            }
            String attendanceDayId = currentAttendanceGroupRecMap.get(serialNumber).getAttendanceDayId();
            getAttendanceDaysVO.setType(currentAttendanceDayMap.get(attendanceDayId).getAttendanceType());
            getAttendanceDaysVO.setTypeName(DayWorkTypeEnum.getByCode(getAttendanceDaysVO.getType()).getDesc());

            // 设置考勤时间规则
            List<CurrentAttendanceDayRecDO> currentAttendanceDayRecs = currentAttendanceDayRecMap.get(attendanceDayId);
            Map<Integer, CurrentAttendanceDayRecDTO> cAttendanceDayRecMap = new TreeMap<>();
            currentAttendanceDayRecs.forEach(item -> cAttendanceDayRecMap.put(item.getSequence(), dozerBeanMapper.map(item, CurrentAttendanceDayRecDTO.class)));
            getAttendanceDaysVO.setCurrentAttendanceDayRecMap(cAttendanceDayRecMap);
            // 设置考勤最早开始和最晚结束时间
            Integer earliestTime = OtherStaticClass.DAY_LATEST_MINUTE, latestTime = 0;
            for (CurrentAttendanceDayRecDO currentAttendanceDayRec : currentAttendanceDayRecs) {
                earliestTime = currentAttendanceDayRec.getUpTime() < earliestTime ? currentAttendanceDayRec.getUpTime() : earliestTime;
                latestTime = currentAttendanceDayRec.getDownTime() > latestTime ? currentAttendanceDayRec.getDownTime() : latestTime;
            }
            getAttendanceDaysVO.setEarliestTime(earliestTime);
            getAttendanceDaysVO.setLatestTime(latestTime);
            getAttendanceDaysVO.setAccountId(accountId);
            getAttendanceDaysVO.setGroupId(currentAttendanceGroup.getId());
            getAttendanceDaysVO.setGroupName(currentAttendanceGroup.getFullName());

            attendanceDaysList.add(getAttendanceDaysVO);
        }

        // 增加请假和出差状态，根据账户id、请假状态和时间限制
        return filter();
    }

    public List<GetAttendanceDaysVO> filter() {
        map.forEach((k, v) -> v.forEach(item -> item.doFilter(accountId, beginTime, endTime)));
        return new ArrayList<>(bucket.get().values());
    }

    /**
     * 判断传入的时间与bucket中的数据是否有交集，如果有，返回交集；如果没有，返回null
     * 注意：传入的两个时间是同一天
     *
     * @param beginTime
     * @param endTime
     * @return void
     * @author daxiong
     * @date 2020-04-11 16:41
     */
    public void setTimeIntersection(AttendanceDoFilterDTO attendanceDoFilterDTO, Date beginTime, Date endTime, DayWorkTypeEnum dayWorkTypeEnum) {
        Integer beginTimeMinute = DateUtil.date2MinuteTotal(beginTime);
        Integer endTimeMinute = DateUtil.date2MinuteTotal(endTime);
        setTimeIntersection(attendanceDoFilterDTO, beginTime, beginTimeMinute, endTimeMinute, dayWorkTypeEnum);
    }

    public void setTimeIntersection(AttendanceDoFilterDTO attendanceDoFilterDTO, Date dateKey, Integer beginTimeMinute, Integer endTimeMinute, DayWorkTypeEnum dayWorkTypeEnum) {
        GetAttendanceDaysVO getAttendanceDaysVO = getByDate(dateKey);
        List<DurationDTO> durationDTOList = getAttendanceDaysVO.getDurationList() == null ?
                new ArrayList<>(SystemStaticClass.INIT_CAPACITY) : getAttendanceDaysVO.getDurationList();
        // 获取当前的考勤规则
        Map<Integer, CurrentAttendanceDayRecDTO> currentAttendanceDayRecMap = getAttendanceDaysVO.getCurrentAttendanceDayRecMap();
        currentAttendanceDayRecMap.forEach((k, v) -> {
            Integer upTime = v.getUpTime();
            Integer downTime = v.getDownTime();
            if (beginTimeMinute >= downTime || endTimeMinute <= upTime) {
                return;
            }
            DurationDTO duration = new DurationDTO();
            // 取两个开始时间相对较晚的一个
            upTime = upTime > beginTimeMinute ? upTime : beginTimeMinute;
            String sTimeStr = DateUtil.minute2HourMinute(upTime);
            // 取两个结束时间相对较早的一个
            downTime = downTime < endTimeMinute ? downTime : endTimeMinute;
            String eTimeStr = DateUtil.minute2HourMinute(downTime);

            duration.setTimeDesc(sTimeStr + "-" + eTimeStr);
            duration.setType(dayWorkTypeEnum.getCode());
            duration.setId(attendanceDoFilterDTO.getId());
            duration.setCreateTime(attendanceDoFilterDTO.getCreateTime());
            duration.setBeginTime(upTime);
            duration.setEndTime(downTime);
            durationDTOList.add(duration);
        });
        getAttendanceDaysVO.setDurationList(durationDTOList);
        putByDate(dateKey, getAttendanceDaysVO);
    }

    /**
     * 修改对应的工作状态
     * @author daxiong
     * @date 2020-04-11 18:06
     * @param date
     * @param dayWorkTypeEnum
     * @return void
     */
    public void updateStatus(Date date, DayWorkTypeEnum dayWorkTypeEnum) {
        GetAttendanceDaysVO getAttendanceDaysVO = getByDate(date);
        List<DurationDTO> durationList = getAttendanceDaysVO.getDurationList();
        if (durationList != null && durationList.size() > 0) {
            getAttendanceDaysVO.setType(dayWorkTypeEnum.getCode());
            getAttendanceDaysVO.setTypeName(dayWorkTypeEnum.getDesc());
        }
    }

    /**
     * 修改对应日期的工作状态
     * @author daxiong
     * @date 2020-04-11 18:15
     * @param attendanceDoFilterDTO
     * @return void
     */
    public void updateWorkDayStatusByTime(AttendanceDoFilterDTO attendanceDoFilterDTO) {
        Date sTime = attendanceDoFilterDTO.getBeginTime();
        Date eTime = attendanceDoFilterDTO.getEndTime();
        DayWorkTypeEnum dayWorkTypeEnum = attendanceDoFilterDTO.getDayWorkTypeEnum();

        int termDays = DateUtil.getTermDays(sTime, eTime);
        if (termDays == 0) {
            GetAttendanceDaysVO getAttendanceDaysVO = getByDate(sTime);
            if (getAttendanceDaysVO.getType() == DayWorkTypeEnum.OFF_WORK.getCode()) {
                return;
            }
            // 设置请假时间交集
            setTimeIntersection(attendanceDoFilterDTO, sTime, eTime, dayWorkTypeEnum);
            // 工作状态改为请假
            updateStatus(sTime, dayWorkTypeEnum);
        } else {
            for (int i = 0; i <= termDays; i++) {
                // 获取考勤的上下班时间
                GetAttendanceDaysVO getAttendanceDaysVO = getByDate(sTime);
                if (getAttendanceDaysVO.getType() == DayWorkTypeEnum.OFF_WORK.getCode()) {
                    // 该天休息，直接跳过
                    sTime = DateUtil.growDateIgnoreHMS(sTime);
                    continue;
                }
                // 判断最后一天有没有形成新的请假
                if (i == termDays && DateUtil.date2MinuteTotal(eTime) <= getAttendanceDaysVO.getEarliestTime()) {
                    return;
                }
                Integer sTimeMinute = i == 0 ? DateUtil.date2MinuteTotal(sTime) : getAttendanceDaysVO.getEarliestTime();
                Integer eTimeMinute = i == termDays ? DateUtil.date2MinuteTotal(eTime) : getAttendanceDaysVO.getLatestTime();
                setTimeIntersection(attendanceDoFilterDTO, sTime, sTimeMinute, eTimeMinute, dayWorkTypeEnum);
                // 修改工作状态
                updateStatus(sTime, dayWorkTypeEnum);
                // 日期加1
                sTime = DateUtil.growDateIgnoreHMS(sTime);
            }
        }
    }

    /**
     * 打印桶中的数据
     *
     * @param
     * @return void
     * @author daxiong
     * @date 2020-04-11 11:20
     */
    public void printBucket() {
        // 输出桶中的数据
        bucket.get().forEach((k, v) -> System.out.println("key为：" + k + "，value为：" + v));
    }

    /**
     * 根据日期将考勤实体存入桶中
     *
     * @param date              日期
     * @param getAttendanceDays 要存的value
     * @return void
     * @author daxiong
     * @date 2020-04-10 15:36
     */
    public void putByDate(Date date, GetAttendanceDaysVO getAttendanceDays) {
        bucket.get().put(date2DateKey(date), getAttendanceDays);
    }

    /**
     * 根据日期获取对应的值
     *
     * @param date
     * @return com.wxhj.cloud.feignClient.business.vo.GetAttendanceDaysVO
     * @author daxiong
     * @date 2020-04-10 15:43
     */
    public GetAttendanceDaysVO getByDate(Date date) {
        return bucket.get().get(date2DateKey(date));
    }

    /**
     * 日期和（桶中的）日期key的转换
     *
     * @param date
     * @return String
     * @author daxiong
     * @date 2020-04-10 15:44
     */
    private String date2DateKey(Date date) {
        String dateKeyStr = "";
        dateKeyStr += DateUtil.getYear(date);
        dateKeyStr += DateUtil.getMonth(date);
        dateKeyStr += DateUtil.getDay(date);
        return dateKeyStr;
    }


}
