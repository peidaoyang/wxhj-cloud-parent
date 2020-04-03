/**
 * @className SceneClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:48:49
 */
package com.wxhj.cloud.feignClient.platform.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.SceneClient;
//import com.wxhj.cloud.feignClient.platform.request.ListSceneByIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.request.ListSceneInfoByIdRequestDTO;

/**
 * @className SceneClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:48:49
 */
@Component
public class SceneClientFallBack implements SceneClient {

	@Override
	public WebApiReturnResultModel selectSceneInfoById(ListSceneInfoByIdRequestDTO selectSceneInfoByIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listSceneByIdList(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
