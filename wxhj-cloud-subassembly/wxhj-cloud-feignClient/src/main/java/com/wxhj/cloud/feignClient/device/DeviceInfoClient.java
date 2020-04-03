package com.wxhj.cloud.feignClient.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceInfoClientFallBack;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;



@Component
@FeignClient(name = "deviceServer",fallback=DeviceInfoClientFallBack.class)
public interface DeviceInfoClient {
	@PostMapping("/deviceInfo/listDeviceInfo")
	public WebApiReturnResultModel listDeviceInfo(
			 @RequestBody CommonPageRequestDTO commonPageRequest);
	
	@PostMapping("/deviceInfo/submitDeviceInfo")
	public WebApiReturnResultModel submitDeviceInfo(@RequestBody SubmitDeviceInfoRequestDTO deviceInfo);
	
	@PostMapping("/deviceInfo/deleteDeviceInfo")
	public WebApiReturnResultModel deleteDeviceInfo(
			 @RequestBody CommonIdRequestDTO commonIdRequest);
	
//	@PostMapping("/deviceInfo/importFileDeviceInfo")
//	public WebApiReturnResultModel importFileDeviceInfo(@RequestBody ImportFileDeviceInfoRequestDTO importFileDeviceInfoRequestDTO);
//	
//	@PostMapping("/deviceInfo/allDeviceInfo")
//	public WebApiReturnResultModel allDeviceInfo();
}
