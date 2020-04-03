/**
 * @className RealNameAuthenticClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:51:00
 */
package com.wxhj.cloud.feignClient.account.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.RealNameAuthenticClient;
import com.wxhj.cloud.feignClient.account.request.RealNameAuthRequestDTO;

/**
 * @className RealNameAuthenticClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:51:00
 */
@Component
public class RealNameAuthenticClientFallBack implements RealNameAuthenticClient{

	@Override
	public WebApiReturnResultModel realNameAuthentication(RealNameAuthRequestDTO realNameAuthenticationRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
