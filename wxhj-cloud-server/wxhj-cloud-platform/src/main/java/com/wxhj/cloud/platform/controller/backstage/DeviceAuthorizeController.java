package com.wxhj.cloud.platform.controller.backstage;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceAuthorizeClient;
import com.wxhj.cloud.feignClient.device.request.ImportFileDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.device.request.InsertDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/backstage/deviceAuthorize")
@Api(tags="设备授权接口")
public class DeviceAuthorizeController {
	@Resource
	DeviceAuthorizeClient deviceAuthorizeClient;
	
	@ApiOperation("设备授权分页查询")
	@PostMapping("/listDeviceAuthorize")
	@LcnTransaction
	public WebApiReturnResultModel listDeviceAuthorize(
			@Validated @RequestBody CommonPageRequestDTO commonPageRequest) {
		return deviceAuthorizeClient.listDeviceAuthorizePage(commonPageRequest);
	}
	
	@ApiOperation("新增设备授权信息")
	@PostMapping("/insertDeviceAuthorize")
	@LcnTransaction
	public WebApiReturnResultModel insertDeviceAuthorize(
			@Validated @RequestBody InsertDeviceAuthorizeRequestDTO deviceAuthorizeRequest) {
		return deviceAuthorizeClient.insertDeviceAuthorize(deviceAuthorizeRequest);
	}
	
	@ApiOperation("设备授权批量添加")
	@PostMapping("/importFileDeviceAuthorize")
	@LcnTransaction
	public WebApiReturnResultModel importFileDeviceAuthorize(@RequestBody @Validated ImportFileDeviceAuthorizeRequestDTO importFileDeviceAuthorizeRequestDTO) {
		return deviceAuthorizeClient.importFileDeviceAuthorize(importFileDeviceAuthorizeRequestDTO);
	}
}
