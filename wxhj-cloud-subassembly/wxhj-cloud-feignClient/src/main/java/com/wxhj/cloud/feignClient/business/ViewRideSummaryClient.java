/**
 * 
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.ViewRideSummaryFallBack;
import com.wxhj.cloud.feignClient.business.request.ViewRideSummaryListRequestDTO;

/**
 * @ClassName: ViewRideSummaryClient.java
 * @author: cya
 * @Date: 2020年2月6日 下午3:10:28 
 */
@Component
@FeignClient(name = "businessServer",fallback=ViewRideSummaryFallBack.class)
public interface ViewRideSummaryClient {
	@PostMapping("/viewRideSummary/viewRideSummaryList")
	WebApiReturnResultModel viewRideSummaryList(@RequestBody ViewRideSummaryListRequestDTO viewRideSummaryListRequest); 
}
