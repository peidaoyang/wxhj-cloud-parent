/**
 * 
 */
package com.wxhj.cloud.app.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AccountConsumeClient;
import com.wxhj.cloud.feignClient.account.request.AppConsumeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AppConsumeInfoSummaryRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: AppConsumeController.java
 * @author: cya
 * @Date: 2020年2月2日 下午4:52:36
 */
@Api(tags = "app消费相关业务")
@RestController
@RequestMapping("/appConsume")
public class AppConsumeController {
	@Resource
	AccountConsumeClient accountConsumeClient;

	@ApiOperation("app消费记录查询")
	@PostMapping("/appConsumeInfo")
	public WebApiReturnResultModel consumeInfo(@RequestBody @Validated AppConsumeInfoRequestDTO appConsumeInfo) {
		return accountConsumeClient.appConsumeInfo(appConsumeInfo);
	}
	
	@ApiOperation("app消费汇总记录查询")
	@PostMapping("/appConsumeInfoSummary")
	public WebApiReturnResultModel appConsumeInfoSummary(@RequestBody @Validated AppConsumeInfoSummaryRequestDTO appConsumeSummary) {
		return accountConsumeClient.appConsumeInfoSummary(appConsumeSummary);
	}

}
