package com.wxhj.cloud.app.controller;

import javax.annotation.Resource;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.RechargeClient;
import com.wxhj.cloud.feignClient.account.request.AppRechargeInfoRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: RechargeInfoController.java
 * @author: cya
 * @Date: 2020年2月2日 下午3:12:41
 */
@Api(tags = "app充值相关业务")
@RestController
@RequestMapping("/appRecharge")
public class AppRechargeController {
	@Resource
	RechargeClient rechargeClient;

	@PostMapping("/rechargeInfo")
	@ApiOperation(value="充值查询")
	@LcnTransaction
	public WebApiReturnResultModel rechargeInfo(@RequestBody @Validated AppRechargeInfoRequestDTO appRechargeInfo) {
		return rechargeClient.appRechargeInfo(appRechargeInfo);
	}

}
