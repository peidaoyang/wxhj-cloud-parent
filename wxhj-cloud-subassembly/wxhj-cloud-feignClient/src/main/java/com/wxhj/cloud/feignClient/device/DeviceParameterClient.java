package com.wxhj.cloud.feignClient.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceParameterClientFallBack;
//import com.wxhj.cloud.feignClient.device.request.DeleteDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.request.ListDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceParameterRequestDTO;

@Component
@FeignClient(name = "deviceServer", fallback = DeviceParameterClientFallBack.class)
public interface DeviceParameterClient {
	@PostMapping("/deviceParameter/listDeviceParameter")
	WebApiReturnResultModel listDeviceParameter(@RequestBody ListDeviceParameterRequestDTO listDeviceParameter);

	@PostMapping("/deviceParameter/submitDevieParameter")
	WebApiReturnResultModel submitDeviceParameter(@RequestBody SubmitDeviceParameterRequestDTO deviceParameterRequest);
	
	@PostMapping("/deviceParameter/listDeviceOrgParameter")
	WebApiReturnResultModel listDeviceOrgParameter(@RequestBody ListDeviceParameterRequestDTO listDeviceParameter);

	
//	@PostMapping("/deviceParameter/deleteDevieParameter")
//	WebApiReturnResultModel deleteDeviceParameter(@RequestBody DeleteDeviceParameterRequestDTO deviceId);

//预留
//	@PostMapping("/deviceParameter/listDeviceByIdList")
//	WebApiReturnResultModel listDeviceByIdList(@RequestBody ListDeviceByIdListRequestDTO listDeviceByIdListRequest);
//
//	@PostMapping("/deviceParameter/listDeviceByOrganizeId")
//	WebApiReturnResultModel listDeviceByOrganizeId(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest);
}
