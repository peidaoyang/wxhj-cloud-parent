package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.VisitInfoClient;
import com.wxhj.cloud.feignClient.business.request.VisitInfoListRequestDTO;


/**
 * @ClassName: VisitInfoClientFallBack.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:48:42 
 */
@Component
public class VisitInfoClientFallBack implements VisitInfoClient{

	@Override
	public WebApiReturnResultModel visitInfoList(VisitInfoListRequestDTO vInfoListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
	
}
