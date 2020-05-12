package com.wxhj.cloud.device.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.PlatformEnum;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.DeviceInfoDO;
import com.wxhj.cloud.device.service.DeviceInfoService;
import com.wxhj.cloud.device.service.DeviceParameterService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.DeviceInfoClient;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceInfoRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.DeviceInfoVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @className DeviceInfoController.java
 * @author jwl
 * @date 2019年11月28日 上午5:14:08
 */
@RestController
@RequestMapping("/deviceInfo")
public class DeviceInfoController implements DeviceInfoClient {
	@Resource
	DeviceInfoService deviceInfoService;
	@Resource
	DeviceParameterService deviceParameterService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@ApiOperation("查询设备信息")
	@PostMapping("/listDeviceInfo")
	@Override
	public WebApiReturnResultModel listDeviceInfo(@Validated @RequestBody CommonPageRequestDTO commonPageRequest) {
		PageInfo<DeviceInfoDO> listDevicePage = deviceInfoService.listDevicePage(commonPageRequest,
				commonPageRequest.getNameValue());
		List<DeviceInfoVO> deviceInfoList = listDevicePage.getList().stream()
				.map(q -> dozerBeanMapper.map(q, DeviceInfoVO.class)).collect(Collectors.toList());
		try {
			deviceInfoList = (List<DeviceInfoVO>) accessedRemotelyService.accessedPlatformEnumList(deviceInfoList,
					PlatformEnum.DRIVER_TYPE);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(listDevicePage, deviceInfoList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("编辑设备信息")
	@PostMapping("/submitDeviceInfo")
	@Override
	public WebApiReturnResultModel submitDeviceInfo(@Validated @RequestBody SubmitDeviceInfoRequestDTO deviceInfo) {
		DeviceInfoDO deviceInfoDO = dozerBeanMapper.map(deviceInfo, DeviceInfoDO.class);
		String id;
		if (Strings.isNullOrEmpty(deviceInfoDO.getId())) {
//<<<<<<< .mine
			id = deviceInfoService.insertCascade(deviceInfoDO);
//||||||| .r53
//			id=deviceInfoService.insert(deviceInfoDO);
//=======
//			id=deviceInfoService.insertCascade(deviceInfoDO);
//>>>>>>> .r67
		} else {
			deviceInfoService.update(deviceInfoDO);
			id = deviceInfoDO.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@ApiOperation("删除设备信息")
	@PostMapping("/deleteDeviceInfo")
	@Override
	public WebApiReturnResultModel deleteDeviceInfo(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		deviceInfoService.deleteById(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}

}
