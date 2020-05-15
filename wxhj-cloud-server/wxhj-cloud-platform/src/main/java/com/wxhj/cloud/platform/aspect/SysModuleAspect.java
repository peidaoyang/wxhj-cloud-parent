/** 
 * @fileName: SysModuleAcpet.java  
 * @author: pjf
 * @date: 2019年11月14日 下午1:19:15 
 */

package com.wxhj.cloud.platform.aspect;

import javax.annotation.Resource;

import com.wxhj.cloud.core.enums.OrganizeTypeEnum;
import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;
import com.wxhj.cloud.platform.domain.SysModuleDO;
import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeTypeDO;
import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeTypeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeService;
import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;

import java.util.List;

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
	@Resource
	SysOrganizeAuthorizeTypeService sysOrganizeAuthorizeTypeService;


	@Pointcut("execution(public String com.wxhj.cloud.platform.service.SysModuleService.insertCascade(..))")
	public void sysModuleInsertCut() {
	}

	@Pointcut("execution(public void com.wxhj.cloud.platform.service.SysModuleService.deleteCascade(..))")
	public void sysModuleDeleteCut() {
	}

	@AfterReturning(returning = "rObject", value = "sysModuleInsertCut()")
	public void sysModuleInsert(JoinPoint joinPoint, Object rObject) {
		String moduleId = (String) rObject;
		String userid = (String) joinPoint.getArgs()[1];
		Integer orgType = (Integer)joinPoint.getArgs()[2];
		if(orgType == OrganizeTypeEnum.DEFAULT_TYPE.getCode()){
			//超级管理员只有一个，在新增菜单的时候需要给超级管理员自动赋予菜单权限
			sysOrganizeAuthorizeService.insert(moduleId,"f8b89131-de13-4dc2-b5bb-b117e12c23bc",userid);
		}else{
			sysOrganizeAuthorizeTypeService.insert(new SysOrganizeAuthorizeTypeDO(moduleId,orgType));
		}
	}

	@After("sysModuleDeleteCut()")
	public void sysModuleDelete(JoinPoint joinPoint) {
		String id = (String) joinPoint.getArgs()[0];
		sysOrganizeAuthorizeService.deleteByModuleId(id);
		sysRoleAuthorizeService.deleteByModuleId(id);
		sysOrganizeAuthorizeTypeService.delete(id);
	}
}
