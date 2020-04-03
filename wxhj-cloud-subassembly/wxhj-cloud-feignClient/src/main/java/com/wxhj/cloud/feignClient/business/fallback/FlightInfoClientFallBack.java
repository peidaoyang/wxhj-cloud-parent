/**
 * 
 */
package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.FlightInfoClient;
import com.wxhj.cloud.feignClient.business.request.AppFlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.FlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitFlightRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @ClassName: FlightInfoClientFallBack.java
 * @author: cya
 * @Date: 2020年2月5日 下午6:50:11
 */
@Component
public class FlightInfoClientFallBack implements FlightInfoClient {

	@Override
	public WebApiReturnResultModel flightList(FlightListRequestDTO flightListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel flightById(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitFlight(SubmitFlightRequestDTO submitFlightRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteFlight(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel appFlightList(AppFlightListRequestDTO appFlightListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
