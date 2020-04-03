/**
 * 
 */
package com.wxhj.cloud.feignClient.platform;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.fallback.UserClientFallBack;

/**
 * @ClassName: UserClient.java
 * @author: cya
 * @Date: 2020年3月18日 下午1:10:23 
 */
@Component
@FeignClient(name = "platformServer", fallback = UserClientFallBack.class)
public interface UserClient {
	@PostMapping("/systemManage/user/userByIdList")
	WebApiReturnResultModel userByIdList(@RequestBody CommonIdListRequestDTO commonIdListRequest);
}
