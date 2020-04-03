/**
 * @className DeviceInfoClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:17:37
 */
package com.wxhj.cloud.feignClient.device.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceInfoClient;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;

/**
 * @className DeviceInfoClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:17:37
 */
@Component
public class DeviceInfoClientFallBack implements DeviceInfoClient {

	@Override
	public WebApiReturnResultModel submitDeviceInfo(SubmitDeviceInfoRequestDTO deviceInfo) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteDeviceInfo(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listDeviceInfo(CommonPageRequestDTO commonPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel importFileDeviceInfo(ImportFileDeviceInfoRequestDTO importFileDeviceInfoRequestDTO) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

}
