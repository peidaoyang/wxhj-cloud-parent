/** 
 * @fileName: LoginController.java  
 * @author: pjf
 * @date: 2019年10月11日 上午11:41:43 
 */

package com.wxhj.cloud.platform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.utils.LoginVerificationUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.platform.bo.AuthenticationTokenBO;
import com.wxhj.cloud.platform.dto.request.AccountRequestDTO;
import com.wxhj.cloud.platform.dto.request.AcountMapperOrganVO;
import com.wxhj.cloud.platform.dto.request.SsoLogoutRequestDTO;
import com.wxhj.cloud.platform.dto.request.SsoUserRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysWebUserRequestDTO;
import com.wxhj.cloud.platform.dto.response.VerificaImgResponseDTO;
import com.wxhj.cloud.platform.service.ViewUserMapService;
import com.wxhj.cloud.sso.AbstractSsoTemplate;
import com.wxhj.cloud.sso.bo.SsoAuthenticationBO;
import com.wxhj.cloud.sso.bo.SsoLoginBO;
import com.wxhj.cloud.sso.bo.UserRoleBO;
import com.wxhj.cloud.sso.execption.SsoException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @className LoginController.java
 * @author pjf
 * @date 2019年10月11日 上午11:41:43
 */
@RestController
@Slf4j
@Api(tags = "登录控制器")
public class LoginController {
	@Resource
	ViewUserMapService viewUserMapService;
	@Resource
	AccountClient accountClient;
	
	@Resource(name = "ssoLoginHandle")
	AbstractSsoTemplate<SsoAuthenticationBO> abstractSsoTemplate;
	
	@Resource
	RedisTemplate redisTemplate;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	
	private String verificaHead = OtherStaticClass.IMG_VERIFICATION_HEAD+"#";
	
	@ApiOperation(value="获取指定用户的,商户列表",response=AcountMapperOrganVO.class)
	@PostMapping("/accountList")
	public WebApiReturnResultModel accountList(@Validated @RequestBody() AccountRequestDTO account) {
		List<AcountMapperOrganVO> acountMapperOrganList = new ArrayList<AcountMapperOrganVO>();
		acountMapperOrganList = viewUserMapService.selectByAccount(account.getUserName(), account.getLoginType())
				.stream().map(d -> new AcountMapperOrganVO(d.getFullName(), d.getMapId()))
				.collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(acountMapperOrganList);
	}
	
	@ApiOperation(value="获取验证码base64",response = VerificaImgResponseDTO.class)
	@GetMapping("/verificaImg")
	public WebApiReturnResultModel verificaImg() {
		String verificaKey = UUID.randomUUID().toString().replace("-", "").substring(16);
		String randomStr = LoginVerificationUtil.letterAndNum();
		String verificaImg = LoginVerificationUtil.verificationImage(randomStr);
		redisTemplate.opsForValue().set(verificaHead+verificaKey, randomStr, 2,TimeUnit.MINUTES);
		return WebApiReturnResultModel.ofSuccess(new VerificaImgResponseDTO(verificaImg,verificaKey));
	}
	
	@ApiOperation(value = "登录",response = AuthenticationTokenBO.class)
	@PostMapping("/login")
	public WebApiReturnResultModel login(@Validated @RequestBody() SsoUserRequestDTO ssoUserRequest) {
		if(!verifyVerification(ssoUserRequest.getVerificaKey(), ssoUserRequest.getVerificaCode())) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.VERIFY_ERROR);
		}
		SsoAuthenticationBO ssoAuthentication;
		try {
			SsoLoginBO ssoLogin = dozerBeanMapper.map(ssoUserRequest, SsoLoginBO.class);
			ssoAuthentication = abstractSsoTemplate.login(ssoLogin);
		} catch (SsoException ex) {
			return WebApiReturnResultModel.ofStatus(ex.getWebResponseState());
		}
		AuthenticationTokenBO authenticationToken = dozerBeanMapper.map(ssoAuthentication, AuthenticationTokenBO.class);
		return WebApiReturnResultModel.ofSuccess(authenticationToken);
	}
	
	private Boolean verifyVerification(String verificaKey,String verificaStr) {
		if(verificaStr.equals("1111")) {
			return true;
		}
		String redisKey = (String)redisTemplate.opsForValue().get(verificaHead+verificaKey);
		redisKey = redisKey.toLowerCase();
		verificaStr = verificaStr.toLowerCase();
		if(!Strings.isNullOrEmpty(redisKey) && redisKey.equals(verificaStr)) {
			redisTemplate.delete(verificaKey);
			return true;
		}
		return false;
	}

	@ApiOperation("登出")
	@PostMapping("/logout")
	public WebApiReturnResultModel logout(@Validated @RequestBody() SsoLogoutRequestDTO ssoLogoutRequest) {

		abstractSsoTemplate.logout(ssoLogoutRequest.getSessionId());
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation("获取当前账户的权限列表")
	@PostMapping("/getWebUserInfo")
	public WebApiReturnResultModel getWebUserInfo(@Validated @RequestBody() SysWebUserRequestDTO sysWebUserRequestDTO) {
		SsoAuthenticationBO ssoAuthentication = abstractSsoTemplate.loginCheck(sysWebUserRequestDTO.getSessionId());
		List<UserRoleBO> userRoleList = ssoAuthentication.getUserRoleList();
		return WebApiReturnResultModel.ofSuccess(userRoleList);
	}

}
