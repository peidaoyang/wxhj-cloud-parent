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
import com.wxhj.cloud.business.domain.RouteInfoDO;
import com.wxhj.cloud.business.service.shuttleBus.RouteInfoService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.RouteInfoClient;
import com.wxhj.cloud.feignClient.business.request.RouteInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitRoutInfoRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.RouteByOrgVO;
import com.wxhj.cloud.feignClient.business.vo.RouteInfoListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: RouteInfoController.java
 * @author: cya
 * @Date: 2020年2月4日 下午3:53:41
 */
@RestController
@RequestMapping("/route")
public class RouteInfoController implements RouteInfoClient {
	@Resource
	RouteInfoService routeInfoService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@SuppressWarnings("unchecked")
	@ApiOperation("线路信息查询")
	@PostMapping("/routeInfoList")
	@Override
	public WebApiReturnResultModel routeInfoList(@RequestBody @Validated RouteInfoListRequestDTO routeInfoList) {
		PageInfo<RouteInfoDO> routeList = routeInfoService.select(routeInfoList, routeInfoList.getOrganizeId(),routeInfoList.getNameValue(),routeInfoList.getType());
		
		List<RouteInfoListVO> routeResponseList = routeList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, RouteInfoListVO.class)).collect(Collectors.toList());
		try {
			routeResponseList = (List<RouteInfoListVO>) accessedRemotelyService.accessedOrganizeList(routeResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(routeList,
				routeResponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
	
	@ApiOperation("根据组织查询线路")
	@PostMapping("/routeByOrg")
	@Override
	public WebApiReturnResultModel routeByOrg(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest) {
		List<RouteByOrgVO> voList = routeInfoService.listByOrg(commonOrganizeRequest.getOrganizeId()).stream()
				.map(q-> dozerBeanMapper.map(q, RouteByOrgVO.class)).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(voList);
	}
	
	
	
	@ApiOperation("新增/修改 线路信息")
	@PostMapping("/submitRoutInfo")
	@Override
	public WebApiReturnResultModel submitRoutInfo(@RequestBody @Validated SubmitRoutInfoRequestDTO submitRoutInfo) {
		RouteInfoDO routeInfo = dozerBeanMapper.map(submitRoutInfo, RouteInfoDO.class);
		String id;
		if (Strings.isNullOrEmpty(submitRoutInfo.getId())) {
			id = routeInfoService.insert(routeInfo);
		} else {
			routeInfoService.update(routeInfo);
			id = routeInfo.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@ApiOperation("删除线路信息")
	@PostMapping("/deleteRouteInfo")
	@Override
	public WebApiReturnResultModel deleteRouteInfo(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		routeInfoService.delete(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess();
	}
}
