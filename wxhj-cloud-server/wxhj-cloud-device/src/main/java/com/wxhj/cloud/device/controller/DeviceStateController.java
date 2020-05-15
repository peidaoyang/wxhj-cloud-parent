package com.wxhj.cloud.device.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.device.service.ViewDeviceStateTotalService;
import com.wxhj.cloud.feignClient.account.FaceChangeClient;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.vo.FaceChangeVO;
import com.wxhj.cloud.feignClient.device.request.ListDeviceStateRequestDTO;
import com.wxhj.cloud.feignClient.device.response.DeviceStateTotalResponseDTO;
import com.wxhj.cloud.feignClient.device.response.DeviceTypeTotalVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.github.dozermapper.core.Mapper;
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
	FaceChangeClient faceChangeClient;
	@Resource
	DeviceStateService deviceStateService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	ViewDeviceStateTotalService viewDeviceStateTotalService;
	@Resource
	MapperClient mapperClient;

//	@ApiOperation("增加设备状态")
//	@PostMapping("/insertDeviceState")
//	public WebApiReturnResultModel insertDeviceState(
//			@Validated @RequestBody InsertDeviceStateRequestDTO insertDeviceStateRequestDTO) {
//		DeviceStateDO deviceState = dozerBeanMapper.map(insertDeviceStateRequestDTO, DeviceStateDO.class);
//		if (Strings.isNullOrEmpty(deviceState.getDeviceId())) {
//			deviceStateService.insert(deviceState);
//		}
//		return WebApiReturnResultModel.ofSuccess();
//	}

	@ApiOperation("查询设备状态")
	@PostMapping("/listDeviceState")
	@Override
	public WebApiReturnResultModel listDeviceState(
			@Validated @RequestBody ListDeviceStateRequestDTO listDeviceStateRequest) {
		PageInfo<DeviceStateDO> deviceStatePageList = deviceStateService.listPage(listDeviceStateRequest,
				listDeviceStateRequest.getOrganizeId(),listDeviceStateRequest.getType());

		List<DeviceStateVO> deviceStateList = deviceStatePageList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, DeviceStateVO.class)).collect(Collectors.toList());
		try {
			deviceStateList = (List<DeviceStateVO>) accessedRemotelyService.accessedOrganizeSceneList(deviceStateList);
			List<String> sceneIdList = deviceStateList.stream().map(q-> q.getSceneId()).collect(Collectors.toList());
			if(sceneIdList.size()>0){
				WebApiReturnResultModel returnResultModel = faceChangeClient.listFaceChange(new CommonIdListRequestDTO(sceneIdList));
				List<FaceChangeVO> faceChangeVOList = FeignUtil.formatArrayClass(returnResultModel,FaceChangeVO.class);
				deviceStateList.forEach(q -> {
					faceChangeVOList.forEach(p-> {
						if(p.getId().equals(q.getSceneId())){
							q.setNeedDownPeople(p.getNeedDownPeople());
							q.setFaceChangeMaxIndex(p.getMaxIndex());
						}
					});
				});
			}
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(deviceStatePageList, deviceStateList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("设备状态统计")
	@PostMapping("/deviceStateTotal")
	@Override
	public WebApiReturnResultModel deviceStateTotal(@Validated @RequestBody CommonIdRequestDTO commonIdRequest){
		int total = deviceStateService.countDevice(commonIdRequest.getId());
		int onLineTotal = deviceStateService.countGreaterThanLastTime(DateUtil.growDateMinute(new Date(),-30),commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess(new DeviceStateTotalResponseDTO(total,onLineTotal,total-onLineTotal));
	}

	@ApiOperation("设备类型 统计")
	@PostMapping("/deviceTypeTotal")
	@Override
	public WebApiReturnResultModel deviceTypeTotal(@Validated @RequestBody CommonIdRequestDTO commonIdRequest){
		List<DeviceTypeTotalVO> voList = viewDeviceStateTotalService.select(commonIdRequest.getId()).stream().map(q-> dozerBeanMapper.map(q,DeviceTypeTotalVO.class)).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(voList);
	}
}
