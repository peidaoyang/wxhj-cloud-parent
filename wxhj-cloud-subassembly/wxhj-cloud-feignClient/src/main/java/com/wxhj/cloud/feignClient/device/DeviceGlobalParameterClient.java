package com.wxhj.cloud.feignClient.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceGlobalParameterClientFallBack;
import com.wxhj.cloud.feignClient.device.request.DlodDevGlobPraRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceGlobalParameterRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

/**
 * @ClassName: DeviceGlobalParameterClientFallBack.java
 * @author: cya
 * @Date: 2019年12月10日 下午5:43:13
 */
@Component
@FeignClient(name = "deviceServer", fallback = DeviceGlobalParameterClientFallBack.class)
public interface DeviceGlobalParameterClient {
	@PostMapping("/deviceGlobalParameter/listDeviceGlobalParameter")
	WebApiReturnResultModel listDeviceGlobalParameter(
			@RequestBody CommonListPageRequestDTO commonListPageRequest);

	@PostMapping("/deviceGlobalParameter/submitDeviceGlobalParameters")
	WebApiReturnResultModel submitDeviceGlobalParameter(
			@RequestBody SubmitDeviceGlobalParameterRequestDTO submitDeviceGlobalParameter);

	@PostMapping("/deviceGlobalParameter/deleteDeviceGlobalRarameter")
	WebApiReturnResultModel deleteDeviceGlobalRarameter(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/deviceGlobalParameter/downloadDevGlo")
	WebApiReturnResultModel downloadDevGlo(@RequestBody DlodDevGlobPraRequestDTO devGlobPra);
}
