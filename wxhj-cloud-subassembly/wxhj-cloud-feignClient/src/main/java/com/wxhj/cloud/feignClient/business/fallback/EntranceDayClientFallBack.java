/**
 * @className EntranceDayClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:01:16
 */
package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.EntranceDayClient;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceDayRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

/**
 * @className EntranceDayClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:01:16
 */
@Component
public class EntranceDayClientFallBack implements EntranceDayClient {

	@Override
	public WebApiReturnResultModel submitEntranceDay(SubmitEntranceDayRequestDTO submitEntranceDay) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listEntranceDay(CommonListPageRequestDTO commonListPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteEntranceDay(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel selectEntranceDayById(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listEntranceDayOrganizeId(CommonOrganizeRequestDTO commonOrganizeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
