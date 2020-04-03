/**
 * @className EntranceGroupAspect.java
 * @author jwl
 * @date 2020年1月10日 下午4:24:05
 */
package com.wxhj.cloud.business.aspect;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.EntranceGroupDO;
import com.wxhj.cloud.business.domain.EntranceGroupRecDO;
import com.wxhj.cloud.business.service.EntranceGroupRecService;
import com.wxhj.cloud.business.service.EntranceGroupService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.device.DeviceGlobalParameterClient;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @className EntranceGroupAspect.java
 * @author jwl
 * @date 2020年1月10日 下午4:24:05
 */
@Aspect
@Component
public class EntranceGroupAspect {

	@Resource
	EntranceGroupRecService entranceGroupRecService;
	@Resource
	EntranceGroupService entranceGroupService;
	@Resource
	DeviceGlobalParameterClient deviceGlobalParameterClient;
	@Resource
	AuthorityGroupClient authorityGroupClient;

	@Pointcut("execution(public String com.wxhj.cloud.business.service.EntranceGroupService.insertCascade(..))")
	public void entranceGroupInsertCut() {
	}

	@Pointcut("execution(public void com.wxhj.cloud.business.service.EntranceGroupService.updateCascade(..))")
	public void entranceGroupUpdateCut() {
	}

	@Pointcut("execution(public void com.wxhj.cloud.business.service.EntranceGroupService.deleteCascade(..))")
	public void entranceGroupDeleteCut() {
	}

	@AfterReturning(returning = "rObject", value = "entranceGroupInsertCut()")
	public void entranceGroupInsert(JoinPoint joinPoint, Object rObject) {
		Object[] args = joinPoint.getArgs();
		String id = (String) rObject;
		List<EntranceGroupRecDO> entranceGroupRecList = (List<EntranceGroupRecDO>) args[1];
		entranceGroupRecList = entranceGroupRecList.stream().map(q -> {

			q.setEntranceGroupId(id);
			return q;
		}).collect(Collectors.toList());
		entranceGroupRecService.insert(entranceGroupRecList);
	}

	@After("entranceGroupUpdateCut()")
	public void entranceGroupUpdate(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		List<EntranceGroupRecDO> entranceGroupRecList = (List<EntranceGroupRecDO>) args[1];
		if (entranceGroupRecList.size() > 0) {
			entranceGroupRecService.delete(entranceGroupRecList.get(0).getEntranceGroupId());
			entranceGroupRecService.insert(entranceGroupRecList);
		}
	}

	@Before("entranceGroupDeleteCut()")
	public void entranceGroupDelete(JoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		String id = (String) args[0];
		EntranceGroupDO selectById = entranceGroupService.selectById(id);
		if (selectById == null) {
			return;
		}
		// 对应删除权限组
		WebApiReturnResultModel deleteAuthorityGroupInfo = authorityGroupClient
				.deleteAuthorityGroupInfo(new CommonIdRequestDTO(id));
		if (!deleteAuthorityGroupInfo.resultSuccess()) {
			throw new Throwable("删除权限组失败");
		}
		// 对应删除资源文件
		if (!Strings.isNullOrEmpty(selectById.getParameterId())) {
			deleteAuthorityGroupInfo = deviceGlobalParameterClient
					.deleteDeviceGlobalRarameter(new CommonIdRequestDTO(selectById.getParameterId()));
			if (!deleteAuthorityGroupInfo.resultSuccess()) {
				throw new Throwable("删除资源文件失败");
			}
		}
	}
}
