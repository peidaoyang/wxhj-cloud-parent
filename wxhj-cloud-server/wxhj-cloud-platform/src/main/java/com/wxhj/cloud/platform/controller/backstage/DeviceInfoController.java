package com.wxhj.cloud.platform.controller.backstage;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceInfoClient;
import com.wxhj.cloud.feignClient.device.DeviceParameterClient;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/device/deviceInfo")
@Api(tags = "设备信息接口")
public class DeviceInfoController {

	@Resource
	DeviceInfoClient deviceInfoClient;
	@Resource
	DeviceParameterClient deviceParameterClient;
	
	@ApiOperation("查询设备信息")
	@PostMapping("/listDeviceInfo")
	@LcnTransaction
	public WebApiReturnResultModel listDeviceInfo(@Validated @RequestBody CommonPageRequestDTO commonPageRequest) {
		return deviceInfoClient.listDeviceInfo(commonPageRequest);
	}
	
	@ApiOperation("编辑设备信息")
	@PostMapping("/submitDeviceInfo")
	@LcnTransaction
	public WebApiReturnResultModel submitDeviceInfo(@Validated @RequestBody SubmitDeviceInfoRequestDTO deviceInfo) {
		return deviceInfoClient.submitDeviceInfo(deviceInfo);
	}

	@ApiOperation("删除设备信息")
	@PostMapping("/deleteDeviceInfo")
	@LcnTransaction
	public WebApiReturnResultModel deleteDeviceInfo(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return deviceInfoClient.deleteDeviceInfo(commonIdRequest);
	}

//	@ApiOperation("通过文件新增设备信息")
//	@PostMapping("/importFileDeviceInfo")
//	@LcnTransaction
//	public WebApiReturnResultModel importFileDeviceInfo(
//			@RequestBody @Validated ImportFileDeviceInfoRequestDTO importFileDeviceInfoRequestDTO) {
//		return deviceInfoClient.importFileDeviceInfo(importFileDeviceInfoRequestDTO);
//	}
//
//	@ApiOperation("获取所有的设备信息")
//	@PostMapping("/listDeviceByOrganizeId")
//	@LcnTransaction
//	public WebApiReturnResultModel listDeviceByOrganizeId(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
//		return deviceParameterClient.listDeviceByOrganizeId(commonOrganizeRequest);
//	}
}
