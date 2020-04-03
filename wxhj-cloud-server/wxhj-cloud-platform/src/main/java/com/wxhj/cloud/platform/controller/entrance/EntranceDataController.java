/**
 * @className EntranceDataController.java
 * @admin jwl
 * @date 2020年1月20日 上午9:06:00
 */
package com.wxhj.cloud.platform.controller.entrance;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.EntranceDataClient;
import com.wxhj.cloud.feignClient.business.request.ListDetailEntranceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataExcelRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className EntranceDataController.java
 * @admin jwl
 * @date 2020年1月20日 上午9:06:00
 */
@Api(tags = "通行记录接口")
@RestController
@RequestMapping("/entranceManage/entranceData")
public class EntranceDataController {
	@Resource
	EntranceDataClient entranceDataClient;
	
	@ApiOperation("查询门禁明细报表")
	@PostMapping("/listDetailEntranceData")
	public WebApiReturnResultModel listDetailEntranceData(
			@Validated @RequestBody ListDetailEntranceDataRequestDTO listDetaileEntranceData) {
		return entranceDataClient.listDetailEntranceData(listDetaileEntranceData);
	}
	
	@ApiOperation("导出门禁明细报表")
	@PostMapping("/listDetailEntranceDataExcel")
	public WebApiReturnResultModel listDetailEntranceDataExcel(
			@Validated @RequestBody ListEntranceDataExcelRequestDTO listEntranceDataExcalRequest) {
		return entranceDataClient.listDetailEntranceDataExcel(listEntranceDataExcalRequest);
	}
}
