/**
 * @className AttendanceDataClient.java
 * @admin jwl
 * @date 2019年12月27日 下午3:56:44
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.AttendanceDataClientFallBack;
import com.wxhj.cloud.feignClient.business.request.DayAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListDayAttendanceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListDayDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthDataByAccountRequestDTO;

/**
 * @className AttendanceDataClient.java
 * @admin jwl
 * @date 2019年12月27日 下午3:56:44
 */
@Component
@FeignClient(name = "businessServer",fallback=AttendanceDataClientFallBack.class)
public interface AttendanceDataClient {
	
	@PostMapping("/attenanceData/listDayAttendanceData")
	WebApiReturnResultModel listDayAttendanceData(
			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData);
	
	@PostMapping("/attenanceData/listMonthAttendanceData")
	WebApiReturnResultModel listMonthAttendanceData(
			@Validated @RequestBody ListMonthAttendanceDataRequestDTO listAttendanceData);
	
	@PostMapping("/attenanceData/listMonthAttendanceByAccountId")
	WebApiReturnResultModel listMonthAttendanceByAccountId(
			@Validated @RequestBody ListMonthAttendanceByAccountIdRequestDTO listAttendanceData);
	
	@PostMapping("/attenanceData/listMonthAttendanceDataExcel")
	WebApiReturnResultModel listMonthAttendanceDataExcel(
			@Validated @RequestBody ListMonthAttendanceDataExcelRequestDTO listAttendanceExcelData);
	
	@PostMapping("/attenanceData/exportDayAttendanceDataExcel")
	WebApiReturnResultModel exportDayAttendanceDataExcel(
			@Validated @RequestBody DayAttendanceDataExcelRequestDTO dayAttendanceDataExcel);
	
	@PostMapping("/attenanceData/listMonthDataByAccount")
	WebApiReturnResultModel listMonthDataByAccount(
			@RequestBody ListMonthDataByAccountRequestDTO listDataByAccount);
	
	@PostMapping("/attenanceData/listDayDataByAccount")
	WebApiReturnResultModel listDayDataByAccount(
			@RequestBody ListDayDataByAccountRequestDTO listDataByAccount);
}
