/**
 * @className AttendanceGroupAspect.java
 * @admin jwl
 * @date 2019年12月16日 下午3:44:20
 */
package com.wxhj.cloud.business.aspect;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.business.domain.AttendanceGroupDO;
import com.wxhj.cloud.business.domain.AttendanceGroupRecDO;
import com.wxhj.cloud.business.service.AttendanceGroupRecService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @className AttendanceGroupAspect.java
 * @admin jwl
 * @date 2019年12月16日 下午3:44:20
 */
@Aspect
@Component
public class AttendanceGroupAspect {

	@Resource
	AuthorityGroupClient authorityGroupClient;
	@Resource
	AttendanceGroupRecService attendanceGroupRecService;

	@Pointcut("execution(public String com.wxhj.cloud.business.service.AttendanceGroupService.insertCascade(..))")
	public void attendanceInsertCut() {

	}

	@Pointcut("execution(public void com.wxhj.cloud.business.service.AttendanceGroupService.updateCascade(..))")
	public void attendanceUpdateCut() {

	}

	@Pointcut("execution(public void com.wxhj.cloud.business.service.AttendanceGroupService.deleteCascade(..))")
	public void attendanceDeleteCut() {

	}

	@AfterReturning(returning = "rObject", value = "attendanceInsertCut()")
	public void insert(JoinPoint joinPoint, Object rObject) {
		Object[] args = joinPoint.getArgs();
		List<AttendanceGroupRecDO> attendanceGroupRecList = (List<AttendanceGroupRecDO>) args[1];
		attendanceGroupRecService.insertList(attendanceGroupRecList);
	}

	@After("attendanceUpdateCut()")
	public void update(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		AttendanceGroupDO attendanceGroup = (AttendanceGroupDO) args[0];
		List<AttendanceGroupRecDO> attendanceGroupRecList = (List<AttendanceGroupRecDO>) args[1];
		attendanceGroupRecService.delete(attendanceGroup.getId());
		attendanceGroupRecService.insertList(attendanceGroupRecList);
	}

	@Before("attendanceDeleteCut()")
	public void attendanceDelete(JoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		String id = (String) args[0];

		// 对应删除权限组
		WebApiReturnResultModel deleteAuthorityGroupInfo = authorityGroupClient
				.deleteAuthorityGroupInfo(new CommonIdRequestDTO(id));
		if (!deleteAuthorityGroupInfo.resultSuccess()) {
			throw new Throwable("删除权限组失败");
		}
	}
}
