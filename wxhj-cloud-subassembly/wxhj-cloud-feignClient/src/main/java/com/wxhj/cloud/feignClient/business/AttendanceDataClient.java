/**
 * @className AttendanceDataClient.java
 * @admin jwl
 * @date 2019年12月27日 下午3:56:44
 */
package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.AttendanceDataClientFallBack;
import com.wxhj.cloud.feignClient.business.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className AttendanceDataClient.java
 * @admin jwl
 * @date 2019年12月27日 下午3:56:44
 */
@Component
@FeignClient(name = "businessServer",fallback=AttendanceDataClientFallBack.class)
public interface AttendanceDataClient {
	
	@PostMapping("/attendanceData/listDayAttendanceData")
	WebApiReturnResultModel listDayAttendanceData(
			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData);

	@PostMapping("/attendanceData/listDayAttendanceMatchingData")
	WebApiReturnResultModel listDayAttendanceMatchingData(
			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData);
	
//	@PostMapping("/attendanceData/listMonthAttendanceData")
//	WebApiReturnResultModel listMonthAttendanceData(
//			@Validated @RequestBody ListMonthAttendanceDataRequestDTO listAttendanceData);
//
	@PostMapping("/attendanceData/listMonthAttendanceByAccountId")
	WebApiReturnResultModel listMonthAttendanceByAccountId(
			@Validated @RequestBody ListMonthAttendanceByAccountIdRequestDTO listAttendanceData);
	
	@PostMapping("/attendanceData/listMonthAttendanceDataExcel")
	WebApiReturnResultModel listMonthAttendanceDataExcel(
			@Validated @RequestBody ListMonthAttendanceDataExcelRequestDTO listAttendanceExcelData);
	
	@PostMapping("/attendanceData/exportDayAttendanceDataExcel")
	WebApiReturnResultModel exportDayAttendanceDataExcel(
			@Validated @RequestBody DayAttendanceDataExcelRequestDTO dayAttendanceDataExcel);
	
	@PostMapping("/attendanceData/listMonthDataByAccount")
	WebApiReturnResultModel listMonthDataByAccount(
			@RequestBody ListMonthDataByAccountRequestDTO listDataByAccount);
	
	@PostMapping("/attendanceData/listDayDataByAccount")
	WebApiReturnResultModel listDayDataByAccount(
			@RequestBody ListDayDataByAccountRequestDTO listDataByAccount);

	@PostMapping("/attendanceData/matchAttendanceDataByAccount")
	WebApiReturnResultModel matchAttendanceDataByAccount(@RequestBody @Validated MatchAttendanceDataByAccountRequestDTO matchAttendanceDataByAccount);
}
