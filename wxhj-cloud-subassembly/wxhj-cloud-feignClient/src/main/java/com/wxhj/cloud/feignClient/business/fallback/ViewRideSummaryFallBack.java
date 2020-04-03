/**
 * 
 */
package com.wxhj.cloud.feignClient.business.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.ViewRideSummaryClient;
import com.wxhj.cloud.feignClient.business.request.ViewRideSummaryListRequestDTO;

/**
 * @ClassName: ViewRideSummaryFallBack.java
 * @author: cya
 * @Date: 2020年2月6日 下午3:12:11 
 */
@Component
public class ViewRideSummaryFallBack implements ViewRideSummaryClient{

	@Override
	public WebApiReturnResultModel viewRideSummaryList(ViewRideSummaryListRequestDTO viewRideSummaryListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
