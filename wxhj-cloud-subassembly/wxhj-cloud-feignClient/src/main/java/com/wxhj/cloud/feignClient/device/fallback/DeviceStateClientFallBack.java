/**
 * @className DeviceStateClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:24:05
 */
package com.wxhj.cloud.feignClient.device.fallback;

import com.wxhj.cloud.feignClient.device.request.ListDeviceStateRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceStateClient;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

/**
 * @className DeviceStateClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:24:05
 */
@Component
public class DeviceStateClientFallBack implements DeviceStateClient{

	@Override
	public WebApiReturnResultModel listDeviceState(ListDeviceStateRequestDTO listDeviceStateRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deviceStateTotal(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deviceTypeTotal(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
