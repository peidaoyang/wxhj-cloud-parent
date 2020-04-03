/**
 * @className EntranceDayClient.java
 * @admin jwl
 * @date 2020年1月10日 下午2:43:06
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.EntranceDayClientFallBack;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceDayRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

/**
 * @className EntranceDayClient.java
 * @admin jwl
 * @date 2020年1月10日 下午2:43:06
 */
@Component
@FeignClient(name = "businessServer", fallback = EntranceDayClientFallBack.class)
public interface EntranceDayClient {
	@PostMapping("/entranceDay/submitEntranceDay")
	WebApiReturnResultModel submitEntranceDay(@RequestBody SubmitEntranceDayRequestDTO submitEntranceDay);

	@PostMapping("/entranceDay/listEntranceDay")
	WebApiReturnResultModel listEntranceDay(@RequestBody CommonListPageRequestDTO commonListPageRequest);

	@PostMapping("/entranceDay/deleteEntranceDay")
	WebApiReturnResultModel deleteEntranceDay(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/entranceDay/selectEntranceDayById")
	WebApiReturnResultModel selectEntranceDayById(@RequestBody CommonIdRequestDTO commonIdRequest);
	
	
	
	
	@PostMapping("/entranceDay/listEntranceDayOrganizeId")
	WebApiReturnResultModel listEntranceDayOrganizeId(
			@Validated @RequestBody CommonOrganizeRequestDTO commonOrganizeRequest);
}
