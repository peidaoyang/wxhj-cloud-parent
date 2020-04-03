package com.wxhj.cloud.platform.controller.device;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceStateClient;
import com.wxhj.cloud.feignClient.device.vo.DeviceStateVO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/device/deviceState")
@Api(tags="设备状态接口")
public class DeviceStateController {
	@Resource
	DeviceStateClient deviceStateClient;
	
	
	@ApiOperation(value="查询设备状态",response = DeviceStateVO.class)
	@PostMapping("/listDeviceState")
	@LcnTransaction
	public WebApiReturnResultModel listDeviceState(
			@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		return deviceStateClient.listDeviceState(commonListPageRequest);
	}
}
