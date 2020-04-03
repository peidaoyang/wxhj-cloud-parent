/**
 * 
 */
package com.wxhj.cloud.platform.controller.backstage;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceGlobalParameterClient;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceGlobalParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.ListDeviceGlobalParameterVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: DeviceGlobalParameterController.java
 * @author: cya
 * @Date: 2020年1月14日 上午8:46:14
 */
@RestController
@RequestMapping("/device/deviceGlobalParameter")
@Api(tags = "设备全局参数接口")
public class DeviceGlobalParameterController {
	@Resource
	DeviceGlobalParameterClient deviceGlobalParameterClient;

	@ApiOperation(value="分页查询全局参数",response = ListDeviceGlobalParameterVO.class)
	@PostMapping("/listDeviceGlobalParameter")
	@LcnTransaction
	public WebApiReturnResultModel listDeviceGlobalParameter(
			@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		return deviceGlobalParameterClient.listDeviceGlobalParameter(commonListPageRequest);
	}

	@ApiOperation("编辑全局参数")
	@PostMapping("/submitDeviceGlobalParameters")
	@LcnTransaction
	public WebApiReturnResultModel submitDeviceGlobalParameter(
			@Validated @RequestBody SubmitDeviceGlobalParameterRequestDTO submitDeviceGlobalParameter) {
		return deviceGlobalParameterClient.submitDeviceGlobalParameter(submitDeviceGlobalParameter);
	}

	@ApiOperation("删除全局参数")
	@PostMapping("/deleteDeviceGlobalRarameter")
	@LcnTransaction
	public WebApiReturnResultModel deleteDeviceGlobalRarameter(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return deviceGlobalParameterClient.deleteDeviceGlobalRarameter(commonIdRequest);
	}
}
