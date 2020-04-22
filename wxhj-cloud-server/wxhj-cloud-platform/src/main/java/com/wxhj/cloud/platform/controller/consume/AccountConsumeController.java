package com.wxhj.cloud.platform.controller.consume;

import javax.annotation.Resource;

import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.account.vo.PersonConsumeVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AccountConsumeClient;
import com.wxhj.cloud.feignClient.account.vo.AccountConsumeVO;
import com.wxhj.cloud.feignClient.account.vo.ViewConsumeSummaryVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RequestMapping("/consumeManage/accountConsume")
@RestController
@Api(tags = "消费接口")
public class AccountConsumeController {
	@Resource
	AccountConsumeClient accountConsumeClient;

	@ApiOperation(value = "消费明细报表", response = AccountConsumeVO.class, responseContainer = "List")
	@ApiResponse(code = 200, message = "请求成功", response = AccountConsumeVO.class)
	@PostMapping("/listConsumeDetail")
	public WebApiReturnResultModel listConsumeDetail(
			@Validated @RequestBody ListConsumeDetailRequestDTO listConsumeDetail) {
		return accountConsumeClient.listConsumeDetail(listConsumeDetail);
	}

	@ApiOperation(value = "消费汇总报表", response = ViewConsumeSummaryVO.class, responseContainer = "List")
	@ApiResponse(code = 200, message = "请求成功", response = ViewConsumeSummaryVO.class)
	@PostMapping("/listConsumeSummary")
	public WebApiReturnResultModel listConsumeSummary(
			@Validated @RequestBody ListConsumeSummaryRequestDTO listConsumeSummary) {
		return accountConsumeClient.listConsumeSummary(listConsumeSummary);
	}

	@ApiOperation("消费明细报表导出")
	@PostMapping("/listConsumeDetailExcel")
	public WebApiReturnResultModel listConsumeDetailExcel(
			@Validated @RequestBody ListConsumeDetailExcelRequestDTO listConsumeDetailExcelRequest) {
		return accountConsumeClient.listConsumeDetailExcel(listConsumeDetailExcelRequest);
	}

	@ApiOperation("消费撤销")
	@PostMapping("/accountRevoke")
	public WebApiReturnResultModel accountRevoke(@RequestBody @Validated AccountRevokeRequestDTO accountRevokeRequest){
		return accountConsumeClient.accountRevoke(accountRevokeRequest);
	}

	@ApiOperation(value = "个人消费记录查询",response = PersonConsumeVO.class)
	@PostMapping("/personConsume")
	public WebApiReturnResultModel personConsume(@RequestBody @Validated PersonConsumeRequestDTO personConsumeRequest){
		return accountConsumeClient.personConsume(personConsumeRequest);
	}


}
