/**
 * 
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.DriverInfoClientFallBack;
import com.wxhj.cloud.feignClient.business.request.DriverListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitDriverInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @ClassName: DriverInfoClient.java
 * @author: cya
 * @Date: 2020年2月4日 下午6:31:06
 */
@Component
@FeignClient(name = "businessServer", fallback = DriverInfoClientFallBack.class)
public interface DriverInfoClient {

	@PostMapping("/driver/driverList")
	WebApiReturnResultModel driverList(@RequestBody DriverListRequestDTO driverList);

	@PostMapping("/driver/submitDriverInfo")
	WebApiReturnResultModel submitDriverInfo(@RequestBody SubmitDriverInfoRequestDTO submitDriverInfo);

	@PostMapping("/driver/deleteDriver")
	WebApiReturnResultModel deleteDriver(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/driver/driverInfoById")
	WebApiReturnResultModel driverInfoById(@RequestBody CommonIdRequestDTO commonIdRequest);
}
