/**
 * @className OrganizeClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:48:36
 */
package com.wxhj.cloud.feignClient.platform.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizeClient;

/**
 * @className OrganizeClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:48:36
 */
@Component
public class OrganizeClientFallBack implements OrganizeClient {

	@Override
	public WebApiReturnResultModel listOrganizeByIdList(CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
