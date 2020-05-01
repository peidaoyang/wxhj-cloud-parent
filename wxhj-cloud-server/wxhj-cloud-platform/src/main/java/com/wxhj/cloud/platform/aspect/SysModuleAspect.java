/** 
 * @fileName: SysModuleAcpet.java  
 * @author: pjf
 * @date: 2019年11月14日 下午1:19:15 
 */

package com.wxhj.cloud.platform.aspect;

import javax.annotation.Resource;

import com.wxhj.cloud.platform.domain.SysModuleDO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeService;
import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;

/**
 * @className SysModuleAcpet.java
 * @author pjf
 * @date 2019年11月14日 下午1:19:15
 */

@Aspect
@Component
public class SysModuleAspect {

	@Resource
	SysOrganizeAuthorizeService sysOrganizeAuthorizeService;
	@Resource
	SysRoleAuthorizeService sysRoleAuthorizeService;

	@Pointcut("execution(public void com.wxhj.cloud.platform.service.SysModuleService.deleteCascade(..))")
	public void sysModuleDeleteCut() {
	}

	@After("sysModuleDeleteCut()")
	public void sysModuleDelete(JoinPoint joinPoint) {
		String id = (String) joinPoint.getArgs()[0];
		sysOrganizeAuthorizeService.deleteByModuleId(id);
		sysRoleAuthorizeService.deleteByModuleId(id);
	}
}
