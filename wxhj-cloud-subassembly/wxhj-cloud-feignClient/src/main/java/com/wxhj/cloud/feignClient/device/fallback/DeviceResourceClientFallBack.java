/**
 * @className DeviceResourceClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:22:54
 */
package com.wxhj.cloud.feignClient.device.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceResourceClient;
import com.wxhj.cloud.feignClient.device.request.ListDeviceResourceRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceResourceRequestDTO;

/**
 * @className DeviceResourceClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:22:54
 */
@Component
public class DeviceResourceClientFallBack implements DeviceResourceClient{

	@Override
	public WebApiReturnResultModel listDeviceResource(ListDeviceResourceRequestDTO listDeviceResource) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitDeviceResource(SubmitDeviceResourceRequestDTO submitDeviceResource) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
