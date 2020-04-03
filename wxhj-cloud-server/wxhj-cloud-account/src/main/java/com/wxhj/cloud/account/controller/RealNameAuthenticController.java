/**
 * @className RealNameAuthenticController.java
 * @admin jwl
 * @date 2019年12月12日 下午3:46:57
 */
package com.wxhj.cloud.account.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.curator.shaded.com.google.common.base.Strings;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.RealNameAuthenticDO;
import com.wxhj.cloud.account.dto.request.ListRealNameAuthRequestDTO;
import com.wxhj.cloud.account.dto.request.UpdateRealNameAuthRequestDTO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.RealNameAuthenticService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.RealNameAuthenticClient;
import com.wxhj.cloud.feignClient.account.request.RealNameAuthRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @className RealNameAuthenticController.java
 * @admin jwl
 * @date 2019年12月12日 下午3:46:57
 */
@RestController
@RequestMapping("/realNameAuthentic")
public class RealNameAuthenticController implements RealNameAuthenticClient{
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AccountInfoService accountInfoService;
	
	@Resource
	FileStorageService fileStorageService;
	@Resource
	RealNameAuthenticService realNameAuthenticService;
	
	@ApiOperation("实名认证")
	@PostMapping(value = "/realNameAuthentication")
	@Override
	public WebApiReturnResultModel realNameAuthentication(
			@Validated @RequestBody RealNameAuthRequestDTO realNameAuthenticationRequest) {
		if(!fileStorageService.existFile(realNameAuthenticationRequest.getFrontCardUuid()) && 
				!fileStorageService.existFile(realNameAuthenticationRequest.getBackCardUuid())) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.NO_FILE);
		}
		AccountInfoDO accountInfo = accountInfoService.selectByAccountId(
				realNameAuthenticationRequest.getAccountId());
		RealNameAuthenticDO realNameAuthentic;
		if(accountInfo.getIsReal() == 0) {
			realNameAuthentic = new RealNameAuthenticDO();
		}else {
			realNameAuthentic = realNameAuthenticService.selectByAccountId(
					realNameAuthenticationRequest.getAccountId());
		}
		realNameAuthentic.setFrontCardUuid(realNameAuthenticationRequest.getFrontCardUuid()); 
		realNameAuthentic.setBackCardUuid(realNameAuthenticationRequest.getBackCardUuid());
		realNameAuthentic.setApproveTime(new Date());
		realNameAuthentic.setIsApprove(1);
		if(Strings.isNullOrEmpty(realNameAuthentic.getAccountId())) {
			realNameAuthentic.setAccountId(accountInfo.getAccountId());
			realNameAuthenticService.insert(realNameAuthentic);
		}else {
			realNameAuthenticService.update(realNameAuthentic);
		}
		accountInfo.setIsReal(1);
		accountInfoService.update(accountInfo);
		
		return WebApiReturnResultModel.ofSuccess();
	}
	
	@ApiOperation("获取实名认证人员信息")
	@PostMapping(value = "/listRealNameAuth")
	public WebApiReturnResultModel listRealNameAuth(
			@Validated @RequestBody ListRealNameAuthRequestDTO listRealNameAuthentic) {
		
		return WebApiReturnResultModel.ofSuccess(realNameAuthenticService.listRealNameAuthentic(
				listRealNameAuthentic, listRealNameAuthentic.getNameValue()));
	}
	
	@ApiOperation("手动修改认证信息")
	@PostMapping(value = "/updateRealNameAuthentic")
	public WebApiReturnResultModel updateRealNameAuthentic(
			@Validated @RequestBody UpdateRealNameAuthRequestDTO updateRealNameAuth) {
		RealNameAuthenticDO realNameAuthentic = dozerBeanMapper.map(updateRealNameAuth,
				RealNameAuthenticDO.class);
		realNameAuthenticService.update(realNameAuthentic);
		AccountInfoDO accountInfo = new AccountInfoDO();
		accountInfo.setAccountId(realNameAuthentic.getAccountId());
		accountInfo.setIsReal(realNameAuthentic.getIsApprove());
		accountInfoService.update(accountInfo);
		return WebApiReturnResultModel.ofSuccess();
	}
}
