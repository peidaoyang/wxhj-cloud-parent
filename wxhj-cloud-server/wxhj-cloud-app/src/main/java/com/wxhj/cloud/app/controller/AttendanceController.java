/**
 * @className QueryOperateController.java
 * @admin jwl
 * @date 2020年1月15日 下午3:10:17
 */
package com.wxhj.cloud.app.controller;


import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceDataClient;
import com.wxhj.cloud.feignClient.business.request.ListDayDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceByAccountIdRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className QueryOperateController.java
 * @admin jwl
 * @date 2020年1月15日 下午3:10:17
 */
@Api(tags = "app考勤相关业务")
@RestController
@RequestMapping("/queryOperate")
public class AttendanceController {
	@Resource
	AttendanceDataClient attendanceDataClient;
	
	@ApiOperation(value = "获取登录用户的汇总报表")
	@PostMapping("/listMonthDataByAccount")
	public WebApiReturnResultModel listMonthDataByAccount(
			@Validated @RequestBody ListMonthAttendanceByAccountIdRequestDTO listAttendanceData) {
		return attendanceDataClient.listMonthAttendanceByAccountId(listAttendanceData);
	}
	
	@ApiOperation(value = "获取登录用户的明细报表")
	@PostMapping("/listDayDataByAccount")
	public WebApiReturnResultModel listDayDataByAccount(
			@Validated @RequestBody ListDayDataByAccountRequestDTO listDataByAccount) {
		return attendanceDataClient.listDayDataByAccount(listDataByAccount);
	}
	
	
}
