/**
 * 
 */
package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.RideInfoClient;
import com.wxhj.cloud.feignClient.business.request.RideInfoListByAccoutIdRequestDTO;
import com.wxhj.cloud.feignClient.business.request.RideInfoListRequestDTO;

/**
 * @ClassName: RideInfoClientFallBack.java
 * @author: cya
 * @Date: 2020年2月6日 下午1:42:25 
 */
@Component
public class RideInfoClientFallBack implements RideInfoClient{

	@Override
	public WebApiReturnResultModel rideInfoList(RideInfoListRequestDTO rideInfoListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel rideInfoListByAccoutId(
			RideInfoListByAccoutIdRequestDTO rideInfoListByAccoutIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
	
}
