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
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.FlightInfoDO;
import com.wxhj.cloud.business.domain.view.ViewFlightRouteDO;
import com.wxhj.cloud.business.service.shuttleBus.FlightInfoService;
import com.wxhj.cloud.business.service.shuttleBus.ViewFlightRouteService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.FlightInfoClient;
import com.wxhj.cloud.feignClient.business.request.AppFlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.FlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitFlightRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.FlightListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: FlightInfoController.java
 * @author: cya
 * @Date: 2020年2月5日 下午5:29:04
 */
@RestController
@RequestMapping("/flight")
@Api("班次接口")
public class FlightInfoController implements FlightInfoClient {
	@Resource
	FlightInfoService flightInfoService;
	@Resource
	ViewFlightRouteService viewFlightRouteService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@ApiOperation("分页查询 班次选项")
	@PostMapping("/flightList")
	@Override
	public WebApiReturnResultModel flightList(@RequestBody @Validated FlightListRequestDTO flightListRequest) {
		PageInfo<ViewFlightRouteDO> flightInfoList = viewFlightRouteService.listPage(flightListRequest, flightListRequest.getOrganizeId(),
					flightListRequest.getNameValue(), flightListRequest.getType());

		List<FlightListVO> flightInfoResponseList = flightInfoList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, FlightListVO.class)).collect(Collectors.toList());
		try {
			flightInfoResponseList = (List<FlightListVO>) accessedRemotelyService
					.accessedOrganizeList(flightInfoResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(flightInfoList, flightInfoResponseList, new PageDefResponseModel());

		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("app查询班次信息")
	@PostMapping("/appFlightList")
	@Override
	public WebApiReturnResultModel appFlightList(@RequestBody @Validated AppFlightListRequestDTO appFlightListRequest) {
		return WebApiReturnResultModel.ofSuccess(
				viewFlightRouteService.listBySiteNamePage(appFlightListRequest, appFlightListRequest.getNameValue()));
	}

	@ApiOperation("根据班次id查询班次选项")
	@PostMapping("/flightById")
	@Override
	public WebApiReturnResultModel flightById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofSuccess(flightInfoService.select(commonIdRequest.getId()));
	}

	@ApiOperation("新增/修改 班次")
	@PostMapping("/submitFlight")
	@Override
	public WebApiReturnResultModel submitFlight(@RequestBody @Validated SubmitFlightRequestDTO submitFlightRequest) {
		FlightInfoDO flightInfoDO = dozerBeanMapper.map(submitFlightRequest, FlightInfoDO.class);
		String id;
		if (Strings.isNullOrEmpty(submitFlightRequest.getId())) {
			id = flightInfoService.insert(flightInfoDO);
		} else {
			flightInfoService.update(flightInfoDO);
			id = flightInfoDO.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@ApiOperation("删除班次")
	@PostMapping("/deleteFlight")
	@Override
	public WebApiReturnResultModel deleteFlight(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		flightInfoService.delete(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess();
	}

}
