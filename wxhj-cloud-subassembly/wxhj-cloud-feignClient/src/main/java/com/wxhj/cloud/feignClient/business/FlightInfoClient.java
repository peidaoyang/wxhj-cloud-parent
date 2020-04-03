/**
 * 
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.FlightInfoClientFallBack;
import com.wxhj.cloud.feignClient.business.request.AppFlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.FlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitFlightRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @ClassName: FlightInfoClient.java
 * @author: cya
 * @Date: 2020年2月5日 下午6:17:16
 */
@Component
@FeignClient(name = "businessServer", fallback = FlightInfoClientFallBack.class)
public interface FlightInfoClient {
	@PostMapping("/flight/flightList")
	WebApiReturnResultModel flightList(@RequestBody FlightListRequestDTO flightListRequest);

	@PostMapping("/flight/flightById")
	WebApiReturnResultModel flightById(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/flight/submitFlight")
	WebApiReturnResultModel submitFlight(@RequestBody SubmitFlightRequestDTO submitFlightRequest);

	@PostMapping("/flight/deleteFlight")
	WebApiReturnResultModel deleteFlight(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/flight/appFlightList")
	WebApiReturnResultModel appFlightList(@RequestBody @Validated AppFlightListRequestDTO appFlightListRequest);
}
