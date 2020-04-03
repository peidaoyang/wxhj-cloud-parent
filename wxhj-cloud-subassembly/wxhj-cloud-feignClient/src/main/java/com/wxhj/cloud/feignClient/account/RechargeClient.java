/**
 * 
 */
package com.wxhj.cloud.feignClient.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.RechargeClientFallBack;
import com.wxhj.cloud.feignClient.account.request.AppRechargeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListRechargeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RechargeExcelRequestDTO;


/**
 * @ClassName: RechargeClient.java
 * @author: cya
 * @Date: 2020年2月2日 上午11:41:54 
 */
@FeignClient(name = "accountServer",fallback=RechargeClientFallBack.class)
public interface RechargeClient {
	@PostMapping("/rechargeInfo/listRechargeInfo")
	WebApiReturnResultModel listRechargeInfo(@RequestBody @Validated ListRechargeInfoRequestDTO listRechargeInfo);
	
	@PostMapping("/rechargeInfo/rechargeExcel")
	WebApiReturnResultModel rechargeExcel(@RequestBody @Validated RechargeExcelRequestDTO rechargeExcel);
	
	@PostMapping("/rechargeInfo/appRechargeInfo")
	WebApiReturnResultModel appRechargeInfo(@RequestBody @Validated AppRechargeInfoRequestDTO appRechargeInfo);
	
//	@PostMapping("/rechargeInfo/rechargeSummary")
//	WebApiReturnResultModel rechargeSummary(@RequestBody @Validated RechargeSummaryRequestDTO rechargeSummary);
}
