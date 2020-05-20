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
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.VersionManageDO;
import com.wxhj.cloud.device.service.VersionManageService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.VersionManageClient;
import com.wxhj.cloud.feignClient.device.request.SubmitVerManageRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageListRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageOrgListRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.VersionManageVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: VersionManage.java
 * @author: cya
 * @Date: 2020年1月2日 下午4:45:59
 */
@RestController
@RequestMapping("/versionManage")
@Api(tags = "版本管理")
public class VersionManageController implements VersionManageClient {

	@Resource
	VersionManageService versionManageService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	FileStorageService fileStorageService;

	@Override
	@PostMapping("/versionManageList")
	@ApiOperation("获取版本管理信息列表")
	public WebApiReturnResultModel versionManageList(
			@RequestBody @Validated() VersionManageListRequestDTO versionManageListRequest) {

		PageInfo<VersionManageDO> versionManageListPage = versionManageService
				.versionManageListPage(versionManageListRequest, versionManageListRequest.getDeviceType());

		List<VersionManageVO> versionManageList = versionManageListPage.getList().stream()
				.map(q -> dozerBeanMapper.map(q, VersionManageVO.class)).collect(Collectors.toList());
		try {
			versionManageList = (List<VersionManageVO>) accessedRemotelyService.accessedOrganizeList(versionManageList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(versionManageListPage, versionManageList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@Override
	@PostMapping("/versionManageOrgList")
	@ApiOperation("根据组织获取版本管理信息列表")
	public WebApiReturnResultModel versionManageOrgList(
			@RequestBody @Validated() VersionManageOrgListRequestDTO versionManageOrgList) {
		PageInfo<VersionManageDO> versionManageListPage = versionManageService.versionManageListPage(
				versionManageOrgList, versionManageOrgList.getOrganizeId(), versionManageOrgList.getDeviceType());

		List<VersionManageVO> versionManageList = versionManageListPage.getList().stream()
				.map(q -> dozerBeanMapper.map(q, VersionManageVO.class)).collect(Collectors.toList());
		try {
			versionManageList = (List<VersionManageVO>) accessedRemotelyService.accessedOrganizeList(versionManageList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(versionManageListPage, versionManageList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@Override
	@PostMapping("/submitVerManage")
	@ApiOperation("新增/更新版本状态")
	public WebApiReturnResultModel submitVerManage(@RequestBody SubmitVerManageRequestDTO submitVerManage) {
		VersionManageDO versionManage = dozerBeanMapper.map(submitVerManage, VersionManageDO.class);
		String id;
		if (Strings.isNullOrEmpty(versionManage.getId())) {
			fileStorageService.notDeleteFile(versionManage.getFileName());
			id = versionManageService.insert(versionManage, submitVerManage.getUserId());
		} else {
			//更新版本状态
			versionManageService.updateByReleaseState(versionManage.getId(), submitVerManage.getUserId());
			id = versionManage.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@Override
	@PostMapping("/deleteVerManage")
	@ApiOperation("删除版本状态")
	public WebApiReturnResultModel deleteVerManage(@RequestBody CommonIdListRequestDTO commonIdListRequest) {

		List<VersionManageDO> versionManageList = versionManageService.listByIdList(commonIdListRequest.getIdList());

		for (VersionManageDO versionManageTemp : versionManageList) {
			// byte[] fileByte =
			// fileStorageService.getFile(versionManageTemp.getFileName());

//			fileStorageService.existFile(versionManageTemp.getFileName())
//			if (fileByte != null) {
			fileStorageService.deleteFile(versionManageTemp.getFileName());
//			} else {
//				continue;
//			}
		}

		versionManageService.deleteByIdList(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess();
	}
}
