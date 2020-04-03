/**
 * @className RealNameAuthenticClient.java
 * @admin jwl
 * @date 2019年12月12日 下午5:41:14
 */
package com.wxhj.cloud.feignClient.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.MapperClientFallBack;
import com.wxhj.cloud.feignClient.account.fallback.RealNameAuthenticClientFallBack;
import com.wxhj.cloud.feignClient.account.request.RealNameAuthRequestDTO;


/**
 * @className RealNameAuthenticClient.java
 * @admin jwl
 * @date 2019年12月12日 下午5:41:14
 */
@FeignClient(name = "accountServer",fallback=RealNameAuthenticClientFallBack.class)
public interface RealNameAuthenticClient {
	@PostMapping(value = "/realNameAuthentic/realNameAuthentication")
	WebApiReturnResultModel realNameAuthentication(
			@RequestBody RealNameAuthRequestDTO realNameAuthenticationRequest);
}
