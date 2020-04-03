package com.wxhj.cloud.platform.controller.shuttleBus;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.RouteInfoClient;
import com.wxhj.cloud.feignClient.business.request.RouteInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitRoutInfoRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.RouteByOrgVO;
import com.wxhj.cloud.feignClient.business.vo.RouteInfoListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: RouteInfoController.java
 * @author: cya
 * @Date: 2020年2月4日 下午4:19:14 
 */
@RestController
@RequestMapping("/shuttleBusManage/route")
@Api(tags="线路接口")
public class RouteInfoController {
	@Resource
	RouteInfoClient routeInfoClient;
	
	@ApiOperation(value="线路信息查询",response = RouteInfoListVO.class)
	@PostMapping("/routeInfoList")
	@LcnTransaction
	public WebApiReturnResultModel routeInfoList(@RequestBody @Validated RouteInfoListRequestDTO routeInfoList) {
		return routeInfoClient.routeInfoList(routeInfoList);
	}
	
	@ApiOperation(value="根据组织查询线路",response = RouteByOrgVO.class)
	@PostMapping("/routeByOrg")
	@LcnTransaction
	public WebApiReturnResultModel routeByOrg(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest) {
		return routeInfoClient.routeByOrg(commonOrganizeRequest);
	}
	
	@ApiOperation("新增/修改 线路信息")
	@PostMapping("/submitRoutInfo")
	@LcnTransaction
	public WebApiReturnResultModel submitRoutInfo(@RequestBody @Validated SubmitRoutInfoRequestDTO submitRoutInfo) {
		return routeInfoClient.submitRoutInfo(submitRoutInfo);
	}
	
	@ApiOperation("删除线路信息")
	@PostMapping("/deleteRouteInfo")
	@LcnTransaction
	public WebApiReturnResultModel deleteRouteInfo(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		return routeInfoClient.deleteRouteInfo(commonIdListRequest);
	}
}
