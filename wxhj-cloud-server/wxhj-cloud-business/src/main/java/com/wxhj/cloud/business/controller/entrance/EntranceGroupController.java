/**
 * @className EntranceGroupController.java
 * @author jwl
 * @date 2020年1月10日 下午4:24:42
 */
package com.wxhj.cloud.business.controller.entrance;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.EntranceDayDO;
import com.wxhj.cloud.business.domain.EntranceGroupDO;
import com.wxhj.cloud.business.domain.EntranceGroupRecDO;
import com.wxhj.cloud.business.dto.response.EntranceGroupResponseDTO;
import com.wxhj.cloud.business.entrance.EntranceGroupBO;
import com.wxhj.cloud.business.entrance.EntranceRuleService;
import com.wxhj.cloud.business.service.EntranceDayService;
import com.wxhj.cloud.business.service.EntranceGroupRecService;
import com.wxhj.cloud.business.service.EntranceGroupService;
import com.wxhj.cloud.business.vo.EntranceGroupRecVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.AuthorityType;
import com.wxhj.cloud.core.enums.DeviceGlobalParameterEnum;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
import com.wxhj.cloud.feignClient.business.EntranceGroupClient;
import com.wxhj.cloud.feignClient.business.request.ListEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.EntranceGroupVO;
import com.wxhj.cloud.feignClient.device.DeviceGlobalParameterClient;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceGlobalParameterRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @className EntranceGroupController.java
 * @author jwl
 * @date 2020年1月10日 下午4:24:42
 */
@RequestMapping("/entranceGroup")
@RestController
@Slf4j
public class EntranceGroupController implements EntranceGroupClient {
	@Resource
	EntranceGroupService entranceGroupService;
	@Resource
	EntranceGroupRecService entranceGroupRecService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	EntranceDayService entranceDayService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	AuthorityGroupClient authorityGroupClient;
	@Resource
	MapperClient mapperClient;
	// @Resource
	// EntranceDayRecService entranceDayRecService;

	@Resource
	DeviceGlobalParameterClient deviceGlobalParameterClient;
	@Resource
	FileStorageService fileStorageService;
	@Resource
	EntranceRuleService entranceRuleService;
	
	@ApiOperation(value = "获取通行规则")
	@PostMapping("/listEntranceGroup")
	@Override
	public WebApiReturnResultModel listEntranceGroup(
			@Validated @RequestBody ListEntranceGroupRequestDTO listEntranceGroup) {
		PageInfo<EntranceGroupDO> entranceGroupList = entranceGroupService.listByNameAndOrganPage(listEntranceGroup,
				listEntranceGroup.getNameValue(), listEntranceGroup.getOrganizeId());
		List<EntranceGroupVO> entranceGroupResponseList = entranceGroupList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, EntranceGroupVO.class)).collect(Collectors.toList());

		try {
			entranceGroupResponseList = (List<EntranceGroupVO>) accessedRemotelyService
					.accessedOrganizeList(entranceGroupResponseList);
			entranceGroupResponseList = (List<EntranceGroupVO>) accessedRemotelyService.accessdAuthoritySynchroList(entranceGroupResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(entranceGroupList, entranceGroupResponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
	
	@ApiOperation(value = "编辑通行规则")
	@PostMapping("/submitEntranceGroup")
	@Override
	public WebApiReturnResultModel submitEntranceGroup(
			@Validated @RequestBody SubmitEntranceGroupRequestDTO submitEntranceGroupRequest) {
		String authorizeId = null;
		try {
			authorizeId = submitAuthorityGroupInfo(submitEntranceGroupRequest);
			insertEntranceGroup(authorizeId, submitEntranceGroupRequest);

		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		return WebApiReturnResultModel.ofSuccess(authorizeId);
	}

	private String submitAuthorityGroupInfo(SubmitEntranceGroupRequestDTO submitEntranceGroupRequest)
			throws WuXiHuaJieFeignError {
		SubmitAuthorityGroupInfoRequestDTO submit = dozerBeanMapper.map(submitEntranceGroupRequest,
				SubmitAuthorityGroupInfoRequestDTO.class);
		submit.setType(AuthorityType.ENTRANCE.getCode());
		WebApiReturnResultModel webApiReturnResultModel = authorityGroupClient.submitAuthorityGroupInfo(submit);
		String authorizeId = FeignUtil.formatClass(webApiReturnResultModel, String.class);
		return authorizeId;
	}

	private void insertEntranceGroup(String id, SubmitEntranceGroupRequestDTO submitEntranceGroupRequest) {
		EntranceGroupDO entranceGroup = dozerBeanMapper.map(submitEntranceGroupRequest, EntranceGroupDO.class);
		List<EntranceGroupRecDO> entranceGroupRecList = submitEntranceGroupRequest.getEntranceGroupRecList().stream()
				.map(q -> dozerBeanMapper.map(q, EntranceGroupRecDO.class)).collect(Collectors.toList());
		for (EntranceGroupRecDO entranceGroupRecTemp : entranceGroupRecList) {
			entranceGroupRecTemp.setEntranceGroupId(id);
		}
		entranceGroup.setId(id);
		if (Strings.isNullOrEmpty(submitEntranceGroupRequest.getId())) {
			entranceGroupService.insertCascade(entranceGroup, entranceGroupRecList);
		} else {
			entranceGroupService.updateCascade(entranceGroup, entranceGroupRecList);
		}
	}

	@ApiOperation(value = "应用通行权限组")
	@PostMapping("/importMapEntrAuth")
	@Override
	public WebApiReturnResultModel importMapEntrAuth(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		EntranceGroupDO entranceGroupTemp = entranceGroupService.selectById(commonIdRequest.getId());
		
//		if (!Strings.isNullOrEmpty(entranceGroupTemp.getParameterId())) {
//			return WebApiReturnResultModel.ofStatus(WebResponseState.NO_DATA, "参数配置为空");
//		}

		EntranceGroupBO entranceGroup = entranceRuleService.createMapEntranceAuthorize(entranceGroupTemp);
		String fileUuid = UUID.randomUUID().toString().concat(".txt");
		try {
			byte[] buffer = JSON.toJSONString(entranceGroup).getBytes(SystemStaticClass.CHARACTER);
			fileStorageService.saveFile(buffer, fileUuid);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}

		// 获取
		String parameterId = null;
		try {
			WebApiReturnResultModel apiReturnResultModel;
			if(!Strings.isNullOrEmpty(entranceGroupTemp.getParameterId())) {
				apiReturnResultModel = deviceGlobalParameterClient
						.deleteDeviceGlobalRarameter(new CommonIdRequestDTO(entranceGroupTemp.getParameterId()));
				FeignUtil.formatClass(apiReturnResultModel, String.class);
			}
			apiReturnResultModel = mapperClient
					.listByAuthIdFromMapAuthScene(new CommonIdRequestDTO(entranceGroupTemp.getId()));
			List<String> sceneList = FeignUtil.formatArrayClass(apiReturnResultModel, String.class);

			SubmitDeviceGlobalParameterRequestDTO submitParameter = createDeviceGlobalParameter(fileUuid, sceneList,
					entranceGroup);
			apiReturnResultModel = deviceGlobalParameterClient.submitDeviceGlobalParameter(submitParameter);
			parameterId = FeignUtil.formatClass(apiReturnResultModel, String.class);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		entranceGroupTemp.setApplyDate(new Date());
		entranceGroupTemp.setParameterId(parameterId);
		entranceGroupService.update(entranceGroupTemp);
		fileStorageService.notDeleteFile(fileUuid);
		return WebApiReturnResultModel.ofSuccess();
	}
	
	@ApiOperation(value = "删除选中通行规则")
	@PostMapping("/deleteEntranceGroup")
	@Override
	public WebApiReturnResultModel deleteEntranceGroup(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		entranceGroupService.deleteCascade(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation(value = "根据编号查询通行规则")
	@PostMapping("/selectEntranceGroup")
	@Override
	public WebApiReturnResultModel selectEntranceGroup(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		EntranceGroupDO entranceGroup = entranceGroupService.selectById(commonIdRequest.getId());
		List<EntranceGroupRecDO> entranceGroupRecList = entranceGroupRecService.listById(entranceGroup.getId());
		List<EntranceGroupRecVO> listRecResponse = new ArrayList<>();
		entranceGroupRecList.forEach(q -> {
			EntranceDayDO entranceDay = entranceDayService.selectById(q.getEntranceDayId());
			if (entranceDay != null) {
				EntranceGroupRecVO entranceDayVO = dozerBeanMapper.map(q, EntranceGroupRecVO.class);
				entranceDayVO.setTimeDescribe(entranceDay.getTimeDescribe());
				listRecResponse.add(entranceDayVO);
			}
		});
		EntranceGroupResponseDTO entranceGroupResponse = dozerBeanMapper.map(entranceGroup,
				EntranceGroupResponseDTO.class);
		entranceGroupResponse.setEntranceDayList(listRecResponse);

		// ListByAuthorityIdRequestDTO listByAuthorityId = new
		// ListByAuthorityIdRequestDTO();
		// listByAuthorityId.setAuthorityId(commonIdRequest.getId());
		WebApiReturnResultModel apiReturnResultModel = mapperClient.listByAuthIdFromMapAuthAccount(commonIdRequest);
		try {
			List<String> listAccount = FeignUtil.formatArrayClass(apiReturnResultModel, String.class);
			apiReturnResultModel = mapperClient.listByAuthIdFromMapAuthScene(commonIdRequest);
			List<String> listScene = FeignUtil.formatArrayClass(apiReturnResultModel, String.class);
			entranceGroupResponse.setAccountList(listAccount);
			entranceGroupResponse.setSceneList(listScene);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		//
		return WebApiReturnResultModel.ofSuccess(entranceGroupResponse);
	}


	// 构建静态参数下载模型
	private SubmitDeviceGlobalParameterRequestDTO createDeviceGlobalParameter(String fileUuid, List<String> sceneId,
			EntranceGroupBO entranceGroup) {
		SubmitDeviceGlobalParameterRequestDTO submitParameter = new SubmitDeviceGlobalParameterRequestDTO();
		submitParameter.setOrganizeId(entranceGroup.getOrganizeId());
		submitParameter.setParameterFileUrl(fileUuid);
		submitParameter.setDeviceType(-1);// 临时变量待优化
		submitParameter.setParameterType(DeviceGlobalParameterEnum.EntranceParameter.getParameterType());// 临时变量
		submitParameter.setSceneIdList(sceneId);
		submitParameter.setDeviceIdListPlus(Arrays.asList("*"));
		//
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(new Date());
		// calendar.add(Calendar.YEAR, 10);
		submitParameter.setStartDatetime(new Date());
		submitParameter.setEndDatetime(DateUtil.calcDate(new Date(), Calendar.YEAR, 10));
		submitParameter.setFullName(entranceGroup.getFullName());
		return submitParameter;
	}
}
