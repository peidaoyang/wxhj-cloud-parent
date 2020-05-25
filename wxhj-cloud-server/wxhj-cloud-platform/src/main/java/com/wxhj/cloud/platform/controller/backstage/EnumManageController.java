/**
 * 
 */
package com.wxhj.cloud.platform.controller.backstage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.enums.OrganizeTypeEnum;
import com.wxhj.cloud.feignClient.account.AccountTypeClient;
import com.wxhj.cloud.feignClient.account.request.ListByOrgTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.ListByOrgTypeVO;
import com.wxhj.cloud.platform.dto.request.EnumOrgTypeListRequestDTO;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.vo.EnumOrgTypeListVO;
import org.springframework.validation.annotation.Validated;
import com.github.dozermapper.core.Mapper;
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
	AccountTypeClient accountTypeClient;
	@Resource
	SysOrganizeService sysOrganizeService;

	@Resource
	Mapper dozerBeanMapper;



	@PostMapping("/enumTypeList")
	@ApiOperation(value="根据枚举编号获取对应的枚举列表",response = EnumManageDO.class)
	@Override
	public WebApiReturnResultModel enumTypeList(@RequestBody EnumTypeListRequestDTO enumTypeListRequest) {
		List<EnumManageDO> enumTypeNameList = enumManageService
				.selectByEnumCode(enumTypeListRequest.getEnumCode());
		return WebApiReturnResultModel.ofSuccess(enumTypeNameList);
	}

	@PostMapping("/enumOrgTypeList")
	@ApiOperation(value = "获取组织类型枚举列表")
	public WebApiReturnResultModel enumOrgTypeList(@RequestBody @Validated EnumOrgTypeListRequestDTO enumOrgTypeList){
		List<EnumOrgTypeListVO> voList = new ArrayList<>();
		if(enumOrgTypeList.getOrgType() == OrganizeTypeEnum.DEFAULT_TYPE.getCode()){
			voList = enumManageService.selectByEnumCode(20).stream().map(q -> dozerBeanMapper.map(q,EnumOrgTypeListVO.class)).collect(Collectors.toList());
		}else if(enumOrgTypeList.getOrgType() == OrganizeTypeEnum.SCHOOL_TYPE.getCode()){
			voList = enumManageService.selectByEnumCode(21).stream().map(q -> dozerBeanMapper.map(q,EnumOrgTypeListVO.class)).collect(Collectors.toList());
		}else if(enumOrgTypeList.getOrgType() == OrganizeTypeEnum.BUSINESS_TYPE.getCode()){
			voList = enumManageService.selectByEnumCode(22).stream().map(q -> dozerBeanMapper.map(q,EnumOrgTypeListVO.class)).collect(Collectors.toList());
		}
		return WebApiReturnResultModel.ofSuccess(voList);
	}

	@PostMapping("/listByOrgId")
	@ApiOperation(value = "根据组织Id获取人员类型列表",response = ListByOrgTypeVO.class)
	@LcnTransaction
	public WebApiReturnResultModel listByOrgType(@RequestBody @Validated CommonIdRequestDTO commonId){
		return accountTypeClient.listByOrgType(new ListByOrgTypeRequestDTO(sysOrganizeService.selectById(commonId.getId()).getType()));
	}

	@PostMapping("/listByOrgType")
	@ApiOperation(value = "根据组织类型获取人员类型列表",response = ListByOrgTypeVO.class)
	@LcnTransaction
	public WebApiReturnResultModel listByOrgType(@RequestBody @Validated ListByOrgTypeRequestDTO listByOrgTypeRequestDTO){
		return accountTypeClient.listByOrgType(listByOrgTypeRequestDTO);
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
