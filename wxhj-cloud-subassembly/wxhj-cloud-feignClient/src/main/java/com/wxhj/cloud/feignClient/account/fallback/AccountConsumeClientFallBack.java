package com.wxhj.cloud.feignClient.account.fallback;

import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AccountConsumeClient;

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

	@Override
	public WebApiReturnResultModel todayConsume(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountRevoke(AccountRevokeRequestDTO accountRevokeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel personConsume(PersonConsumeRequestDTO personConsumeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
