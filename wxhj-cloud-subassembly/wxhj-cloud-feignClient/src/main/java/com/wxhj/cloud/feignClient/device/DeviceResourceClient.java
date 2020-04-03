/**
 * @className DeviceResourceClient.java
 * @admin jwl
 * @date 2020年1月7日 下午4:08:10
 */
package com.wxhj.cloud.feignClient.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceResourceClientFallBack;
import com.wxhj.cloud.feignClient.device.request.ListDeviceResourceRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceResourceRequestDTO;


/**
 * @className DeviceResourceClient.java
 * @admin jwl
 * @date 2020年1月7日 下午4:08:10
 */
@Component
@FeignClient(name = "deviceServer",fallback=DeviceResourceClientFallBack.class)
public interface DeviceResourceClient {
	@PostMapping("/deviceResource/listDeviceResource")
	WebApiReturnResultModel listDeviceResource(
			@RequestBody ListDeviceResourceRequestDTO listDeviceResource);
	
	@PostMapping("/deviceResource/submitDeviceResource")
	WebApiReturnResultModel submitDeviceResource(
			@RequestBody SubmitDeviceResourceRequestDTO submitDeviceResource);
}
