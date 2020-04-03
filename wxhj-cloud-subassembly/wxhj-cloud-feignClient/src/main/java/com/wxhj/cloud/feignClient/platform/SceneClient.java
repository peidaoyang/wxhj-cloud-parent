/**
 * @className SceneClient.java
 * @admin jwl
 * @date 2019年12月26日 下午5:43:00
 */
package com.wxhj.cloud.feignClient.platform;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.fallback.SceneClientFallBack;
//import com.wxhj.cloud.feignClient.platform.request.ListSceneByIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.request.ListSceneInfoByIdRequestDTO;

/**
 * @className SceneClient.java
 * @admin jwl
 * @date 2019年12月26日 下午5:43:00
 */
@FeignClient(name = "platformServer", fallback = SceneClientFallBack.class)
public interface SceneClient {

	@PostMapping("/device/scene/listSceneByIdList")
	WebApiReturnResultModel listSceneByIdList(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/device/scene/selectSceneInfoById")
	WebApiReturnResultModel selectSceneInfoById(@RequestBody() ListSceneInfoByIdRequestDTO selectSceneInfoByIdRequest);

}
