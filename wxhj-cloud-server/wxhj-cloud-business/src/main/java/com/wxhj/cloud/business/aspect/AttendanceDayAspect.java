/**
 * @className AttendanceDayAspect.java
 * @admin jwl
 * @date 2019年12月16日 下午2:57:13
 */
package com.wxhj.cloud.business.aspect;

import java.util.List;

import javax.annotation.Resource;

import com.wxhj.cloud.business.domain.AttendanceDayDO;
import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.attenance.AttendanceDayRecBO;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
import com.wxhj.cloud.business.service.AttendanceDayRecService;

/**
 * @className AttendanceDayAspect.java
 * @admin jwl
 * @date 2019年12月16日 下午2:57:13
 */
@Aspect
@Component
public class AttendanceDayAspect {
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AttendanceDayRecService attendanceDayRecService;
	
	@Pointcut("execution(public String com.wxhj.cloud.business.service.AttendanceDayService.insertCascade(..))")
	public void attendanceInsertCut() {
		
	}
	
	@Pointcut("execution(public void com.wxhj.cloud.business.service.AttendanceDayService.updateCascade(..))")
	public void attendanceUpdateCut() {
		
	}

	
	@AfterReturning(returning = "rObject", value = "attendanceInsertCut()")
	public void insert(JoinPoint joinPoint, Object rObject) {
		Object[] args = joinPoint.getArgs();
		String id = (String) rObject;
		AttendanceDayDO attendanceDay = (AttendanceDayDO) args[0];
		List<AttendanceDayRecDO> attendanceDayRecList = (List<AttendanceDayRecDO>) args[1];
		//List<AttendanceDayRecBO> listAttendanceDayRec = attendanceDayBO.getAttendanceDayRec();
		attendanceDayRecList.forEach(q -> q.setAttendanceId(id));
		attendanceDayRecService.insertList(attendanceDayRecList);
	}
	
	@After("attendanceUpdateCut()")
	@Transactional
	public void update(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		AttendanceDayDO attendanceDay = (AttendanceDayDO) args[0];
		List<AttendanceDayRecDO> attendanceDayRecList = (List<AttendanceDayRecDO>) args[1];

		attendanceDayRecList.forEach(q -> q.setAttendanceId(attendanceDay.getId()));
		
		attendanceDayRecService.delete(attendanceDay.getId());
		
		attendanceDayRecService.insertList(attendanceDayRecList);
	}
	
//	@Before("attendanceRecInsertListCut()")
//	public void listInsert(JoinPoint joinPoint) {
//		Object[] args = joinPoint.getArgs();
//		List<AttendanceDayRecBO> listAttendanceDayRec = (List<AttendanceDayRecBO>) args[0];
//		attendanceDayRecService.delete(listAttendanceDayRec.get(0).getAttendanceId());
//	}
}
