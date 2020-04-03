package com.wxhj.cloud.feignClient.platform.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.AnnouncementClient;

@Component
public class AnnouncementClientFallBack implements AnnouncementClient {

	@Override
	public WebApiReturnResultModel newestAnnouncement(CommonOrganizeRequestDTO commonOrganizeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
