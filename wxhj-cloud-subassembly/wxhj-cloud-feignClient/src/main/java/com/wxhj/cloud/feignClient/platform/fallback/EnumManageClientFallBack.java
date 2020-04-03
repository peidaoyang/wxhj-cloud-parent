package com.wxhj.cloud.feignClient.platform.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.platform.EnumManageClient;
import com.wxhj.cloud.feignClient.platform.request.EnumTypeListRequestDTO;

/**
 * @className EnumManageClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:24:50
 */
@Component
public class EnumManageClientFallBack implements EnumManageClient {


	@Override
	public WebApiReturnResultModel enumTypeList(EnumTypeListRequestDTO enumTypeListRequest) {

		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
