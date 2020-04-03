/**
 * @className AppLoginHandle.java
 * @admin jwl
 * @date 2019年12月10日 下午5:15:43
 */
package com.wxhj.cloud.account.config;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.core.enums.AccountLoginTypeEnum;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.sso.AbstractSsoTemplate;
import com.wxhj.cloud.sso.SsoCacheOperation;
import com.wxhj.cloud.sso.bo.AppAuthenticationBO;
import com.wxhj.cloud.sso.bo.SsoLoginBO;
import com.wxhj.cloud.sso.execption.IncorrectCredentialsSsoException;
import com.wxhj.cloud.sso.execption.LockedAccountSsoException;
import com.wxhj.cloud.sso.execption.SsoException;
import com.wxhj.cloud.sso.execption.UnknownAccountSsoException;

/**
 * @className AppLoginHandle.java
 * @admin jwl
 * @date 2019年12月10日 下午5:15:43
 */
@Component
public class AppLoginHandle extends AbstractSsoTemplate<AppAuthenticationBO> {
	@Resource
	DozerBeanMapper dozerBeanMapper;

	@Resource
	AccountInfoService accountIfnoService;

	@Resource
	SsoCacheOperation<AppAuthenticationBO> ssoCacheOperation;

	AccountInfoDO accountInfo;

	@Override
	public AppAuthenticationBO doGetAuthenticationInfo(SsoLoginBO ssoLogin) throws SsoException {
		AppAuthenticationBO appAuthentication = dozerBeanMapper.map(ssoLogin, AppAuthenticationBO.class);

		if (ssoLogin.getLoginType().equals(AccountLoginTypeEnum.ACCOUNT_LOGIN.getLoginType())) {
			accountInfo = accountIfnoService.selectByAccountId(ssoLogin.getUserName());
		} else {
			accountInfo = accountIfnoService.selectByOrganizeIdAndPhone(ssoLogin.getMapId(), ssoLogin.getUserName());
		}

		if (accountInfo == null) {
			throw new UnknownAccountSsoException();
		}

		String passwordStr = PasswordUtil.calculationPassword(ssoLogin.getPassword(), accountInfo.getUserSecretKey());

		if (appAuthentication.getLoginType() == 0) {
			if (!accountInfo.getAccountId().equals(appAuthentication.getUserName())) {
				throw new UnknownAccountSsoException();
			}
		} else if (appAuthentication.getLoginType() == 1) {
			if (!accountInfo.getPhoneNumber().equals(appAuthentication.getUserName())) {
				throw new UnknownAccountSsoException();
			}
		}

		if (!accountInfo.getUserPassword().equals(passwordStr)) {
			throw new IncorrectCredentialsSsoException();
		}
		appAuthentication.setUserId(accountInfo.getAccountId());
		appAuthentication.setOrganizeId(accountInfo.getOrganizeId());
		appAuthentication.setUserName(accountInfo.getName());

		return appAuthentication;
	}

	@Override
	protected SsoCacheOperation<AppAuthenticationBO> getSsoCacheOperation() {
		ssoCacheOperation.setExpireMinite(OtherStaticClass.SSO_REDIS_EXPIRE_MINITE);
		ssoCacheOperation.setKey(RedisKeyStaticClass.SSO_USER);
		return ssoCacheOperation;
	}

}
