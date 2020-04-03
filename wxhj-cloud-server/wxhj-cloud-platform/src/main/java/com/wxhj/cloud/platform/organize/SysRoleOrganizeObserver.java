package com.wxhj.cloud.platform.organize;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.design.observer.CommonObserver;
import com.wxhj.cloud.platform.domain.SysRoleDO;
import com.wxhj.cloud.platform.service.SysRoleService;

@Component
public class SysRoleOrganizeObserver extends CommonObserver<OrganizeVariableBO> {
	@Resource
	SysRoleService sysRoleService;

	@Override
	public OrganizeVariableBO execute(OrganizeVariableBO t) {
		SysRoleDO sysRoleTemp = new SysRoleDO();
		sysRoleTemp.setFullName("管理员");
		sysRoleTemp.setSortCode(1);
		sysRoleTemp.setOrganizeId(t.getId());
		sysRoleTemp.setDescription(t.getFullName() + "管理员");
		String insert = sysRoleService.insertSuperManage(sysRoleTemp, t.getUserId());
		t.setRoleId(insert);
		return t;
	}

}
