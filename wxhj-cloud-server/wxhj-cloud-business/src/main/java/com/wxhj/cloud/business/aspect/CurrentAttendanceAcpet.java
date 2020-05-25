/**
 * @className CurrentAttendanceAcpet.java
 * @author jwl
 * @date 2019年12月27日 下午5:14:43
 */
package com.wxhj.cloud.business.aspect;

import com.wxhj.cloud.business.domain.*;
import com.wxhj.cloud.business.service.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jwl
 * @className CurrentAttendanceAcpet.java
 * @date 2019年12月27日 下午5:14:43
 */
@Aspect
@Component
public class CurrentAttendanceAcpet {
    @Resource
    AttendanceGroupService attendanceGroupService;
    @Resource
    AttendanceGroupRecService attendanceGroupRecService;
    @Resource
    AttendanceDayService attendanceDayService;
    @Resource
    AttendanceDayRecService attendanceDayRecService;
    @Resource
    CurrentAccountAuthorityService currentAccountAuthorityService;
    @Resource
    CurrentAttendanceDayService currentAttendanceDayService;
    @Resource
    CurrentAttendanceGroupRecService currentAttendanceGroupRecService;
    //@Resource
    //CurrentAuthoritySceneService currentAuthoritySceneService;
    @Resource
    CurrentAttendanceGroupService currentAttendanceGroupService;
    @Resource
    CurrentAttendanceDayRecService currentAttendanceDayRecService;

    @Resource
    Mapper dozerBeanMapper;

    @Pointcut("execution(public void com.wxhj.cloud.business.service.CurrentAuthoritySceneService.insertListCascade(..))")
    public void authoritySceneInsertCut() {

    }

    @Pointcut("execution(public void com.wxhj.cloud.business.service.CurrentAuthoritySceneService.deleteCascade(..))")
    public void authoritySceneDeleteCut() {

    }

    @AfterReturning(returning = "rObject", value = "authoritySceneInsertCut()")
    public void insert(JoinPoint joinPoint, Object rObject) {
        Object[] args = joinPoint.getArgs();
        List<CurrentAccountAuthorityDO> listAccount = (List<CurrentAccountAuthorityDO>) args[1];
        // String authorityId = (String) args[2];
        String attendanceId = (String) args[2];
        currentAccountAuthorityService.insertList(listAccount);
        // 增加应用考勤组
        insertCurrentAttendanceGroup(attendanceId);
        // 增加应用考勤详情
        List<CurrentAttendanceGroupRecDO> attendanceGroupList = insertCurrentAttendanceGroupRecDO(attendanceId);

        //
        List<String> dayIdList = attendanceGroupList.stream().map(q -> q.getAttendanceDayId()).distinct().collect(Collectors.toList());

        // 增加应用班次
        insertCurrentAttendanceDay(attendanceId, dayIdList);
        // 增加应用班次详情
        insertCurrentAttendanceDayRec(attendanceId, dayIdList);

        AttendanceGroupDO attendanceGroup = new AttendanceGroupDO();
        attendanceGroup.setId(attendanceId);
        attendanceGroup.setApplyDate(LocalDateTime.now());

        attendanceGroupService.update(attendanceGroup);
    }

    @Before("authoritySceneDeleteCut()")
    public void delete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String authorityId = (String) args[0];
        currentAttendanceGroupService.delete(authorityId);
        currentAccountAuthorityService.delete(authorityId);
    }

    // String authorityId
    private void insertCurrentAttendanceGroup(String attendanceId) {
        AttendanceGroupDO attendanceGroup = attendanceGroupService.selectAttendanceGroupById(attendanceId);
        CurrentAttendanceGroupDO currentAttendanceGroup = dozerBeanMapper.map(attendanceGroup,
                CurrentAttendanceGroupDO.class);
        currentAttendanceGroupService.insert(currentAttendanceGroup);
    }
    // String authorityId
    private void insertCurrentAttendanceDay(String id, List<String> dayIdList) {
        List<AttendanceDayDO> attendanceDayList = attendanceDayService.listByIdList(dayIdList);
        List<CurrentAttendanceDayDO> currentAttendanceDayList =
                attendanceDayList.stream().map(q -> {
                    CurrentAttendanceDayDO currentAttendanceDayTemp = dozerBeanMapper.map(q,
                            CurrentAttendanceDayDO.class);
                    currentAttendanceDayTemp.setGroupId(id);
                    currentAttendanceDayTemp.setDayId(q.getId());
                    return currentAttendanceDayTemp;
                }).collect(Collectors.toList());
        currentAttendanceDayService.insertList(currentAttendanceDayList);
    }


    private List<CurrentAttendanceGroupRecDO> insertCurrentAttendanceGroupRecDO(String id) {
        List<AttendanceGroupRecDO> attendanceGroupRec = attendanceGroupRecService.listById(id);
        List<CurrentAttendanceGroupRecDO> listCurrent = attendanceGroupRec.stream()
                .map(q -> dozerBeanMapper.map(q, CurrentAttendanceGroupRecDO.class)).collect(Collectors.toList());
        currentAttendanceGroupRecService.insertList(listCurrent);
        return listCurrent;
    }

    private void insertCurrentAttendanceDayRec(String id, List<String> dayIdList) {

        List<AttendanceDayRecDO> attendanceDayRecList = attendanceDayRecService.listByIdList(dayIdList);

        List<CurrentAttendanceDayRecDO> currentAttendanceDayRecList = attendanceDayRecList.stream().map(q -> {
            CurrentAttendanceDayRecDO currentAttendanceDayRecTemp = dozerBeanMapper.map(q, CurrentAttendanceDayRecDO.class);
            currentAttendanceDayRecTemp.setDayId(q.getId());
            currentAttendanceDayRecTemp.setGroupId(id);
            return currentAttendanceDayRecTemp;
        }).collect(Collectors.toList());
        currentAttendanceDayRecService.insertList(currentAttendanceDayRecList);

    }
}
