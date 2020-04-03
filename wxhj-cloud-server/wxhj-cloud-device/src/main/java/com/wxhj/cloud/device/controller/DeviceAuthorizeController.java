package com.wxhj.cloud.device.controller;

import java.util.Calendar;
import java.util.Date;
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
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.file.IFileAnalysis;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.DeviceAuthorizeDO;
import com.wxhj.cloud.device.service.DeviceAuthorizeService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.DeviceAuthorizeClient;
import com.wxhj.cloud.feignClient.device.request.ImportFileDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.device.request.InsertDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.DeviceAuthorizeVO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className DeviceAuthorizeController.java
 * @author jwl
 * @date 2019年11月29日 上午午9:47:13
 */
@Api("设备授权编号管理")
@RestController
@RequestMapping("/deviceAuthorize")
public class DeviceAuthorizeController implements DeviceAuthorizeClient {
	@Resource
	DeviceAuthorizeService deviceAuthorizeService;

	@Resource
	FileStorageService fileStorageService;

	@Resource
	DozerBeanMapper dozerBeanMapper;

	@Resource(name = "deviceAuthorizeFileAnalysis")
	IFileAnalysis<DeviceAuthorizeDO> deviceAuthorizeFileAnalysis;

	@ApiOperation("添加授权信息")
	@PostMapping("/insertDeviceAuthorize")
	@Override
	public WebApiReturnResultModel insertDeviceAuthorize(
			@Validated @RequestBody InsertDeviceAuthorizeRequestDTO deviceAuthorizeRequest) {
		DeviceAuthorizeDO deviceAuthorize = dozerBeanMapper.map(deviceAuthorizeRequest, DeviceAuthorizeDO.class);
		Date date = new Date();
		deviceAuthorize.setCreatorTime(date);
		if (deviceAuthorizeRequest.getValidTime() == null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 100);
			deviceAuthorize.setValidTime(cal.getTime());
		}
		return WebApiReturnResultModel.ofSuccess(deviceAuthorizeService.insert(deviceAuthorize));
	}

	@ApiOperation("查询设备授权信息")
	@PostMapping("/listDeviceAuthorizePage")
	@Override
	public WebApiReturnResultModel listDeviceAuthorizePage(
			@Validated @RequestBody CommonPageRequestDTO commonPageRequest) {
		PageInfo<DeviceAuthorizeDO> listPage = deviceAuthorizeService.listDeviceAuthorize(commonPageRequest);
		List<DeviceAuthorizeVO> voList = listPage.getList().stream()
				.map(q -> dozerBeanMapper.map(q, DeviceAuthorizeVO.class)).collect(Collectors.toList());
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(listPage, voList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
	
	
	@ApiOperation("通过文件对设备进行批量授权")
	@PostMapping("/importFileDeviceAuthorize")
	@Override
	public WebApiReturnResultModel importFileDeviceAuthorize(
			@RequestBody @Validated ImportFileDeviceAuthorizeRequestDTO importFileDeviceAuthorizeRequestDTO) {
		byte[] fileByte = fileStorageService.getFile(importFileDeviceAuthorizeRequestDTO.getFileName());
		if (fileByte == null) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.NO_FILE);
		}
		List<DeviceAuthorizeDO> deviceAuthorizeList = deviceAuthorizeFileAnalysis.fileAnalysis(fileByte);
		deviceAuthorizeList.forEach(q -> q.setCreatorTime(new Date()));
		deviceAuthorizeService.insert(deviceAuthorizeList);
		return WebApiReturnResultModel.ofSuccess(null);
	}
}
