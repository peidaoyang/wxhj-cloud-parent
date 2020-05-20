package com.wxhj.cloud.platform.organize;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.design.observer.CommonObserver;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;
import com.wxhj.cloud.platform.domain.SysUserDO;
import com.wxhj.cloud.platform.service.SysUserService;

@Component
public class SysUserOrganizeObserver extends CommonObserver<OrganizeVariableBO> {
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	SysUserService sysUserService;

	@Override
	public OrganizeVariableBO execute(OrganizeVariableBO t) throws Exception {
		SysUserDO sysUserDO = dozerBeanMapper.map(t.getSysOrgUserSubmitRequest(), SysUserDO.class);
		String key = UUID.randomUUID().toString().replace("-", "").substring(16);
		String passwordStr = sysUserDO.getMobilePhone().substring(sysUserDO.getMobilePhone().length() - 4);
		String passWord = PasswordUtil.calculationPassword(passwordStr, key);
		sysUserDO.setUserSecretKey(key);
		sysUserDO.setUserPassword(passWord);
		sysUserDO.setAccount(t.getAccountId());
		sysUserDO.setOrganizeId(t.getId());
		sysUserDO.initialization();
		List<MapOrganizeUserDO> mapOrganizeUserList = Arrays.asList(new MapOrganizeUserDO(t.getId(), t.getRoleId()));
		sysUserService.insertCascade(sysUserDO, t.getUserId(), mapOrganizeUserList);
		return t;
	}

}
