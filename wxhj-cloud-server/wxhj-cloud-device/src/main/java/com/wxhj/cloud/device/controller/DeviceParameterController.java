package com.wxhj.cloud.device.controller;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.core.statics.LocalDateTimeStaticClass;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.DeviceParameterDO;
import com.wxhj.cloud.device.service.DeviceParameterService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.DeviceParameterClient;
//import com.wxhj.cloud.feignClient.device.request.DeleteDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.request.ListDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceParameterRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.DeviceParameterVO;

import io.swagger.annotations.ApiOperation;

/**
 * @className DeviceParameterController.java
 * @author jwl
 * @date 2019年12月2日 上午8:42:54
 */

@RestController
@RequestMapping("/deviceParameter")
public class DeviceParameterController implements DeviceParameterClient {
	@Resource
	DeviceParameterService deviceParameterService;

	@Resource
	Mapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@ApiOperation("按条件查询设备参数信息")
	@PostMapping("/listDeviceParameter")
	@Override
	public WebApiReturnResultModel listDeviceParameter(
			@Validated @RequestBody ListDeviceParameterRequestDTO listDeviceParameter) {
		PageInfo<DeviceParameterDO> listDevice = deviceParameterService.listPage(listDeviceParameter,
				listDeviceParameter.getNameValue(), listDeviceParameter.getOrganizeId(), listDeviceParameter.getType());

		List<DeviceParameterVO> deviceParameterList = listDevice.getList().stream()
				.map(q -> dozerBeanMapper.map(q, DeviceParameterVO.class)).collect(Collectors.toList());
		
		try {
			deviceParameterList = (List<DeviceParameterVO>) accessedRemotelyService
					.accessedOrganizeSceneList(deviceParameterList);
			if(listDeviceParameter.getType().equals("sceneName")){
				deviceParameterList = deviceParameterList.stream().filter(q -> q.getSceneName().contains(listDeviceParameter.getNameValue())).collect(Collectors.toList());
			}
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listDevice,
				deviceParameterList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);

	}

	@ApiOperation("按条件查询设备参数信息")
	@PostMapping("/listDeviceOrgParameter")
	@Override
	public WebApiReturnResultModel listDeviceOrgParameter(
			@Validated @RequestBody ListDeviceParameterRequestDTO listDeviceParameter) {
		PageInfo<DeviceParameterDO> listDevice = deviceParameterService.listPage(listDeviceParameter,
				listDeviceParameter.getNameValue(), listDeviceParameter.getOrganizeId(), listDeviceParameter.getType());

		List<DeviceParameterVO> deviceParameterList = listDevice.getList().stream()
				.map(q -> dozerBeanMapper.map(q, DeviceParameterVO.class)).collect(Collectors.toList());

		try {
			deviceParameterList = (List<DeviceParameterVO>) accessedRemotelyService
					.accessedOrganizeSceneList(deviceParameterList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listDevice,
				deviceParameterList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);

	}

	@ApiOperation("编辑设备参数信息")
	@PostMapping("/submitDevieParameter")
	@Override
	public WebApiReturnResultModel submitDeviceParameter(
			@Validated @RequestBody SubmitDeviceParameterRequestDTO deviceParameterRequest) {
		DeviceParameterDO deviceParameter = dozerBeanMapper.map(deviceParameterRequest, DeviceParameterDO.class);
		deviceParameter.setParameterVersion(LocalDateTimeStaticClass.getTimestamp());
		//new Date().getTime() / 1000
		deviceParameterService.update(deviceParameter);
		return WebApiReturnResultModel.ofSuccess();
	}

//	@ApiOperation("删除设备参数信息")
//	@PostMapping("/deleteDevieParameter")
//	@Override
//	public WebApiReturnResultModel deleteDeviceParameter(
//			@Validated @RequestBody DeleteDeviceParameterRequestDTO deleteDeviceParameterRequest) {
//		deviceParameterService.delete(deleteDeviceParameterRequest.getDeviceParameterId());
//		return WebApiReturnResultModel.ofSuccess();
//	}
//
//
//	
//预留
//	@PostMapping("/listDeviceByIdList")
//	@Override
//	public WebApiReturnResultModel listDeviceByIdList(
//			@RequestBody ListDeviceByIdListRequestDTO listDeviceByIdListRequest) {
//		List<DeviceParameterDO> listByDeviceId = deviceParameterService
//				.listByDeviceId(listDeviceByIdListRequest.getDeviceIdList());
//		return WebApiReturnResultModel.ofSuccess(listByDeviceId);
//	}
//
//	@PostMapping("/listDeviceByOrganizeId")
//	@Override
//	public WebApiReturnResultModel listDeviceByOrganizeId(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
//		List<DeviceParameterDO> listByDeviceId = deviceParameterService
//				.listByOrganizeId(commonOrganizeRequest.getOrganizeId());
//		return WebApiReturnResultModel.ofSuccess(listByDeviceId);
//	}

}
