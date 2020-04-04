package com.wxhj.cloud.business.controller.visitor;

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
import com.wxhj.cloud.business.domain.VisitorInfoDO;
import com.wxhj.cloud.business.service.visitor.VisitorInfoService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.VisitorInfoClient;
import com.wxhj.cloud.feignClient.business.request.CheckVisRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitVisitorRequestDTO;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoAppRequestDTO;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoPosRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.VisitorInfoListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: VisitorInfoController.java
 * @author: cya
 * @Date: 2020年2月11日 下午4:06:45
 */
@RestController
@RequestMapping("/visitorInfo")
public class VisitorInfoController implements VisitorInfoClient {
	@Resource
	VisitorInfoService visitorInfoService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@PostMapping("/visitorInfoList")
	@ApiOperation("查询访客信息")
	@Override
	public WebApiReturnResultModel visitorInfoList(
			@RequestBody @Validated VisitorInfoListRequestDTO visitorInfoListRequest) {
		PageInfo<VisitorInfoDO> visitorList = visitorInfoService.listPage(visitorInfoListRequest,
					visitorInfoListRequest.getOrganizeId(), visitorInfoListRequest.getNameValue(),
					visitorInfoListRequest.getIsCheck(),visitorInfoListRequest.getType());
	
		List<VisitorInfoListVO> visitorResponseList = visitorList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, VisitorInfoListVO.class)).collect(Collectors.toList());

		try {
			visitorResponseList = (List<VisitorInfoListVO>) accessedRemotelyService.accessedOrganizeSceneList(visitorResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(visitorList,
				visitorResponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@PostMapping("/submitVisitor")
	@ApiOperation("新增/修改访客信息")
	@Override
	public WebApiReturnResultModel submitVisitor(@RequestBody @Validated SubmitVisitorRequestDTO submitVisitorRequest) {
		VisitorInfoDO visitorInfo = dozerBeanMapper.map(submitVisitorRequest, VisitorInfoDO.class);
		String id;
		if (Strings.isNullOrEmpty(submitVisitorRequest.getId())) {
			id = visitorInfoService.insert(visitorInfo);
		} else {
			visitorInfoService.update(visitorInfo);
			id = visitorInfo.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@PostMapping("/checkVis")
	@ApiOperation("审核访客")
	@Override
	public WebApiReturnResultModel checkVis(@RequestBody @Validated CheckVisRequestDTO checkVisRequest) {
		visitorInfoService.check(checkVisRequest.getId(), checkVisRequest.getIsCheck());
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation("app访客信息查询")
	@PostMapping("/visitorInfoApp")
	@Override
	public WebApiReturnResultModel visitorInfoApp(
			@RequestBody @Validated VisitorInfoAppRequestDTO visitorInfoAppRequest) {
		return WebApiReturnResultModel
				.ofSuccess(visitorInfoService.select(visitorInfoAppRequest, visitorInfoAppRequest.getAccountId(),
						visitorInfoAppRequest.getBeginTime(), visitorInfoAppRequest.getEndTime()));
	}

	@ApiOperation("设备校验访客")
	@PostMapping("/visitorInfoPos")
	@Override
	public WebApiReturnResultModel visitorInfoPos(@RequestBody VisitorInfoPosRequestDTO visitorInfoPosRequest) {
		return WebApiReturnResultModel
				.ofSuccess(visitorInfoService.selectByIdNumberAndSceneId(visitorInfoPosRequest.getIsNumber(),
						visitorInfoPosRequest.getSceneId(), visitorInfoPosRequest.getDateTime()));
	}

	@ApiOperation("删除访客信息")
	@PostMapping("/deleteVisitor")
	@Override
	public WebApiReturnResultModel deleteVisitor(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		visitorInfoService.delete(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess();
	}
}