package com.wxhj.cloud.business.attendance;

import com.wxhj.cloud.business.attendance.factory.AttendanceSingleDayFactory;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
import com.wxhj.cloud.business.bo.AttendanceGroupBO;
import com.wxhj.cloud.business.bo.AttendanceMatchingBO;
import com.wxhj.cloud.business.config.AttendanceExtentTypeEnum;
import com.wxhj.cloud.business.domain.*;
import com.wxhj.cloud.business.service.*;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.feignClient.business.bo.AttendanceGroupRecBO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    @Resource
    CurrentAuthoritySceneService currentAuthoritySceneService;
    @Resource
    CurrentAccountAuthorityService currentAccountAuthorityService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    CurrentAttendanceGroupService currentAttendanceGroupService;
    @Resource
    CurrentAttendanceGroupRecService currentAttendanceGroupRecService;
    @Resource
    CurrentAttendanceDayService currentAttendanceDayService;
    @Resource
    CurrentAttendanceDayRecService currentAttendanceDayRecService;

    @Override
    public CurrentAccountAuthorityDO selectCurrentAccountAuthority(AttendanceRecordBO attendanceRecord) {
        // 根据场景查询当前对应的权限组
        List<CurrentAuthoritySceneDO> currentAuthoritySceneList = currentAuthoritySceneService
                .selectBySceneId(attendanceRecord.getSceneId());
        List<String> authorityGroupIdList = currentAuthoritySceneList.stream().map(q -> q.getAuthorityGroupId())
                .collect(Collectors.toList());
        if (authorityGroupIdList.size() < 1) {
            // 无匹配权限组
            return null;
        }
        // 根据权限组id查询对应的规则
        List<CurrentAccountAuthorityDO> currentAccountAuthorityList = currentAccountAuthorityService
                .selectByAuthorityList(authorityGroupIdList);
        // 在当前规则中查找对应的账户
        currentAccountAuthorityList = currentAccountAuthorityList.stream()
                .filter(q -> q.getAccountId().equals(attendanceRecord.getAccountId())).collect(Collectors.toList());
        if (currentAccountAuthorityList.size() < 1) {
            // 无该用户匹配账户
            return null;
        }
        // 获取匹配的到的第一个账户
        CurrentAccountAuthorityDO currentAccountAuthority = currentAccountAuthorityList.get(0);
        return currentAccountAuthority;
    }

    @Override
    public List<AttendanceSingleDayBO> listAttendanceSingleDay(CurrentAccountAuthorityDO currentAccountAuthority) {
        // 获取权限组
        CurrentAttendanceGroupDO currentAttendanceGroup = currentAttendanceGroupService
                .selectById(currentAccountAuthority.getAuthorityGroupId());
        AttendanceGroupBO attendanceGroup = dozerBeanMapper.map(currentAttendanceGroup, AttendanceGroupBO.class);
        // 获取权限组明细
        List<CurrentAttendanceGroupRecDO> currentAttendanceGroupRecList = currentAttendanceGroupRecService
                .selectByAttendanceGroupId(currentAccountAuthority.getAuthorityGroupId());
        attendanceGroup.setAttendanceGroupRecList(currentAttendanceGroupRecList.stream()
                .map(q -> dozerBeanMapper.map(q, AttendanceGroupRecBO.class)).collect(Collectors.toList()));
        // 获取权限组对应的日规则
        List<CurrentAttendanceDayDO> currentAttendanceDayList = currentAttendanceDayService
                .listByGroupId(currentAccountAuthority.getAuthorityGroupId());
        List<AttendanceDayBO> attendanceDayList = new ArrayList<>();
        currentAttendanceDayList.forEach(q -> {
            AttendanceDayBO attendanceDayTemp = dozerBeanMapper.map(q, AttendanceDayBO.class);
            List<CurrentAttendanceDayRecDO> currnetAttendanceDayRecList = currentAttendanceDayRecService
                    .listByGroupIdAndDayId(q.getGroupId(), q.getDayId());
            attendanceDayTemp.setAttendanceDayRec(currnetAttendanceDayRecList.stream()
                    .map(p -> dozerBeanMapper.map(p, AttendanceDayRecBO.class)).collect(Collectors.toList()));
            attendanceDayList.add(attendanceDayTemp);
        });
        // 构造AttendanceSingleDayBO列表
        List<AttendanceSingleDayBO> attendanceSingleDayList = AttendanceSingleDayFactory
                .createAttendanceDateMatching(attendanceGroup, attendanceDayList);
        return attendanceSingleDayList;
    }

    @Override
    public AttendanceMatchingBO mathingAttendanceMatching(AttendanceRecordBO attendanceRecord,
                                                          List<AttendanceSingleDayBO> attendanceSingleDayList) {
        // Calendar cal = Calendar.getInstance();
        Date date = attendanceRecord.getRecordDatetime();
        // cal.setTime(date);
        Integer dayTimeStamp = DateUtil.getDateNumber(date, Calendar.HOUR_OF_DAY) * 60
                + DateUtil.getDateNumber(date, Calendar.MINUTE);
        //
        AttendanceMatchingTypeEnum matchingType = AttendanceMatchingTypeEnum.MATCHING_TYPE1;

        AttendanceMatchingBO attendanceMatching = null;
        // 匹配逻辑
        for (int i = 0; i < attendanceSingleDayList.size(); ) {
            //
            attendanceMatching = null;
            if (AttendanceMatchingTypeEnum.MATCHING_TYPE1.equals(matchingType)) {
                attendanceMatching = attendanceSingleDayList.get(i).matching(date, dayTimeStamp, matchingType);
                matchingType = AttendanceMatchingTypeEnum.MATCHING_TYPE2;
            } else if (AttendanceMatchingTypeEnum.MATCHING_TYPE2.equals(matchingType)) {
                Date dateTemp = DateUtil.calcDate(date, Calendar.DAY_OF_YEAR, -1);
//				cal.setTime(date);
//				cal.add(Calendar.DAY_OF_YEAR, -1);
                attendanceMatching = attendanceSingleDayList.get(i).matching(dateTemp,
                        dayTimeStamp + AttendanceStaticClass.DAY_MINUTE, matchingType);
                matchingType = AttendanceMatchingTypeEnum.MATCHING_TYPE1;
                i++;
            } else {
                Date dateTemp = DateUtil.calcDate(date, Calendar.DAY_OF_YEAR, -1);
//				cal.setTime(date);
//				cal.add(Calendar.DAY_OF_YEAR, -1);
                attendanceMatching = attendanceSingleDayList.get(i).matching(dateTemp,
                        dayTimeStamp + AttendanceStaticClass.DAY_MINUTE, matchingType);
                i++;
            }
            if (attendanceMatching != null) {
                if (attendanceMatching.getExtentType() == 1) {
                    matchingType = AttendanceMatchingTypeEnum.MATCHING_TYPE3;
                    attendanceMatching = null;
                } else {
                    break;
                }
            }
        }
        return attendanceMatching;
    }

    @Override
    public AttendanceMatchingBO defAttendanceMatching(AttendanceRecordBO attendanceRecord) {
        AttendanceMatchingBO attendanceMatching = new AttendanceMatchingBO();
        attendanceMatching.setMatchingDate(attendanceRecord.getRecordDatetime());
        attendanceMatching.setMatchingTime(0);
        attendanceMatching.setAttendanceId("0");
        attendanceMatching.setAttendanceSequence(0);
        attendanceMatching.setDownTime(1339);
        attendanceMatching.setUpTime(0);
        attendanceMatching.setExtentType(AttendanceExtentTypeEnum.NO_MATCH.getExtentType());
        return attendanceMatching;
    }

    @Override
    public AttendanceDataDO composeAttendanceData(AttendanceRecordBO attendanceRecord,
                                                  AttendanceMatchingBO attendanceMatching) {
        AttendanceDataDO attendanceData = dozerBeanMapper.map(attendanceRecord, AttendanceDataDO.class);
        attendanceData.setAttendanceId(attendanceMatching.getAttendanceId());
        attendanceData.setMatchingDate(attendanceMatching.getMatchingDate());
        attendanceData.setMatchingTime(attendanceMatching.getMatchingTime());
        attendanceData.setAttendanceSequence(attendanceMatching.getAttendanceSequence());
        attendanceData.setExtentType(attendanceMatching.getExtentType());
        attendanceData.setUpTime(attendanceMatching.getUpTime());
        attendanceData.setDownTime(attendanceMatching.getDownTime());
//        attendanceData.setRecordTimeStamp(
//                attendanceRecord.getRecordDatetime().getTime() / 1000
//                        -
//                        AttendanceStaticClass.WEEK_DAY_SECOND);
        attendanceData.setRecordTimeStamp(attendanceRecord.getRecordDatetime().getTime() / 1000);
        return attendanceData;
        // attendanceDataService.insertCascade(attendanceData);
    }

}
