/**
 * 
 */
package com.wxhj.cloud.platform.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import com.wxhj.cloud.feignClient.platform.EnumManageClient;
import com.wxhj.cloud.feignClient.platform.request.EnumTypeListRequestDTO;
import com.wxhj.cloud.platform.domain.EnumManageDO;
import com.wxhj.cloud.platform.dto.request.SubmitEnumInfoRequestDTO;
import com.wxhj.cloud.platform.service.EnumManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: EnumManageController.java
 * @author: cya
 * @Date: 2020年1月8日 下午2:59:27
 */
@RequestMapping("/backstage/enumManage")
@RestController
@Api(tags = "枚举管理接口")
public class EnumManageController implements EnumManageClient {
	@Resource
	EnumManageService enumManageService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	
	@PostMapping("/enumTypeList")
	@ApiOperation(value="根据枚举编号获取对应的枚举列表",response = EnumManageDO.class)
	@Override
	public WebApiReturnResultModel enumTypeList(@RequestBody EnumTypeListRequestDTO enumTypeListRequest) {
		List<EnumManageDO> enumTypeNameList = enumManageService
				.selectByEnumCode(enumTypeListRequest.getEnumCode());
		return WebApiReturnResultModel.ofSuccess(enumTypeNameList);
	}
	

	@PostMapping("/enumManageList")
	@ApiOperation(value="获取枚举信息列表",response = EnumManageDO.class)
	public WebApiReturnResultModel enumManageList(@RequestBody CommonPageRequestDTO commonPageRequestDTO) {
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) enumManageService
				.selectEnumList(commonPageRequestDTO, commonPageRequestDTO.getNameValue());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@PostMapping("/submitEnumInfo")
	@ApiOperation("新增/修改枚举信息列表")
	public WebApiReturnResultModel submitEnumInfo(@RequestBody SubmitEnumInfoRequestDTO submitEnumInfoRequest) {
		EnumManageDO enumManageDO = dozerBeanMapper.map(submitEnumInfoRequest, EnumManageDO.class);
		String id;
		if (Strings.isNullOrEmpty(enumManageDO.getId())) {
			id=enumManageService.insert(enumManageDO);
		} else {
			// 枚举编号一旦设定就无法改变
			enumManageDO.setEnumCode(null);
			enumManageService.update(enumManageDO);
			id = enumManageDO.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@PostMapping("/deleteEnum")
	@ApiOperation("删除枚举")
	public WebApiReturnResultModel deleteEnum(@RequestBody CommonIdRequestDTO commonIdRequest) {
		enumManageService.delete(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}
}
