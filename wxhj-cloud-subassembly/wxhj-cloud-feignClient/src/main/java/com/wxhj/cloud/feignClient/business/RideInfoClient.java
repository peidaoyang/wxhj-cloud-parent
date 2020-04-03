package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.RideInfoClientFallBack;
import com.wxhj.cloud.feignClient.business.request.RideInfoListByAccoutIdRequestDTO;
import com.wxhj.cloud.feignClient.business.request.RideInfoListRequestDTO;

/**
 * @ClassName: RideInfoClient.java
 * @author: cya
 * @Date: 2020年2月6日 下午1:41:46 
 */
@Component
@FeignClient(name = "businessServer",fallback=RideInfoClientFallBack.class)
public interface RideInfoClient {
	@PostMapping("/ride/rideInfoList")
	WebApiReturnResultModel rideInfoList(@RequestBody @Validated RideInfoListRequestDTO rideInfoListRequest);
	
	@PostMapping("/ride/rideInfoListByAccoutId")
	WebApiReturnResultModel rideInfoListByAccoutId(@RequestBody @Validated RideInfoListByAccoutIdRequestDTO rideInfoListByAccoutIdRequest);
}
