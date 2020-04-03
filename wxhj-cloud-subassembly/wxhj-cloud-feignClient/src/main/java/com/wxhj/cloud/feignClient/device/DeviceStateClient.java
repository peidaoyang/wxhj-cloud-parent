package com.wxhj.cloud.feignClient.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceStateClientFallBack;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;


@Component
@FeignClient(name = "deviceServer",fallback=DeviceStateClientFallBack.class)
public interface DeviceStateClient {
	
	@PostMapping("/deviceState/listDeviceState")
	public WebApiReturnResultModel listDeviceState(@RequestBody CommonListPageRequestDTO commonListPageRequest);
}
