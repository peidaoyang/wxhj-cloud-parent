/** 
 * @fileName: DeviceGlobalParameterController.java  
 * @author: pjf
 * @date: 2019年12月10日 上午11:28:11 
 */

package com.wxhj.cloud.device.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.DeviceGlobalParameterDO;
import com.wxhj.cloud.device.service.DeviceGlobalParameterService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.DeviceGlobalParameterClient;
import com.wxhj.cloud.feignClient.device.request.DlodDevGlobPraRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceGlobalParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.ListDeviceGlobalParameterVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className DeviceGlobalParameterController.java
 * @author pjf
 * @date 2019年12月10日 上午11:28:11
 */
@Api("设备全局参数")
@RestController
@RequestMapping("/deviceGlobalParameter")
public class DeviceGlobalParameterController implements DeviceGlobalParameterClient {
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	DeviceGlobalParameterService deviceGlobalParameterService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@ApiOperation("分页查询全局参数")
	@PostMapping("/listDeviceGlobalParameter")
	@Override
	public WebApiReturnResultModel listDeviceGlobalParameter(
			@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		PageInfo<DeviceGlobalParameterDO> deviceGlobalList = deviceGlobalParameterService.listDeviceGlobalParameter(
				commonListPageRequest, commonListPageRequest.getNameValue(), commonListPageRequest.getOrganizeId());

		List<ListDeviceGlobalParameterVO> deviceGlobalResponseList = deviceGlobalList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ListDeviceGlobalParameterVO.class)).collect(Collectors.toList());

		try {
			deviceGlobalResponseList = (List<ListDeviceGlobalParameterVO>) accessedRemotelyService
					.accessedOrganizeSceneList(deviceGlobalResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(deviceGlobalList, deviceGlobalResponseList, new PageDefResponseModel());

		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("编辑全局参数")
	@PostMapping("/submitDeviceGlobalParameters")
	@Override
	public WebApiReturnResultModel submitDeviceGlobalParameter(
			@Validated @RequestBody SubmitDeviceGlobalParameterRequestDTO submitDeviceGlobalParameter) {
		DeviceGlobalParameterDO deviceGlobalParmeter = dozerBeanMapper.map(submitDeviceGlobalParameter,
				DeviceGlobalParameterDO.class);
		deviceGlobalParmeter.setTimeStamp(System.currentTimeMillis()/1000);
		String id = null;
		if (Strings.isNullOrEmpty(deviceGlobalParmeter.getId())) {
			id = deviceGlobalParameterService.insert(deviceGlobalParmeter);
		} else {
			id = deviceGlobalParmeter.getId();
			deviceGlobalParameterService.updateCascade(deviceGlobalParmeter);
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@ApiOperation("删除全局参数")
	@PostMapping("/deleteDeviceGlobalRarameter")
	@Override
	public WebApiReturnResultModel deleteDeviceGlobalRarameter(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		deviceGlobalParameterService.deleteCascade(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation("全局参数下载")
	@PostMapping("/downloadDevGlo")
	@Override
	public WebApiReturnResultModel downloadDevGlo(@Validated @RequestBody DlodDevGlobPraRequestDTO devGlobPra) {
		DeviceGlobalParameterDO deviceGlobalParmeter = dozerBeanMapper.map(devGlobPra, DeviceGlobalParameterDO.class);
		deviceGlobalParmeter.setTimeStamp(System.currentTimeMillis());
		deviceGlobalParameterService.insert(deviceGlobalParmeter);
		return WebApiReturnResultModel.ofSuccess();
	}

}
