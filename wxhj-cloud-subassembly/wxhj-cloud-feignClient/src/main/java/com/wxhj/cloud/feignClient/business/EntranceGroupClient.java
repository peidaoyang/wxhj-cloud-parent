/**
 * @className EntranceGroupClient.java
 * @admin jwl
 * @date 2020年1月13日 上午10:01:06
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.EntranceGroupClientFallBack;
import com.wxhj.cloud.feignClient.business.request.ListEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

/**
 * @className EntranceGroupClient.java
 * @admin jwl
 * @date 2020年1月13日 上午10:01:06
 */
@Component
@FeignClient(name = "businessServer", fallback = EntranceGroupClientFallBack.class)
public interface EntranceGroupClient {

	@PostMapping("/entranceGroup/submitEntranceGroup")
	WebApiReturnResultModel submitEntranceGroup(@RequestBody SubmitEntranceGroupRequestDTO submitEntranceGroupRequest);

	@PostMapping("/entranceGroup/listEntranceGroup")
	WebApiReturnResultModel listEntranceGroup(@RequestBody ListEntranceGroupRequestDTO listEntranceGroup);

	@PostMapping("/entranceGroup/deleteEntranceGroup")
	WebApiReturnResultModel deleteEntranceGroup(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/entranceGroup/selectEntranceGroup")
	WebApiReturnResultModel selectEntranceGroup(@RequestBody CommonIdRequestDTO commonIdRequest);

	/*
	 * @PostMapping("/entranceGroup/listEntranceOrganizeId") WebApiReturnResultModel
	 * listEntranceOrganizeId(
	 * 
	 * @Validated @RequestBody CommonOrganizeRequestDTO commonOrganizeRequest);
	 */
	@PostMapping("/entranceGroup/importMapEntrAuth")
	public WebApiReturnResultModel importMapEntrAuth(@Validated @RequestBody CommonIdRequestDTO commonIdRequest);
}
