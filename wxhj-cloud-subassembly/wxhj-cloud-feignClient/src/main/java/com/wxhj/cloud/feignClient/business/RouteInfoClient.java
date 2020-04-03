/**
 * 
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.RouteInfoClientFallBack;
import com.wxhj.cloud.feignClient.business.request.RouteInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitRoutInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

/**
 * @ClassName: RouteInfoClient.java
 * @author: cya
 * @Date: 2020年2月4日 下午4:16:02 
 */
@Component
@FeignClient(name = "businessServer",fallback=RouteInfoClientFallBack.class)
public interface RouteInfoClient {
	
	@PostMapping("/route/routeInfoList")
	WebApiReturnResultModel routeInfoList(@RequestBody RouteInfoListRequestDTO routeInfoList);
	
	@PostMapping("/route/routeByOrg")
	WebApiReturnResultModel routeByOrg(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest);
	
	
	@PostMapping("/route/submitRoutInfo")
	WebApiReturnResultModel submitRoutInfo(@RequestBody SubmitRoutInfoRequestDTO submitRoutInfo);
	
	@PostMapping("/route/deleteRouteInfo")
	WebApiReturnResultModel deleteRouteInfo(@RequestBody CommonIdListRequestDTO commonIdListRequest);
}
