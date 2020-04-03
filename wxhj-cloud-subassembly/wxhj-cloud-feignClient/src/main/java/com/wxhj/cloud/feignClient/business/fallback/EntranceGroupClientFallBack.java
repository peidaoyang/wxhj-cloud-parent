/**
 * @className EntranceGroupClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:04:55
 */
package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.EntranceGroupClient;
import com.wxhj.cloud.feignClient.business.request.ListEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @className EntranceGroupClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:04:55
 */
@Component
public class EntranceGroupClientFallBack implements EntranceGroupClient {

	@Override
	public WebApiReturnResultModel submitEntranceGroup(SubmitEntranceGroupRequestDTO submitEntranceGroupRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listEntranceGroup(ListEntranceGroupRequestDTO listEntranceGroup) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteEntranceGroup(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel selectEntranceGroup(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel importMapEntrAuth(CommonIdRequestDTO commonIdRequest) {
		// TODO Auto-generated method stub
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel listEntranceOrganizeId(CommonOrganizeRequestDTO commonOrganizeRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

}
