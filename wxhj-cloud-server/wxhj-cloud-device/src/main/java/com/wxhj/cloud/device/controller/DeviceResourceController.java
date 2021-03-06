/**
 * @className DeviceResourceController.java
 * @admin jwl
 * @date 2020年1月7日 下午1:13:50
 */
package com.wxhj.cloud.device.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.DeviceResourceDO;
import com.wxhj.cloud.device.domain.VersionManageDO;
import com.wxhj.cloud.device.domain.view.ViewDeviceResourceDO;
import com.wxhj.cloud.device.service.DeviceResourceService;
import com.wxhj.cloud.device.service.VersionManageService;
import com.wxhj.cloud.device.service.ViewDeviceResourceService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.DeviceResourceClient;
import com.wxhj.cloud.feignClient.device.request.ListDeviceResourceRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceResourceRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.ViewDeviceResourceVO;

import io.swagger.annotations.ApiOperation;

/**
 * @className DeviceResourceController.java
 * @admin jwl
 * @date 2020年1月7日 下午1:13:50
 */
@RequestMapping("/deviceResource")
@RestController
public class DeviceResourceController implements DeviceResourceClient {
	@Resource
	DeviceResourceService deviceResourceService;
	@Resource
	ViewDeviceResourceService viewDeviceResourceService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	VersionManageService versionManageService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@PostMapping("/listDeviceResource")
	@ApiOperation("查询设备资源")
	@Override
	public WebApiReturnResultModel listDeviceResource(@RequestBody ListDeviceResourceRequestDTO listDeviceResource) {

		PageInfo<ViewDeviceResourceDO> listPage = viewDeviceResourceService.listPage(listDeviceResource,
				listDeviceResource.getOrganizeId(), listDeviceResource.getDeviceType(),
				listDeviceResource.getNameValue());

		List<ViewDeviceResourceVO> viewDeviceResourceList = listPage.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ViewDeviceResourceVO.class)).collect(Collectors.toList());

		try {
			viewDeviceResourceList = (List<ViewDeviceResourceVO>) accessedRemotelyService
					.accessedOrganizeList(viewDeviceResourceList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage,
				viewDeviceResourceList, new PageDefResponseModel());

		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@PostMapping("/submitDeviceResource")
	@ApiOperation("编辑设备资源")
	@Override
	public WebApiReturnResultModel submitDeviceResource(
			@RequestBody SubmitDeviceResourceRequestDTO submitDeviceResource) {

		VersionManageDO versionManage = versionManageService.selectById(submitDeviceResource.getVersionId());

		List<DeviceResourceDO> deviceResourceList = submitDeviceResource.getDeviceIdList().stream().map(q -> {
			DeviceResourceDO deviceResource = new DeviceResourceDO();
			deviceResource.setDeviceId(q);
			deviceResource.setResourceType(versionManage.getResourceType());
			deviceResource.setVersionId(submitDeviceResource.getVersionId());
			deviceResource.setFileSize(versionManage.getFileSize());
			deviceResource.setMd5(versionManage.getMd5());
			return deviceResource;
		}).collect(Collectors.toList());
		List<DeviceResourceDO> deviceResource = deviceResourceService
				.selectByDeviceIdAndResourceType(submitDeviceResource.getDeviceIdList(), versionManage.getResourceType());
		if (deviceResource.size() > 0) {
			deviceResource.forEach(q -> {
				deviceResourceService.delete(q.getId());
			});
		}
		deviceResourceService.insertList(deviceResourceList);
		return WebApiReturnResultModel.ofSuccess();
	}
}
