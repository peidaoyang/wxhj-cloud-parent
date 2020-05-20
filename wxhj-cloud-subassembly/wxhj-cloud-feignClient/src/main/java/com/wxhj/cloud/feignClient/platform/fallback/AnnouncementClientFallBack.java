package com.wxhj.cloud.feignClient.platform.fallback;

import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.AnnouncementClient;

@Component
public class AnnouncementClientFallBack implements AnnouncementClient {

	@Override
	public WebApiReturnResultModel appAnnouncementList(CommonListPageRequestDTO commonListPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel selectById(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
