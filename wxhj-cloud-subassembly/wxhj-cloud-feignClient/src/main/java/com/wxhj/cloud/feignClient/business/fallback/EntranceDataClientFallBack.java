/**
 * @className EntranceDataClientFallBack.java
 * @admin jwl
 * @date 2020年1月20日 上午10:54:11
 */
package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.feignClient.business.request.ListEntranceDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.EntranceDataClient;
import com.wxhj.cloud.feignClient.business.request.ListDetailEntranceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataExcelRequestDTO;

/**
 * @className EntranceDataClientFallBack.java
 * @admin jwl
 * @date 2020年1月20日 上午10:54:11
 */
@Component
public class EntranceDataClientFallBack implements EntranceDataClient {

	@Override
	public WebApiReturnResultModel listDetailEntranceData(ListDetailEntranceDataRequestDTO listDetaileEntranceData) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listDetailEntranceDataExcel(
			ListEntranceDataExcelRequestDTO listEntranceDataExcalRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel totayEntrance(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listEntranceDataByAccount(ListEntranceDataByAccountRequestDTO listEntranceDataByAccount) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
}
