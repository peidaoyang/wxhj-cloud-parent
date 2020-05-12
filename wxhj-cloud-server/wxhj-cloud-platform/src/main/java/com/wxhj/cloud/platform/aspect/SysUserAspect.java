/** 
 * @fileName: SysUserAcpet.java  
 * @author: pjf
 * @date: 2019年11月12日 上午8:58:30 
 */

package com.wxhj.cloud.platform.aspect;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;
import com.wxhj.cloud.platform.domain.SysUserDO;
import com.wxhj.cloud.platform.service.MapOrganizeUserService;
import com.wxhj.cloud.platform.service.ViewOrganizeUserService;

/**
 * @className SysUserAcpet.java
 * @author pjf
 * @date 2019年11月12日 上午8:58:30
 */

@Aspect
@Component
public class SysUserAspect {
	@Resource
	MapOrganizeUserService mapOrganizeUserService;
	@Resource
	ViewOrganizeUserService viewOrganizeUserService;
	@Resource
	Mapper dozerBeanMapper;

	@Pointcut("execution(public String com.wxhj.cloud.platform.service.SysUserService.insertCascade(..))")
	public void sysUserInsertCut() {
	}

	@Pointcut("execution(public void com.wxhj.cloud.platform.service.SysUserService.updateCascade(..))")
	public void sysUserUpdateCut() {

	}

	@AfterReturning(returning = "rObject", value = "sysUserInsertCut()")
	public void sysUserInsert(JoinPoint joinPoint, Object rObject) {
		Object[] args = joinPoint.getArgs();
		String userId = (String) rObject;
		List<MapOrganizeUserDO> mapOrganizeUserList = (List<MapOrganizeUserDO>) args[2];
		mapOrganizeUserList.forEach(q -> q.setUserId(userId));
		mapOrganizeUserService.insert(mapOrganizeUserList);
	}

	@After("sysUserUpdateCut()")
	public void sysUserUpdate(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		SysUserDO sysUser = (SysUserDO) args[0];
		List<MapOrganizeUserDO> newMapOrganizeUserList = ((List<MapOrganizeUserDO>) args[2]).stream()
				.filter(q -> !Strings.isNullOrEmpty(q.getRoleId())).collect(Collectors.toList());
		List<MapOrganizeUserDO> oldMapOrganizeUserList = viewOrganizeUserService.listByUserId(sysUser.getId()).stream()
				.map(q -> {
					return new MapOrganizeUserDO(q.getId(), q.getOrganizeId(), q.getUserId(), q.getRoleId());
				}).collect(Collectors.toList());

		List<MapOrganizeUserDO> deleteMapOrganizeUserList = oldMapOrganizeUserList.stream()
				.filter(q -> !newMapOrganizeUserList.contains(q)).collect(Collectors.toList());
		List<MapOrganizeUserDO> addMapOrganizeUserList = newMapOrganizeUserList.stream()
				.filter(q -> !oldMapOrganizeUserList.contains(q)).collect(Collectors.toList());
		List<String> deleteList = deleteMapOrganizeUserList.stream().map(q -> q.getId()).collect(Collectors.toList());
		if (deleteList.size() > 0) {
			mapOrganizeUserService.deleteById(deleteList);
		}
		if (addMapOrganizeUserList.size() > 0) {
			mapOrganizeUserService.insert(addMapOrganizeUserList);
		}
	}
	
}
