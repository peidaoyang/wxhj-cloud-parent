package com.wxhj.cloud.platform.controller.system;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import com.wxhj.cloud.feignClient.platform.vo.AnnouncementListVO;
import com.wxhj.cloud.platform.domain.EnumManageDO;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.AnnouncementClient;
import com.wxhj.cloud.platform.domain.AnnouncementDO;
import com.wxhj.cloud.platform.dto.request.AnnouncementListRequestDTO;
import com.wxhj.cloud.platform.dto.request.SubmitAnnouncementRequestDTO;
import com.wxhj.cloud.platform.dto.response.NewestAnnouncementResponseDTO;
import com.wxhj.cloud.platform.service.AnnouncementServcie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/systemManage/announcement")
@Api(tags = "公告接口")
public class AnnouncementController implements AnnouncementClient {
	@Resource
	AnnouncementServcie announcementServcie;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@ApiOperation("查下公告信息")
	@PostMapping("/announcementList")
	public WebApiReturnResultModel announcementList(
			@RequestBody @Validated AnnouncementListRequestDTO announcementListRequestD) {
		PageInfo<AnnouncementDO> announcementInfoList = announcementServcie.select(announcementListRequestD,
				announcementListRequestD.getOrganizeId());

		List<AnnouncementListVO> announcementResponseList = announcementInfoList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, AnnouncementListVO.class)).collect(Collectors.toList());
		try {
			announcementResponseList = (List<AnnouncementListVO>) accessedRemotelyService.accessedOrganizeList(announcementResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(announcementInfoList, announcementResponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("新增/修改公告信息")
	@PostMapping("/submitAnnouncement")
	public WebApiReturnResultModel submitAnnouncement(
			@RequestBody @Validated SubmitAnnouncementRequestDTO submitAnnouncementRequest) {
		AnnouncementDO announcement = dozerBeanMapper.map(submitAnnouncementRequest, AnnouncementDO.class);
		String id;
		if (Strings.isNullOrEmpty(submitAnnouncementRequest.getId())) {
			id = announcementServcie.insert(announcement, submitAnnouncementRequest.getUserId());
		} else {
			announcementServcie.update(announcement);
			id = announcement.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@ApiOperation("删除公告信息")
	@PostMapping("/deleteAnnouncement")
	public WebApiReturnResultModel deleteAnnouncement(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
		announcementServcie.delete(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation("获取公告信息")
	@PostMapping("/appAnnouncementList")
	@Override
	public WebApiReturnResultModel appAnnouncementList(
			@RequestBody @Validated CommonListPageRequestDTO commonListPageRequest) {
		PageInfo<AnnouncementDO> announcementInfoList = announcementServcie.listPage(commonListPageRequest,commonListPageRequest.getOrganizeId(),commonListPageRequest.getNameValue());
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(announcementInfoList,pageDefResponseModel, AnnouncementDO.class);
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("根据id获取公告详情")
	@PostMapping("/selectById")
	@Override
	public WebApiReturnResultModel selectById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
		return WebApiReturnResultModel.ofSuccess(announcementServcie.select(commonIdRequest.getId()));
	}
}
