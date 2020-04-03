package com.wxhj.cloud.feignClient.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceAuthorizeClientFallBack;
import com.wxhj.cloud.feignClient.device.request.ImportFileDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.device.request.InsertDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;


@Component
@FeignClient(name = "deviceServer",fallback=DeviceAuthorizeClientFallBack.class)
public interface DeviceAuthorizeClient {
	
	@PostMapping("/deviceAuthorize/insertDeviceAuthorize")
	public WebApiReturnResultModel insertDeviceAuthorize(
			@RequestBody InsertDeviceAuthorizeRequestDTO deviceAuthorizeRequest);
	
	@PostMapping("/deviceAuthorize/listDeviceAuthorizePage")
	public WebApiReturnResultModel listDeviceAuthorizePage(
			@RequestBody CommonPageRequestDTO commonPageRequest);

	@PostMapping("/deviceAuthorize/importFileDeviceAuthorize")
	public WebApiReturnResultModel importFileDeviceAuthorize(@RequestBody @Validated ImportFileDeviceAuthorizeRequestDTO importFileDeviceAuthorizeRequestDTO);
}
