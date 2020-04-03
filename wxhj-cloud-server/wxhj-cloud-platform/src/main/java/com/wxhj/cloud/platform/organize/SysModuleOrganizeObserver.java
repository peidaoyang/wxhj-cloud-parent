package com.wxhj.cloud.platform.organize;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.design.observer.CommonObserver;
import com.wxhj.cloud.platform.domain.SysRoleAuthorizeDO;
import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;

@Component
public class SysModuleOrganizeObserver extends CommonObserver<OrganizeVariableBO> {
	@Resource
	SysRoleAuthorizeService sysRoleAuthorizeService;

	@Override
	public OrganizeVariableBO execute(OrganizeVariableBO t) {

		List<SysRoleAuthorizeDO> sysRoleAuthorizeList = t.getSysModuleIdList().stream()
				.map(q -> new SysRoleAuthorizeDO(q, t.getRoleId())).collect(Collectors.toList());
		sysRoleAuthorizeService.insertList(sysRoleAuthorizeList, t.getUserId());
		return t;
	}

}
