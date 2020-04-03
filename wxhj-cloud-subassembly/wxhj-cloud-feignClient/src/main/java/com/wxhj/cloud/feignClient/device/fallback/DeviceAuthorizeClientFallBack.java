/**
 * @className DeviceAuthorizeClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:09:35
 */
package com.wxhj.cloud.feignClient.device.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceAuthorizeClient;
import com.wxhj.cloud.feignClient.device.request.ImportFileDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.device.request.InsertDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;

/**
 * @className DeviceAuthorizeClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:09:35
 */
@Component
public class DeviceAuthorizeClientFallBack implements DeviceAuthorizeClient{

	@Override
	public WebApiReturnResultModel insertDeviceAuthorize(InsertDeviceAuthorizeRequestDTO deviceAuthorizeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listDeviceAuthorizePage(CommonPageRequestDTO commonPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel importFileDeviceAuthorize(
			ImportFileDeviceAuthorizeRequestDTO importFileDeviceAuthorizeRequestDTO) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
	
}
