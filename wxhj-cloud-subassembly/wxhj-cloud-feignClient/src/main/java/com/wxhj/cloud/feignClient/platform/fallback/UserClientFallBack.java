/**
 * 
 */
package com.wxhj.cloud.feignClient.platform.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.UserClient;

/**
 * @ClassName: UserClientFallBack.java
 * @author: cya
 * @Date: 2020年3月18日 下午1:11:02
 */
@Component
public class UserClientFallBack implements UserClient {

	@Override
	public WebApiReturnResultModel userByIdList(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
}
