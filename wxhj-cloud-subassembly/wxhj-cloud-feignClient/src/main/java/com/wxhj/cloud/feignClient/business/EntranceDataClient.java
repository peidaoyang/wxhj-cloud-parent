/**
 * @className EntranceDataClient.java
 * @admin jwl
 * @date 2020年1月20日 上午8:58:37
 */
package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.feignClient.business.request.ListEntranceDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.EntranceDataClientFallBack;
import com.wxhj.cloud.feignClient.business.request.ListDetailEntranceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataExcelRequestDTO;


/**
 * @className EntranceDataClient.java
 * @admin jwl
 * @date 2020年1月20日 上午8:58:37
 */
@Component
@FeignClient(name = "businessServer",fallback=EntranceDataClientFallBack.class)
public interface EntranceDataClient {
	@PostMapping("/entranceData/listDetailEntranceData")
	WebApiReturnResultModel listDetailEntranceData(
			@RequestBody ListDetailEntranceDataRequestDTO listDetaileEntranceData);
	@PostMapping("/entranceData/listDetailEntranceDataExcel")
	WebApiReturnResultModel listDetailEntranceDataExcel(
			@RequestBody ListEntranceDataExcelRequestDTO listEntranceDataExcalRequest);

	@PostMapping("/entranceData/totayEntrance")
	WebApiReturnResultModel totayEntrance(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/entranceData/listEntranceDataByAccount")
	WebApiReturnResultModel listEntranceDataByAccount(@RequestBody @Validated ListEntranceDataByAccountRequestDTO listEntranceDataByAccount);
}
