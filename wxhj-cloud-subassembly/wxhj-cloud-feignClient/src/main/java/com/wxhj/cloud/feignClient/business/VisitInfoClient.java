/**
 * 
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.VisitInfoClientFallBack;
import com.wxhj.cloud.feignClient.business.request.VisitInfoListRequestDTO;

/**
 * @ClassName: VisitInfoClient.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:48:09 
 */
@Component
@FeignClient(name = "businessServer",fallback=VisitInfoClientFallBack.class)
public interface VisitInfoClient {
	@PostMapping("/visitInfo/visitInfoList")
	WebApiReturnResultModel visitInfoList(@RequestBody VisitInfoListRequestDTO vInfoListRequest);
}
