package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.feignClient.business.request.*;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.VisitorInfoClient;

import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

/**
 * @ClassName: VisitorInfoClientFallBack.java
 * @author: cya
 * @Date: 2020年2月11日 下午4:36:04
 */
@Component
public class VisitorInfoClientFallBack implements VisitorInfoClient {

	@Override
	public WebApiReturnResultModel visitorInfoList(VisitorInfoListRequestDTO visitorInfoListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitVisitor(SubmitVisitorRequestDTO submitVisitorRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel checkVis(CheckVisRequestDTO checkVisRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel visitorInfoApp(VisitorInfoAppRequestDTO visitorInfoAppRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel visitorInfoPos(VisitorInfoPosRequestDTO visitorInfoPosRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel deleteVisitor(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
}
