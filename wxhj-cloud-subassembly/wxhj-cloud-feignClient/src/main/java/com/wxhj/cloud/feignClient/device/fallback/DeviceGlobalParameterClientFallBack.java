/**
 * @className DeviceGlobalParameterClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:13:09
 */
package com.wxhj.cloud.feignClient.device.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceGlobalParameterClient;
import com.wxhj.cloud.feignClient.device.request.DlodDevGlobPraRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceGlobalParameterRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

/**
 * @className DeviceGlobalParameterClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:13:09
 */
@Component
public class DeviceGlobalParameterClientFallBack implements DeviceGlobalParameterClient {

	@Override
	public WebApiReturnResultModel listDeviceGlobalParameter(
			CommonListPageRequestDTO commonListPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitDeviceGlobalParameter(
			SubmitDeviceGlobalParameterRequestDTO submitDeviceGlobalParameter) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteDeviceGlobalRarameter(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel downloadDevGlo(DlodDevGlobPraRequestDTO devGlobPra) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
