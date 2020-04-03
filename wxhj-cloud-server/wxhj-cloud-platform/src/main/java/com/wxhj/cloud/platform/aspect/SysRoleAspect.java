/**
 * 
 */
package com.wxhj.cloud.platform.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;

/**
 * @ClassName: SysRoleAspect.java
 * @author: cya
 * @Date: 2020年3月10日 上午10:30:24 
 */
@Aspect
@Component
public class SysRoleAspect {
	@Resource
	SysRoleAuthorizeService sysRoleAuthorizeService;
	
//	@Pointcut("execution(public String com.wxhj.cloud.platform.service.SysRoleService.insertOptimize(..))")
//	public void sysRoleInsertCut() {
//		
//	}
	
	@Pointcut("execution(public String com.wxhj.cloud.platform.service.SysRoleService.deleteByOrganizeIdCascade(..))")
	public void sysRoleDeleteCut() {
		
	}
	
//	@AfterReturning(returning = "rObject", value = "sysRoleInsertCut()")
//	public void sysRoleInsert(JoinPoint joinPoint, Object rObject) {
//		String roleId = (String) rObject;
//		SysRoleBO sysRole = (SysRoleBO) joinPoint.getArgs()[0];
//		String userId = (String) joinPoint.getArgs()[1];
//		List<String> moduleList = sysRole.getSysModuleIdList();
//		List<SysRoleAuthorizeDO> sysRoleAuthorizeList = moduleList.stream().map(q -> new SysRoleAuthorizeDO(q,roleId)).collect(Collectors.toList());
//		sysRoleAuthorizeService.insertList(sysRoleAuthorizeList, userId);
//	}
	
	@After("sysRoleDeleteCut()")
	public void sysRoleDeleteCut(JoinPoint joinPoint) {
		String id = (String) joinPoint.getArgs()[0];
		sysRoleAuthorizeService.deleteByOrganizeId(id);
	}
	
}
