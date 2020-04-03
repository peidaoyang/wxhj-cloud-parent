package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.RouteInfoClient;
import com.wxhj.cloud.feignClient.business.request.RouteInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitRoutInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

/**
 * @ClassName: RouteInfoClientFallBack.java
 * @author: cya
 * @Date: 2020年2月4日 下午4:44:58 
 */
@Component
public class RouteInfoClientFallBack implements RouteInfoClient{

	@Override
	public WebApiReturnResultModel routeInfoList(RouteInfoListRequestDTO routeInfoList) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitRoutInfo(SubmitRoutInfoRequestDTO submitRoutInfo) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteRouteInfo(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel routeByOrg(CommonOrganizeRequestDTO commonOrganizeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
