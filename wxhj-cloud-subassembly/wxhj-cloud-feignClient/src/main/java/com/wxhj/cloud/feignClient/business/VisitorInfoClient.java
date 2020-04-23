package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.feignClient.business.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.VisitorInfoClientFallBack;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

/**
 * @ClassName: VisitorInfoClient.java
 * @author: cya
 * @Date: 2020年2月11日 下午4:35:16
 */
@Component
@FeignClient(name = "businessServer", fallback = VisitorInfoClientFallBack.class)
public interface VisitorInfoClient {
	@PostMapping("/visitorInfo/visitorInfoList")
	WebApiReturnResultModel visitorInfoList(@RequestBody VisitorInfoListRequestDTO visitorInfoListRequest);

	@PostMapping("/visitorInfo/submitVisitor")
	WebApiReturnResultModel submitVisitor(@RequestBody SubmitVisitorRequestDTO submitVisitorRequest);

	@PostMapping("/visitorInfo/checkVis")
	WebApiReturnResultModel checkVis(@RequestBody CheckVisRequestDTO checkVisRequest);

	@PostMapping("/visitorInfo/visitorInfoApp")
	WebApiReturnResultModel visitorInfoApp(@RequestBody VisitorInfoAppRequestDTO visitorInfoAppRequest);

	@PostMapping("/visitorInfo/visitorInfoPos")
	WebApiReturnResultModel visitorInfoPos(@RequestBody VisitorInfoPosRequestDTO visitorInfoPosRequest);

	@PostMapping("/visitorInfo/deleteVisitor")
	WebApiReturnResultModel deleteVisitor(@RequestBody CommonIdListRequestDTO commonIdListRequest);
}
