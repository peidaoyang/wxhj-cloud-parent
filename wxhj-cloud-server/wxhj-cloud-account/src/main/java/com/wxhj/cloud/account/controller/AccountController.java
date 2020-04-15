/**
 * @fileName: AccountController.java
 * @author: pjf
 * @date: 2019骞�10鏈�29鏃� 涓嬪崍2:07:08
 */

package com.wxhj.cloud.account.controller;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.domain.RechargeInfoDO;
import com.wxhj.cloud.account.dto.response.AppAccountInfoResponseDTO;
import com.wxhj.cloud.account.service.*;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.component.service.PhoneShortMessageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.file.IFileAnalysis;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.bo.AuthenticationTokenBO;
import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.account.response.AccountBalanceResponseDTO;
import com.wxhj.cloud.feignClient.account.response.AccountRegisterResponseDTO;
import com.wxhj.cloud.feignClient.account.response.AccountTotalResponseDTO;
import com.wxhj.cloud.feignClient.account.vo.*;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.vo.KeyValueVO;
import com.wxhj.cloud.redis.core.MoblicPhoneCodeHelper;
import com.wxhj.cloud.sso.AbstractSsoTemplate;
import com.wxhj.cloud.sso.bo.AppAuthenticationBO;
import com.wxhj.cloud.sso.bo.SsoLoginBO;
import com.wxhj.cloud.sso.execption.SsoException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

//import com.wxhj.cloud.account.dto.response.AppBalanceResponseDTO;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController implements AccountClient {
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    AccountInfoService accountInfoService;
    @Resource
    MoblicPhoneCodeHelper moblicPhoneCodeHelper;
    @Resource
    MapAccountAuthorityService mapAccountAuthorityService;
    @Resource
    ViewAuthorityAccountService viewAuthorityAccountService;

    @Resource
    AbstractSsoTemplate<AppAuthenticationBO> abstractSsoTemplate;
    @Resource
    FileStorageService fileStorageService;
    @Resource(name = "accountInfoFileAnalysis")
    IFileAnalysis<AccountInfoDO> accountInfoFileAnalysis;
    @Resource
    RechargeInfoService rechargeInfoService;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    PhoneShortMessageService phoneShortMessageService;

    @Resource
    MapAccountAuthorityPlusService mapAccountAuthorityPlusService;

    @ApiOperation("手机获取验证号")
    @PostMapping("/mobilePhoneCode")
    @Override
    public WebApiReturnResultModel mobilePhoneCode(
            @Validated @RequestBody() MobilePhoneCodeRequestDTO mobilePhoneCodeRequest) {
        String moblicPhoneCodeCheck = moblicPhoneCodeHelper
                .moblicPhoneCodeCheck(mobilePhoneCodeRequest.getMobilePhone());
        if (moblicPhoneCodeCheck != null) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.MOBILE_PHONE_CODE_EXIST);
        } else {
            String code = moblicPhoneCodeHelper.generateMoblicPhoneCode(mobilePhoneCodeRequest.getMobilePhone());
            Map<String, String> messageMap = new HashMap();
            messageMap.put("code", code);
            phoneShortMessageService.sendMessage(mobilePhoneCodeRequest.getMobilePhone(), messageMap);
        }
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("验证验证码接口")
    @PostMapping("/verifyMobileCode")
    @Override
    public WebApiReturnResultModel verifyMobileCode(
            @RequestBody @Validated VerifyMobileCodeRequestDTO verifyMobileCodeRequest) {
        return WebApiReturnResultModel.ofSuccess(moblicPhoneCodeHelper
                .moblicPhoneCodeCheck(verifyMobileCodeRequest.getMobilePhone(), verifyMobileCodeRequest.getCode()));
    }

    @ApiOperation("账户注册")
    @PostMapping("/accountRegister")
    @Override
    public WebApiReturnResultModel accountRegister(
            @Validated @RequestBody() AccountRegisterRequestDTO accountRegisterRequest) {
        boolean existByPhoneNumber = accountInfoService.isExistByPhoneNumberAndOrg(
                accountRegisterRequest.getPhoneNumber(), accountRegisterRequest.getOrganizeId());
        if (existByPhoneNumber) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.PHONE_NUMBER_EXIST);
        }


        AccountInfoDO accountInfoDO = dozerBeanMapper.map(accountRegisterRequest, AccountInfoDO.class);
        accountInfoDO.initialization();
        String key = PasswordUtil.generatePasswordKey();
        accountInfoDO.setUserSecretKey(key);
        String password = accountInfoDO.getPhoneNumber();
        password = password.substring(password.length() - 4, password.length());
        password = PasswordUtil.calculationPassword(password, key);
        accountInfoDO.setUserPassword(password);
        String accountId = accountInfoService.insert(accountInfoDO);

        return WebApiReturnResultModel.ofSuccess(new AccountRegisterResponseDTO(accountId));
    }

    @ApiOperation("添加或修改账户权限")
    @PostMapping("/submitAccountInfo")
    @Override
    @Transactional
    public WebApiReturnResultModel submitAccountInfo(
            @RequestBody SubmitAccountInfoRequestDTO submitAccountInfoRequest) {
        AccountInfoDO accountInfo = dozerBeanMapper.map(submitAccountInfoRequest, AccountInfoDO.class);
        //姓名由于下发设备暂不做修改
        accountInfo.setName(null);
        accountInfoService.update(accountInfo);


        List<MapAccountAuthorityDO> newMapAccountAuthorityList = submitAccountInfoRequest.getAuthorityGroupIdList()
                .stream().map(q -> new MapAccountAuthorityDO(null, q, submitAccountInfoRequest.getAccountId()))
                .collect(Collectors.toList());
        MapAccountAuthorityDO mapAccountAuthority = new MapAccountAuthorityDO();
        mapAccountAuthority.setAccountId(submitAccountInfoRequest.getAccountId());
        List<MapAccountAuthorityDO> oldMapAccountAuthorityList = mapAccountAuthorityService.list(mapAccountAuthority);
        List<MapAccountAuthorityDO> addMapAccountAuthorityList = newMapAccountAuthorityList.stream()
                .filter(q -> !oldMapAccountAuthorityList.contains(q)).collect(Collectors.toList());
        List<MapAccountAuthorityDO> deleteMapAccountAuthorityList = oldMapAccountAuthorityList.stream()
                .filter(q -> !newMapAccountAuthorityList.contains(q)).collect(Collectors.toList());
        addMapAccountAuthorityList.forEach(q -> {
            mapAccountAuthorityService.insertCascade(q);
        });
        deleteMapAccountAuthorityList.forEach(q -> {
            mapAccountAuthorityService.deleteCascade(q.getAuthorityGroupId(), q.getAccountId());
        });
        return WebApiReturnResultModel.ofSuccess();
    }

    @PostMapping(value = "/importFileAccountInfo")
    @ApiOperation("导入账户信息")
    @Override
    public WebApiReturnResultModel importFileAccountInfo(
            @Validated @RequestBody ImportFileAccountInfoRequestDTO importFileAccountInfoRequest) {
        byte[] fileByte = fileStorageService.getFile(importFileAccountInfoRequest.getFileName());
        if (fileByte == null) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.NO_FILE);
        }

        List<AccountInfoDO> accountInfoList = accountInfoFileAnalysis.fileAnalysis(fileByte);
        for (AccountInfoDO accountInfoDO : accountInfoList) {
            accountInfoDO.initialization();
            accountInfoDO.setOrganizeId(importFileAccountInfoRequest.getOrganizeId());
            accountInfoDO.setChildOrganizeId(importFileAccountInfoRequest.getChildOrganizeId());

            String key = PasswordUtil.generatePasswordKey();
            accountInfoDO.setUserSecretKey(key);
            String password = accountInfoDO.getPhoneNumber();
            password = password.substring(password.length() - 4, password.length());
            password = PasswordUtil.calculationPassword(password, key);
            accountInfoDO.setUserPassword(password);
        }
        accountInfoService.insertList(accountInfoList);
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("根据账户id查询单条账户信息")
    @PostMapping("/accountOne")
    @Override
    public WebApiReturnResultModel accountOne(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
        AccountInfoDO selectByAccountId = accountInfoService.selectByAccountId(commonIdRequest.getId());
        if (selectByAccountId == null) {
            return WebApiReturnResultModel.ofSuccess(selectByAccountId);
        }
        AccountOneVO accountInfo = dozerBeanMapper.map(selectByAccountId, AccountOneVO.class);
        return WebApiReturnResultModel.ofSuccess(accountInfo);
    }

    @ApiOperation("根据账户id查询账户详细信息")
    @PostMapping("/accountDetail")
    @Override
    public WebApiReturnResultModel accountDetail(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
        AccountInfoDO selectByAccountId = accountInfoService.selectByAccountId(commonIdRequest.getId());
        if (selectByAccountId == null) {
            return WebApiReturnResultModel.ofSuccess(selectByAccountId);
        }
        AccountDetailVO accountInfo = dozerBeanMapper.map(selectByAccountId, AccountDetailVO.class);
        List<AccountDetailVO> accountInfoList = Arrays.asList(accountInfo);
        try {
            accountInfoList = (List<AccountDetailVO>) accessedRemotelyService
                    .accessedOrganizeChildrenList(accountInfoList);
//			accountInfoList = (List<AccountDetailVO>) accessedRemotelyService.accessedFaceImageList(accountInfoList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        accountInfo = accountInfoList.get(0);
        accountInfo.setImageUrl(fileStorageService.generateFileUrl(accountInfo.getImageName()));

        List<ViewAuthorityAccountVO> authList = viewAuthorityAccountService.list(accountInfo.getAccountId()).stream()
                .map(q -> dozerBeanMapper.map(q, ViewAuthorityAccountVO.class)).collect(Collectors.toList());
        Collections.sort(authList);
        accountInfo.setAuthorityList(authList);
        return WebApiReturnResultModel.ofSuccess(accountInfo);
    }

    @ApiOperation("分页根组织账户信息（子组织）")
    @PostMapping("/listAccountPage")
    @Override
    public WebApiReturnResultModel listAccountPage(
            @Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
//		PageInfo<AccountInfoDO> listByNameOrPhoneNumberPage = accountInfoService.listByNameOrPhoneNumberPage(
//				commonListPageRequest.getNameValue(), commonListPageRequest.getOrganizeId(), commonListPageRequest);
        PageInfo<AccountInfoDO> listByNameOrPhoneNumberPage = accountInfoService.listByNameAndChildrenOrg(
                commonListPageRequest.getNameValue(), commonListPageRequest.getOrganizeId(), commonListPageRequest);

        List<AccountInfoVO> accountInfoList = listByNameOrPhoneNumberPage.getList().stream()
                .map(q -> dozerBeanMapper.map(q, AccountInfoVO.class)).collect(Collectors.toList());
        try {
            accountInfoList = (List<AccountInfoVO>) accessedRemotelyService.accessedOrganizeChildrenList(accountInfoList);
        } catch (WuXiHuaJieFeignError e) {
            // TODO Auto-generated catch block
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
                .initPageResponseModel(listByNameOrPhoneNumberPage, accountInfoList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("分页根组织账户信息（根组织）")
    @PostMapping("/listAccountPageByRootOrg")
    @Override
    public WebApiReturnResultModel listAccountPageByRootOrg(
            @Validated @RequestBody ListAccountPageByRootOrg listAccountPageByRootOrg) {
        PageInfo<AccountInfoDO> listByNameOrPhoneNumberPage = accountInfoService.listByNamePage(
                listAccountPageByRootOrg.getNameValue(), listAccountPageByRootOrg.getOrganizeId(),
                listAccountPageByRootOrg.getType(), listAccountPageByRootOrg);
        List<AccountInfoVO> accountInfoList = listByNameOrPhoneNumberPage.getList().stream()
                .map(q -> dozerBeanMapper.map(q, AccountInfoVO.class)).collect(Collectors.toList());
        try {
            accountInfoList = (List<AccountInfoVO>) accessedRemotelyService
                    .accessedOrganizeChildrenList(accountInfoList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
                .initPageResponseModel(listByNameOrPhoneNumberPage, accountInfoList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("组织及子组织账户信息")
    @PostMapping("/listAccountPageByOrg")
    @Override
    public WebApiReturnResultModel listAccountPageByOrg(
            @Validated @RequestBody ListAccountPageByOrgRequestDTO listAccountPageByOrg) {
        PageInfo<AccountInfoDO> listByNameOrPhoneNumberPage = accountInfoService.listByNameOrPhoneNumberAndChildOrgPage(
                listAccountPageByOrg.getNameValue(), listAccountPageByOrg.getOrganizeIdList(),listAccountPageByOrg.getType(), listAccountPageByOrg);

        List<AccountInfoVO> accountInfoList = listByNameOrPhoneNumberPage.getList().stream()
                .map(q -> dozerBeanMapper.map(q, AccountInfoVO.class)).collect(Collectors.toList());

        if (accountInfoList != null && accountInfoList.size() > 0) {
            try {
                accountInfoList = (List<AccountInfoVO>) accessedRemotelyService.accessedOrganizeChildrenList(accountInfoList);
            } catch (WuXiHuaJieFeignError e) {
                return e.getWebApiReturnResultModel();
            }

            for (AccountInfoVO accountInfoTemp : accountInfoList) {
                accountInfoTemp.setImageUrl(fileStorageService.generateFileUrl(accountInfoTemp.getImageName()));
            }
        }

        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
                .initPageResponseModel(listByNameOrPhoneNumberPage, accountInfoList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }


//	@ApiOperation("更新是否人脸注册")
//	@PostMapping("/updateIsFace")
//	@Override
//	@Transactional
//	public WebApiReturnResultModel updateIsFace(@RequestBody UpdateIsFaceRequestDTO updateIsFaceRequest) {
//		AccountInfoDO accountInfo = dozerBeanMapper.map(updateIsFaceRequest, AccountInfoDO.class);
//		accountInfoService.update(accountInfo);
//		return WebApiReturnResultModel.ofSuccess();
//	}

    @ApiOperation("用户登录查询组织")
    @PostMapping("/accountLoginOrganize")
    public WebApiReturnResultModel accountLoginOrganize(
            @RequestBody AccountLoginOrganizeRequestDTO listAccountLoginRequest) {
        List<String> listOrganize = new ArrayList<String>();
        for (AccountInfoDO accountInfo : accountInfoService.listByPhoneNumber(listAccountLoginRequest.getUserName())) {
            listOrganize.add(accountInfo.getOrganizeId());
        }
        AccountInfoDO accountInfo = accountInfoService.selectByAccountId(listAccountLoginRequest.getUserName());
        if (accountInfo != null) {
            listOrganize.add(accountInfo.getOrganizeId());
        }
        return WebApiReturnResultModel.ofSuccess(listOrganize);
    }

    @ApiOperation("账户登录")
    @PostMapping("/accountLogin")
    @Override
    public WebApiReturnResultModel accountLogin(@RequestBody AccountLoginRequestDTO accountLoginRequest) {
        AppAuthenticationBO appAuthentication;
        try {
            SsoLoginBO ssoLogin = dozerBeanMapper.map(accountLoginRequest, SsoLoginBO.class);
            appAuthentication = abstractSsoTemplate.login(ssoLogin);
        } catch (SsoException e) {
            return WebApiReturnResultModel.ofStatus(e.getWebResponseState());
        }
        AuthenticationTokenBO authentication = dozerBeanMapper.map(appAuthentication, AuthenticationTokenBO.class);
        return WebApiReturnResultModel.ofSuccess(authentication);
    }

    @Override
    @ApiOperation("登出")
    @PostMapping("/accountLogout")
    public WebApiReturnResultModel accountLogout(@RequestBody @Validated AccoutLogoutRequestDTO accoutLogoutRequest) {
        abstractSsoTemplate.logout(accoutLogoutRequest.getSessionId());
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("账户重置密码")
    @PostMapping("/accountResetPassword")
    @Override
    public WebApiReturnResultModel accountResetPassword(
            @Validated @RequestBody AccountResetPasswordRequestDTO accountreset) {
        String oldPassword = accountreset.getOldPassword();
        AccountInfoDO accountInfoDO = accountInfoService.selectByAccountId(accountreset.getAccountId());
        oldPassword = PasswordUtil.calculationPassword(oldPassword, accountInfoDO.getUserSecretKey());
        if (!oldPassword.equals(accountInfoDO.getUserPassword())) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.PASSWORD_ERROR);
        }
        String key = PasswordUtil.generatePasswordKey();
        String newPassword = PasswordUtil.calculationPassword(accountreset.getNewPassword(), key);
        AccountInfoDO accountInfo = new AccountInfoDO();
        accountInfo.setAccountId(accountreset.getAccountId());
        accountInfo.setUserPassword(newPassword);
        accountInfo.setUserSecretKey(key);
        accountInfoService.update(accountInfo);
        return WebApiReturnResultModel.ofSuccess();
    }

    @Transactional
    @Override
    @ApiOperation("忘记密码")
    @PostMapping("/forgetPassword")
    public WebApiReturnResultModel forgetPassword(
            @RequestBody @Validated ForgetPasswordRequestDTO forgetPasswordRequest) {

        if (!moblicPhoneCodeHelper.moblicPhoneCodeCheck(forgetPasswordRequest.getMobilePhone(),
                forgetPasswordRequest.getCode())) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.PHONE_CODE_ERROR);
        }

        List<AccountInfoDO> accountList = accountInfoService.listByPhoneNumber(forgetPasswordRequest.getMobilePhone());
        for (AccountInfoDO accountInfo : accountList) {
            String key = PasswordUtil.generatePasswordKey();
            String newPassword = PasswordUtil.calculationPassword(forgetPasswordRequest.getNewPassword(), key);
            accountInfo.setUserPassword(newPassword);
            accountInfo.setUserSecretKey(key);
        }
        accountList.forEach(q -> accountInfoService.update(q));
        return WebApiReturnResultModel.ofSuccess();
    }

//	@ApiModelProperty("用户余额查询")
//	@PostMapping("/appBalance")
//	@Override
//	public WebApiReturnResultModel appBalance(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
//		AppBalanceResponseDTO appBalanceResponse = new AppBalanceResponseDTO(
//				accountInfoService.selectByAccountId(commonIdRequest.getId()).getAccountBalance() / 100.00);
//		return WebApiReturnResultModel.ofSuccess(appBalanceResponse);
//	}

    @ApiOperation("用户余额查询")
    @PostMapping("/accountBalance")
    @Override
    public WebApiReturnResultModel accountBalance(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        AccountInfoDO accountInfo = accountInfoService.selectByAccountId(commonIdRequest.getId());

        if (accountInfo == null) {
            return WebApiReturnResultModel.ofSuccess(WebResponseState.ACCOUNT_NO_EXIST);
        }
        double balance = accountInfo.getAccountBalance() / 100.00;
        AccountBalanceResponseDTO appBalanceResponse = new AccountBalanceResponseDTO(commonIdRequest.getId()
                , balance);
        return WebApiReturnResultModel.ofSuccess(appBalanceResponse);
    }


    @ApiOperation("后台充值")
    @PostMapping("/recharge")
    @Override
    @Transactional
    public WebApiReturnResultModel recharge(@Validated @RequestBody RechargeRequestDTO recharge) {
        int balance = accountInfoService.selectByAccountId(recharge.getAccountId()).getAccountBalance();
        accountInfoService.recharge(recharge.getAccountId(), recharge.getAmount());

        RechargeInfoDO rechargeInfo = dozerBeanMapper.map(recharge, RechargeInfoDO.class);
        rechargeInfo.setAccountBalance(balance + recharge.getAmount());
        rechargeInfo.setOrganizeId(recharge.getCurrentOrganizeId());
        return WebApiReturnResultModel.ofSuccess(rechargeInfoService.insert(rechargeInfo, recharge.getUserId()));
    }

    @ApiOperation("扣费:仅用于自动化测试")
    @PostMapping("/charging")
    public WebApiReturnResultModel charging(@RequestBody ChargingRequestDTO chargingRequest) {
        AccountInfoDO accountInfo = accountInfoService.selectByAccountId(chargingRequest.getAccountId());
        accountInfo.setAccountBalance(accountInfo.getAccountBalance() - chargingRequest.getAmount());
        accountInfo.setRechargeTotalAmount(accountInfo.getRechargeTotalAmount() - chargingRequest.getAmount());
        accountInfoService.update(accountInfo);
        rechargeInfoService.delete(chargingRequest.getOrderId());
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("app用户信息查询接口")
    @PostMapping("/appAccountInfo")
    @Override
    public WebApiReturnResultModel appAccountInfo(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
        AccountInfoDO selectByAccountId = accountInfoService.selectByAccountId(commonIdRequest.getId());
        AppAccountInfoResponseDTO accountResponseInfo = dozerBeanMapper.map(selectByAccountId,
                AppAccountInfoResponseDTO.class);
        return WebApiReturnResultModel.ofSuccess(accountResponseInfo);
    }

    @ApiOperation("已注册人脸和根组织查询账户")
    @PostMapping("/listAccountByChildOrganizeList")
    @Override
    public WebApiReturnResultModel listAccountByChildOrganizeList(
            @Validated @RequestBody CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest) {
        List<AccountInfoDO> listByChildOrganIdList = accountInfoService
                .listByChildOrgIdAndIsFace(commonOrganizeIdListRequest.getOrganizeIdList());
        List<KeyValueVO> keyValueList = listByChildOrganIdList.stream()
                .map(q -> new KeyValueVO(q.getAccountId(), q.getName())).collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(keyValueList);
    }

    @ApiOperation("app用户冻结")
    @Override
    @PostMapping("/accountFrozen")
    public WebApiReturnResultModel accountFrozen(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
//		List<MapAccountAuthorityDO> listByAccountId = mapAccountAuthorityService
//				.listByAccountId(commonIdRequest.getId());
//		for (MapAccountAuthorityDO mapAccountAuthorityTemp : listByAccountId) {
//			mapAccountAuthorityService.deleteCascade(mapAccountAuthorityTemp.getAuthorityGroupId(),
//					mapAccountAuthorityTemp.getAccountId());
//		}
        mapAccountAuthorityPlusService.deleteByAccountId(commonIdRequest.getId());
        //
        AccountInfoDO accountInfo = new AccountInfoDO();
        accountInfo.setAccountId(commonIdRequest.getId());
        accountInfo.setIsFrozen(1);
        //
        accountInfoService.update(accountInfo);
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("账户解冻")
    @PostMapping("/accountThaw")
    @Override
    public WebApiReturnResultModel accountThaw(@RequestBody @Validated CommonIdRequestDTO commonId) {
        AccountInfoDO accountInfo = new AccountInfoDO();
        accountInfo.setAccountId(commonId.getId());
        accountInfo.setIsFrozen(0);
        accountInfoService.update(accountInfo);
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("app用户删除(用户删除的前提是已冻结)")
    @Override
    @PostMapping("/accountDelete")
    public WebApiReturnResultModel accountDelete(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
        AccountInfoDO selectByAccountId = accountInfoService.selectByAccountId(commonIdRequest.getId());
        if (selectByAccountId == null) {
            return WebApiReturnResultModel.ofSuccess();
        }
        if (!selectByAccountId.getIsFrozen().equals(1)) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.FACE_NOT_FROZEN);
        }

        if (selectByAccountId.getIsFace() == 1) {
            //删除人脸图片
            fileStorageService.deleteFile(selectByAccountId.getImageName());
        }
        accountInfoService.deleteCascade(selectByAccountId);
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("账户数量统计")
    @PostMapping("/accountTotal")
    @Override
    public WebApiReturnResultModel accountTotal(@Validated @RequestBody CommonIdRequestDTO commonIdRequest){
        int accountTotal = accountInfoService.listByOrganizeId(commonIdRequest.getId()).size();
        int faceAccountTotal = accountInfoService.listByOrganizeIdAndIsFace(commonIdRequest.getId());
        return WebApiReturnResultModel.ofSuccess(new AccountTotalResponseDTO(accountTotal,faceAccountTotal,null));
    }
}