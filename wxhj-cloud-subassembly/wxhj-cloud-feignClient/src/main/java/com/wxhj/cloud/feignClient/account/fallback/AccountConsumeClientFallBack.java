package com.wxhj.cloud.feignClient.account.fallback;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AccountConsumeClient;
import com.wxhj.cloud.feignClient.account.request.AppConsumeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AppConsumeInfoSummaryRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeDetailExcelRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeDetailRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeSummaryRequestDTO;

@Component
public class AccountConsumeClientFallBack implements AccountConsumeClient {

	@Override
	public WebApiReturnResultModel listConsumeDetail(ListConsumeDetailRequestDTO listConsumeDetail) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel appConsumeInfo(@RequestBody @Validated AppConsumeInfoRequestDTO appConsumeInfo) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listConsumeSummary(ListConsumeSummaryRequestDTO listConsumeSummary) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listConsumeDetailExcel(
			ListConsumeDetailExcelRequestDTO listConsumeDetailExcelRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel appConsumeInfoSummary(AppConsumeInfoSummaryRequestDTO appConsumeSummary) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
