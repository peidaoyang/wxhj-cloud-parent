/**
 * 
 */
package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.feignClient.account.request.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.RechargeClientFallBack;


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

	@PostMapping("/rechargeInfo/refund")
	WebApiReturnResultModel refund(@RequestBody @Validated RefundRequestDTO refundRequest);

	@PostMapping("/rechargeInfo/personRecharge")
	WebApiReturnResultModel personRecharge(@RequestBody @Validated PersonRechargeRequestDTO personRechargeRequest);

}
