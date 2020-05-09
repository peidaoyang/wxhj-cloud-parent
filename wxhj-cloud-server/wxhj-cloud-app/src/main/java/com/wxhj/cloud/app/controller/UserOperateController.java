/**
 * @className UserOperateController.java
 * @admin jwl
 * @date 2019年12月9日 下午1:44:38
 */
package com.wxhj.cloud.app.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizeClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className UserOperateController.java
 * @admin jwl
 * @date 2019年12月9日 下午1:44:38
 */
@Api(tags = "app用户系统操作控制器")
@RestController
@RequestMapping("/userOperate")
public class UserOperateController {
    @Resource
    OrganizeClient organizeClient;
    @Resource
    AccountClient accountClient;
    @Resource
    FaceAccountClient faceAccountClient;

    @ApiOperation("用户人脸注册")
    @PostMapping("/faceRegister")
    @LcnTransaction
    public WebApiReturnResultModel faceRegister(@Validated @RequestBody FaceRegisterRequestDTO faceRegisterRequest) {
        return faceAccountClient.faceRegister(faceRegisterRequest);
    }

//	@ApiOperation("用户注册")
//	@PostMapping("/accountRegister")
//	@LcnTransaction
//	public WebApiReturnResultModel accountRegister(
//			@Validated @RequestBody AccountRegisterRequestDTO accountRegisterRequest) {
//		return accountClient.accountRegister(accountRegisterRequest);
//	}

    @ApiOperation("用户登录")
    @PostMapping("/accountLogin")
    @LcnTransaction
    public WebApiReturnResultModel accountLogin(@Validated @RequestBody AccountLoginRequestDTO accountLoginRequest) {
        return accountClient.accountLogin(accountLoginRequest);
    }

    @ApiOperation("用户登录查询组织")
    @PostMapping("/accountLoginOrganize")
    public WebApiReturnResultModel accountLoginOrganize(
            @Validated @RequestBody AccountLoginOrganizeRequestDTO listAccountLoginRequest) {
        WebApiReturnResultModel webApiReturnResultModel = accountClient.accountLoginOrganize(listAccountLoginRequest);
        List<String> list = null;
        try {
            list = FeignUtil.formatArrayClass(webApiReturnResultModel, String.class);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest = new CommonOrganizeIdListRequestDTO(list);
        if (Strings.isNullOrEmpty(list.get(0).toString())) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_NO_EXIST);
        }

        return organizeClient.listOrganizeByIdList(commonOrganizeIdListRequest);
    }


    @ApiOperation("重置用户密码")
    @PostMapping("/accountResetPassword")
    @LcnTransaction
    public WebApiReturnResultModel accountResetPassword(
            @Validated @RequestBody AccountResetPasswordRequestDTO accountreset) {
        return accountClient.accountResetPassword(accountreset);
    }

    @ApiOperation("用户信息查询")
    @PostMapping("/accountInfo")
    @LcnTransaction
    public WebApiReturnResultModel accountInfo(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
        return accountClient.appAccountInfo(commonIdRequest);
    }

    @ApiOperation("获取验证码")
    @PostMapping("/mobilePhoneCode")
    @LcnTransaction
    public WebApiReturnResultModel mobilePhoneCode(
            @Validated @RequestBody() MobilePhoneCodeRequestDTO mobilePhoneCodeRequest) {
        return accountClient.mobilePhoneCode(mobilePhoneCodeRequest);
    }

    @ApiOperation("验证码")
    @PostMapping("/verifyMobileCode")
    @LcnTransaction
    public WebApiReturnResultModel verifyMobileCode(
            @RequestBody @Validated VerifyMobileCodeRequestDTO verifyMobileCodeRequest) {
        return accountClient.verifyMobileCode(verifyMobileCodeRequest);
    }

    @ApiOperation("忘记密码")
    @PostMapping("/forgetPassword")
    @LcnTransaction
    public WebApiReturnResultModel forgetPassword(
            @RequestBody @Validated ForgetPasswordRequestDTO forgetPasswordRequest) {
        return accountClient.forgetPassword(forgetPasswordRequest);
    }

    @ApiOperation("登出")
    @PostMapping("/accountLogout")
    @LcnTransaction
    public WebApiReturnResultModel accountLogout(@RequestBody @Validated AccoutLogoutRequestDTO accoutLogoutRequest) {
        return accountClient.accountLogout(accoutLogoutRequest);
    }

    @ApiModelProperty("用户余额查询")
    @PostMapping("/appBalance")
    @LcnTransaction
    public WebApiReturnResultModel appBalance(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        return accountClient.accountBalance(commonIdRequest);
    }

}
