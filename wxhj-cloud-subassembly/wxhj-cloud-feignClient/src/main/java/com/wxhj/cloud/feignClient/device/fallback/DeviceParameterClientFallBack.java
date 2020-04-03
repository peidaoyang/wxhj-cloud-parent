/**
 * @className DeviceParameterClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:20:32
 */
package com.wxhj.cloud.feignClient.device.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceParameterClient;
//import com.wxhj.cloud.feignClient.device.request.DeleteDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.request.ListDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceParameterRequestDTO;

/**
 * @className DeviceParameterClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:20:32
 */
@Component
public class DeviceParameterClientFallBack implements DeviceParameterClient {
	
	@Override
	public WebApiReturnResultModel listDeviceParameter(ListDeviceParameterRequestDTO listDeviceParameter) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
	
	@Override
	public WebApiReturnResultModel submitDeviceParameter(SubmitDeviceParameterRequestDTO deviceParameterRequestDTO) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listDeviceOrgParameter(ListDeviceParameterRequestDTO listDeviceParameter) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
	

//	@Override
//	public WebApiReturnResultModel deleteDeviceParameter(DeleteDeviceParameterRequestDTO deviceId) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}


	//预留
//	@Override
//	public WebApiReturnResultModel listDeviceByIdList(ListDeviceByIdListRequestDTO listDeviceByIdListRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}
//
//	@Override
//	public WebApiReturnResultModel listDeviceByOrganizeId(CommonOrganizeRequestDTO commonOrganizeRequest) {
//		// TODO Auto-generated method stub
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//
//	}

}
