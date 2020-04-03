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
import com.wxhj.cloud.device.domain.DeviceStateDO;
import com.wxhj.cloud.device.dto.request.InsertDeviceStateRequestDTO;
import com.wxhj.cloud.device.service.DeviceStateService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.DeviceStateClient;
import com.wxhj.cloud.feignClient.device.vo.DeviceStateVO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/deviceState")
public class DeviceStateController implements DeviceStateClient {

	@Resource
	DeviceStateService deviceStateService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	DozerBeanMapper dozerBeanMapper;

	@ApiOperation("增加设备状态")
	@PostMapping("/insertDeviceState")
	public WebApiReturnResultModel insertDeviceState(
			@Validated @RequestBody InsertDeviceStateRequestDTO insertDeviceStateRequestDTO) {
		DeviceStateDO deviceState = dozerBeanMapper.map(insertDeviceStateRequestDTO, DeviceStateDO.class);
		if (Strings.isNullOrEmpty(deviceState.getDeviceId())) {
			deviceStateService.insert(deviceState);
		}
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation("查询设备状态")
	@PostMapping("/listDeviceState")
	@Override
	public WebApiReturnResultModel listDeviceState(
			@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		PageInfo<DeviceStateDO> deviceStatePageList = deviceStateService.listPage(commonListPageRequest,
				commonListPageRequest.getOrganizeId());

		List<DeviceStateVO> deviceStateList = deviceStatePageList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, DeviceStateVO.class)).collect(Collectors.toList());

		try {
			deviceStateList = (List<DeviceStateVO>) accessedRemotelyService.accessedOrganizeSceneList(deviceStateList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(deviceStatePageList, deviceStateList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

}
