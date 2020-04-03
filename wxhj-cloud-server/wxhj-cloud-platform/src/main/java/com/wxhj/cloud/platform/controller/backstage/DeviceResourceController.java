/**
 * @className DeviceResourceController.java
 * @admin jwl
 * @date 2020年1月7日 下午4:10:31
 */
package com.wxhj.cloud.platform.controller.backstage;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceResourceClient;
import com.wxhj.cloud.feignClient.device.request.ListDeviceResourceRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceResourceRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.ViewDeviceResourceVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className DeviceResourceController.java
 * @admin jwl
 * @date 2020年1月7日 下午4:10:31
 */
@RestController
@RequestMapping("/backstage/deviceResource")
@Api(tags="设备资源")
public class DeviceResourceController {
	@Resource
	DeviceResourceClient deviceResourceClient;
	
	@PostMapping("/listDeviceResource")
	@ApiOperation(value = "查询设备资源",response = ViewDeviceResourceVO.class)
	@LcnTransaction
	public WebApiReturnResultModel listDeviceResource(
			@RequestBody ListDeviceResourceRequestDTO listDeviceResource) {
		return deviceResourceClient.listDeviceResource(listDeviceResource);
	}
	
	@PostMapping("/submitDeviceResource")
	@ApiOperation("编辑设备资源")
	@LcnTransaction
	public WebApiReturnResultModel submitDeviceResource(
			@RequestBody SubmitDeviceResourceRequestDTO submitDeviceResource) {
		return deviceResourceClient.submitDeviceResource(submitDeviceResource);
	}
}
