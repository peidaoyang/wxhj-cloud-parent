package com.wxhj.cloud.platform.controller.backstage;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.VersionManageClient;
import com.wxhj.cloud.feignClient.device.request.SubmitVerManageRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageListRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageOrgListRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.VersionManageVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: VersionManageController.java
 * @author: cya
 * @Date: 2020年1月3日 上午9:39:41
 */
@RequestMapping("/device/versionManage")
@RestController
@Api(tags = "版本管理信息接口")
public class VersionManageController {
	@Resource
	VersionManageClient versionManageClient;

	@PostMapping("/versionManageList")
	@ApiOperation(value="设备程序管理分页查询", response = VersionManageVO.class)
	@LcnTransaction
	public WebApiReturnResultModel versionManageList(
			@RequestBody @Validated VersionManageListRequestDTO versionManageListRequestDTO) {
		return versionManageClient.versionManageList(versionManageListRequestDTO);
	}
	

	@PostMapping("/submitVerManage")
	@ApiOperation("新增/发布 设备程序管理")
	@LcnTransaction
	public WebApiReturnResultModel submitVerManage(@RequestBody @Validated SubmitVerManageRequestDTO submitVerManage) {
		return versionManageClient.submitVerManage(submitVerManage);
	}



	@PostMapping("/deleteVerManage")
	@ApiOperation("删除设备程序管理")
	@LcnTransaction
	public WebApiReturnResultModel deleteVerManage(@RequestBody CommonIdListRequestDTO commonIdListRequest) {
		return versionManageClient.deleteVerManage(commonIdListRequest);
	}
	
	
	@PostMapping("/versionManageOrgList")
	@ApiOperation(value="根据组织获取版本管理信息列表",response = VersionManageVO.class)
	@LcnTransaction
	public WebApiReturnResultModel versionManageOrgList(
			@RequestBody @Validated VersionManageOrgListRequestDTO versionManageOrgList) {
		return versionManageClient.versionManageOrgList(versionManageOrgList);
	}
}
