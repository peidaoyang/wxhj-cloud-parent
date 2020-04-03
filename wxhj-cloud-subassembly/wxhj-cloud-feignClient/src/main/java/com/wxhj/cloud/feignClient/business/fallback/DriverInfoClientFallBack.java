/**
 * 
 */
package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.DriverInfoClient;
import com.wxhj.cloud.feignClient.business.request.DriverListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitDriverInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @ClassName: DriverInfoClientFallBack.java
 * @author: cya
 * @Date: 2020年2月4日 下午6:33:36 
 */
@Component
public class DriverInfoClientFallBack implements DriverInfoClient{


	@Override
	public WebApiReturnResultModel driverList(DriverListRequestDTO driverList) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel submitDriverInfo(SubmitDriverInfoRequestDTO submitDriverInfo) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel deleteDriver(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel driverInfoById(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
