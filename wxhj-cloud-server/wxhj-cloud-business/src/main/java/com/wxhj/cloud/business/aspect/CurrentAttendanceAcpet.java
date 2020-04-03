/**
 * @className CurrentAttendanceAcpet.java
 * @author jwl
 * @date 2019年12月27日 下午5:14:43
 */
package com.wxhj.cloud.business.aspect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.business.bo.CurrentAttendanceDayRecBO;
import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import com.wxhj.cloud.business.domain.AttendanceGroupDO;
import com.wxhj.cloud.business.domain.AttendanceGroupRecDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceDayDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceDayRecDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;
import com.wxhj.cloud.business.service.AttendanceDayRecService;
import com.wxhj.cloud.business.service.AttendanceDayService;
import com.wxhj.cloud.business.service.AttendanceGroupRecService;
import com.wxhj.cloud.business.service.AttendanceGroupService;
import com.wxhj.cloud.business.service.CurrentAccountAuthorityService;
import com.wxhj.cloud.business.service.CurrentAttendanceDayRecService;
import com.wxhj.cloud.business.service.CurrentAttendanceDayService;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupRecService;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupService;
import com.wxhj.cloud.business.service.CurrentAuthoritySceneService;

/**
 * @className CurrentAttendanceAcpet.java
 * @author jwl
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
	@Resource
	CurrentAuthoritySceneService currentAuthoritySceneService;
	@Resource
	CurrentAttendanceGroupService currentAttendanceGroupService;
	@Resource
	CurrentAttendanceDayRecService currentAttendanceDayRecService;

	@Resource
	DozerBeanMapper dozerBeanMapper;

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
		insertCurrentAttendanceGroupDO(attendanceId);
		// 增加应用考勤详情
		insertCurrentAttendanceGroupRecDO(attendanceId);
		
		// 增加应用班次
		List<CurrentAttendanceDayRecBO> listId= insertCurrentAttendanceDayDO(attendanceId);
		// 增加应用班次详情
		insertCurrentAttendanceDayRecDO(listId);

		AttendanceGroupDO attendanceGroup = new AttendanceGroupDO();
		attendanceGroup.setId(attendanceId);
		attendanceGroup.setApplyDate(new Date());
//		MapAttendanceAuthorizeDO MapAttendanceAuthorizeDO = new MapAttendanceAuthorizeDO();
//		MapAttendanceAuthorizeDO.setAuthorityId(authorityId);
//		MapAttendanceAuthorizeDO.setAttendanceId(attendanceId);
//		MapAttendanceAuthorizeDO.setApplyDate(new Date());
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
	private void insertCurrentAttendanceGroupDO(String attendanceId) {
		AttendanceGroupDO attendanceGroup = attendanceGroupService.selectAttendanceGroupById(attendanceId);
		CurrentAttendanceGroupDO currentAttendanceGroup = dozerBeanMapper.map(attendanceGroup,
				CurrentAttendanceGroupDO.class);
		// currentAttendanceGroup.setId(authorityId);
		currentAttendanceGroupService.insert(currentAttendanceGroup);
	}

// String authorityId
	private List<CurrentAttendanceDayRecBO> insertCurrentAttendanceDayDO(String id) {
		List<AttendanceGroupRecDO> attendanceGroupRec = attendanceGroupRecService.listById(id);
		List<String> attendanceDayId = new ArrayList<String>();
		for (int i = 0; i < attendanceGroupRec.size(); i++) {
			AttendanceGroupRecDO attendanceGroupRecDO = attendanceGroupRec.get(i);
			if (!attendanceDayId.contains(attendanceGroupRecDO.getAttendanceDayId())) {
				attendanceDayId.add(attendanceGroupRecDO.getAttendanceDayId());
			}
		}
		List<CurrentAttendanceDayDO> listCurrent = new ArrayList<>();
		List<CurrentAttendanceDayRecBO> currentAttendanceIdList = new ArrayList<CurrentAttendanceDayRecBO>();
		attendanceDayId.forEach(q -> {
			CurrentAttendanceDayDO current = dozerBeanMapper.map(attendanceDayService.selectById(q),
					CurrentAttendanceDayDO.class);
			
			//此处需要设置一下groupId
			current.setAttendanceId(id);
			current.setId(UUID.randomUUID().toString());
			listCurrent.add(current);
			currentAttendanceIdList.add(new CurrentAttendanceDayRecBO(q,current.getId()));
		});
		currentAttendanceDayService.insertList(listCurrent);
		return currentAttendanceIdList;
	}

//String authorityId
	private void insertCurrentAttendanceGroupRecDO(String id) {
		List<AttendanceGroupRecDO> attendanceGroupRec = attendanceGroupRecService.listById(id);
//		List<CurrentAttendanceGroupRecDO> listCurrent = attendanceGroupRec.stream()
//				.map(q -> new CurrentAttendanceGroupRecDO(authorityId, q.getSerialNumber(), q.getAttendanceDayId()))
//				.collect(Collectors.toList());

		List<CurrentAttendanceGroupRecDO> listCurrent = attendanceGroupRec.stream()
				.map(q -> dozerBeanMapper.map(q, CurrentAttendanceGroupRecDO.class)).collect(Collectors.toList());

		currentAttendanceGroupRecService.insertList(listCurrent);
	}

	private void insertCurrentAttendanceDayRecDO(List<CurrentAttendanceDayRecBO> listId) {
		listId.forEach(q -> {
			List<AttendanceDayRecDO> listAttendanceDayRec = attendanceDayRecService
					.listAttendanceDayRecByAttendanceId(q.getAttendanceId());
			List<CurrentAttendanceDayRecDO> listCurrent = new ArrayList<>();
			listAttendanceDayRec.forEach(k -> {
				CurrentAttendanceDayRecDO current = dozerBeanMapper.map(k, CurrentAttendanceDayRecDO.class);
				//需要设置guid，否则数据库报错
				current.setId(UUID.randomUUID().toString());
				current.setAttendanceId(q.getCurrentAttendanceId());
				listCurrent.add(current);
			});
			currentAttendanceDayRecService.insertList(listCurrent);
		});
	}
}
