package com.wxhj.cloud.business.controller.shuttleBus;

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
import com.wxhj.cloud.business.domain.view.ViewRideSummaryDO;
import com.wxhj.cloud.business.service.shuttleBus.ViewRideSummaryService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.ViewRideSummaryClient;
import com.wxhj.cloud.feignClient.business.request.ViewRideSummaryListRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.ViewRideSummaryListVO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: ViewRideSummaryController.java
 * @author: cya
 * @Date: 2020年2月6日 下午2:59:30
 */
@RestController
@RequestMapping("/viewRideSummary")
public class ViewRideSummaryController implements ViewRideSummaryClient {
	@Resource
	ViewRideSummaryService viewRideSummaryService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	Mapper dozerBeanMapper;

	@SuppressWarnings("unchecked")
	@ApiOperation("班次汇总分页查询")
	@PostMapping("/viewRideSummaryList")
	public WebApiReturnResultModel viewRideSummaryList(
			@RequestBody @Validated ViewRideSummaryListRequestDTO viewRideSummaryListRequest) {
		PageInfo<ViewRideSummaryDO> viewRideSummaryInfoList = viewRideSummaryService.select(viewRideSummaryListRequest,
				viewRideSummaryListRequest.getOrganizeId(), viewRideSummaryListRequest.getNameValue(),
				viewRideSummaryListRequest.getBeginTime(), viewRideSummaryListRequest.getEndTime());

		List<ViewRideSummaryListVO> viewRideSummaryResponseList = viewRideSummaryInfoList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ViewRideSummaryListVO.class)).collect(Collectors.toList());

		try {
			viewRideSummaryResponseList = (List<ViewRideSummaryListVO>) accessedRemotelyService
					.accessedOrganizeList(viewRideSummaryResponseList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(
				viewRideSummaryInfoList, viewRideSummaryResponseList, new PageDefResponseModel());

		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
}
