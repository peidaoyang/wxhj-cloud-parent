package com.wxhj.cloud.platform.controller.device;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceParameterClient;
import com.wxhj.cloud.feignClient.device.request.ListDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.DeviceParameterVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/device/deviceParameter")
@Api(tags="设备参数接口")
public class DeviceParameterController {
	@Resource
	DeviceParameterClient deviceParameterClient;
	
	
	@ApiOperation(value="按条件查询设备参数信息",response = DeviceParameterVO.class)
	@PostMapping("/listDeviceParameter")
	@LcnTransaction
	public WebApiReturnResultModel listDeviceParameter(
			@Validated @RequestBody ListDeviceParameterRequestDTO listDeviceParameter) {
		return deviceParameterClient.listDeviceParameter(listDeviceParameter);
	}
	
	@ApiOperation("编辑设备参数信息")
	@PostMapping("/submitDevieParameter")
	@LcnTransaction
	public WebApiReturnResultModel submitDeviceParameter(
			@Validated @RequestBody SubmitDeviceParameterRequestDTO deviceParameterRequestDTO) {
		return deviceParameterClient.submitDeviceParameter(deviceParameterRequestDTO);
	}
	
	@ApiOperation(value="根据组织查询设备参数信息",response = DeviceParameterVO.class)
	@PostMapping("/listDeviceOrgParameter")
	@LcnTransaction
	public WebApiReturnResultModel listDeviceOrgParameter(
			@Validated @RequestBody ListDeviceParameterRequestDTO listDeviceParameter) {
		return deviceParameterClient.listDeviceOrgParameter(listDeviceParameter);
	}
	
//	@ApiOperation("删除设备参数信息")
//	@PostMapping("/deleteDevieParameter")
//	@LcnTransaction
//	public WebApiReturnResultModel deleteDeviceParameter(
//			@Validated @RequestBody DeleteDeviceParameterRequestDTO deviceId) {
//		return deviceParameterClient.deleteDeviceParameter(deviceId);
//	}
	
	
}
