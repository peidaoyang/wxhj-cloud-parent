/**
 * @className OrganizeClient.java
 * @admin jwl
 * @date 2019年12月11日 下午1:20:39
 */
package com.wxhj.cloud.feignClient.platform;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.fallback.OrganizeClientFallBack;

/**
 * @className OrganizeClient.java
 * @admin jwl
 * @date 2019年12月11日 下午1:20:39
 */
@Component
@FeignClient(name = "platformServer", fallback = OrganizeClientFallBack.class)
public interface OrganizeClient {

	@PostMapping("/systemManage/organize/listOrganizeByIdList")
	WebApiReturnResultModel listOrganizeByIdList(
			@RequestBody CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest);

}
