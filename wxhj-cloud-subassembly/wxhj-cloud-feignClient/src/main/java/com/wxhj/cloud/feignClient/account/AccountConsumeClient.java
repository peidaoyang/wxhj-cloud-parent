package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.AccountConsumeClientFallBack;
import com.wxhj.cloud.feignClient.account.request.AppConsumeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AppConsumeInfoSummaryRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeDetailExcelRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeDetailRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeSummaryRequestDTO;

@FeignClient(name = "accountServer", fallback = AccountConsumeClientFallBack.class)
public interface AccountConsumeClient {

	@PostMapping("/accountConsume/listConsumeDetail")
	public WebApiReturnResultModel listConsumeDetail(@RequestBody ListConsumeDetailRequestDTO listConsumeDetail);

	@PostMapping("/accountConsume/listConsumeSummary")
	public WebApiReturnResultModel listConsumeSummary(@RequestBody ListConsumeSummaryRequestDTO listConsumeSummary);

	@PostMapping("/accountConsume/listConsumeDetailExcel")
	public WebApiReturnResultModel listConsumeDetailExcel(
			@RequestBody ListConsumeDetailExcelRequestDTO listConsumeDetailExcelRequest);

	@PostMapping("/accountConsume/appConsumeInfo")
	WebApiReturnResultModel appConsumeInfo(@RequestBody AppConsumeInfoRequestDTO appConsumeInfo);
	
	@PostMapping("/accountConsume/appConsumeInfoSummary")
	WebApiReturnResultModel appConsumeInfoSummary(@RequestBody AppConsumeInfoSummaryRequestDTO appConsumeSummary);

	@PostMapping("/accountConsume/todayConsume")
	WebApiReturnResultModel todayConsume(@RequestBody @Validated CommonIdRequestDTO commonIdRequest);

}
