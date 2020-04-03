/** 
 * @fileName: SysOrganizeAcpet.java  
 * @author: pjf
 * @date: 2019年11月13日 下午1:34:54 
 */

package com.wxhj.cloud.platform.aspect;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.platform.bo.SysOrgOptimizeBO;
import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeDO;
import com.wxhj.cloud.platform.service.MapOrganizeUserService;
import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeService;

/**
 * @className SysOrganizeAcpet.java
 * @author pjf
 * @date 2019年11月13日 下午1:34:54
 */
@Aspect
@Component
public class SysOrganizeAspect {
	@Resource
	SysOrganizeAuthorizeService sysOrganizeAuthorizeService;
	@Resource
	MapOrganizeUserService mapOrganizeUserService;

	@Pointcut("execution(public void com.wxhj.cloud.platform.service.SysOrganizeService.shamDeleteCascade(..))")
	public void sysOrganizeShamDeleteCut() {
	}

//	@Pointcut("execution(public String com.wxhj.cloud.platform.service.SysOrganizeService.insert(..))")
//	public void sysOrganizeInsertCut() {
//
//	}

	@Pointcut("execution(public String com.wxhj.cloud.platform.service.SysOrganizeService.insertCascade(..))")
	public void sysOrganizeInsertOptimizeCut() {

	}

//	@AfterReturning(returning = "rObject", value = "sysOrganizeInsertCut()")
//	public void sysOrganizeInsert(JoinPoint joinPoint, Object rObject) {
//		String organizeId = (String) rObject;
//		SysOrganizeDO sysOrganize = (SysOrganizeDO) joinPoint.getArgs()[0];
//		String userId = (String) joinPoint.getArgs()[1];
//		if (sysOrganize.getLayers().equals(1)) {
//			sysOrganizeAuthorizeService.inserDef(organizeId, userId);
//		} else {
//			String parentId = sysOrganize.getParentId();
//			List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = sysOrganizeAuthorizeService.selectByOrganizeId(parentId);
//			sysOrganizeAuthorizeList.forEach(q->{
//				q.setOrganizeId(organizeId);
//			});
//			sysOrganizeAuthorizeService.insertList(sysOrganizeAuthorizeList, userId);
//		}
//	}

	@AfterReturning(returning = "rObject", value = "sysOrganizeInsertOptimizeCut()")
	public void sysOrganizeOptimizeInsert(JoinPoint joinPoint, Object rObject) {
		String organizeId = (String) rObject;
		SysOrgOptimizeBO sysOrganize = (SysOrgOptimizeBO) joinPoint.getArgs()[0];
		String userId = (String) joinPoint.getArgs()[1];
		List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = new ArrayList<>();
		List<String> moduleList = sysOrganize.getSysModuleIdList();
		moduleList.forEach(q -> {
			SysOrganizeAuthorizeDO sysOrganizeAuthorize = new SysOrganizeAuthorizeDO(q, organizeId);
			sysOrganizeAuthorizeList.add(sysOrganizeAuthorize);
		});
		sysOrganizeAuthorizeService.insertList(sysOrganizeAuthorizeList, userId);
	}

	@After("sysOrganizeShamDeleteCut()")
	public void sysOrganizeShamDeleteByKey(JoinPoint joinPoint) {
		String id = (String) joinPoint.getArgs()[0];
		sysOrganizeAuthorizeService.deleteByModuleId(id);
		mapOrganizeUserService.deleteByOrganizeId(id);
	}
}
