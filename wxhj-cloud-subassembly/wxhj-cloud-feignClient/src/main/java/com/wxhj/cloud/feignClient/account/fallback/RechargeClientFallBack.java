/**
 * 
 */
package com.wxhj.cloud.feignClient.account.fallback;

import com.wxhj.cloud.feignClient.account.request.*;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.RechargeClient;


/**
 * @ClassName: RechargeClientFallBack.java
 * @author: cya
 * @Date: 2020年2月2日 上午11:50:28 
 */
@Component
public class RechargeClientFallBack implements RechargeClient{

	@Override
	public WebApiReturnResultModel listRechargeInfo(ListRechargeInfoRequestDTO listRechargeInfo) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel rechargeExcel(RechargeExcelRequestDTO rechargeExcel) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
	
	@Override
	public WebApiReturnResultModel appRechargeInfo(AppRechargeInfoRequestDTO appRechargeInfo) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel refund(RefundRequestDTO refundRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel personRecharge(PersonRechargeRequestDTO personRechargeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


//	@Override
//	public WebApiReturnResultModel rechargeSummary(RechargeSummaryRequestDTO rechargeSummary) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}
}
