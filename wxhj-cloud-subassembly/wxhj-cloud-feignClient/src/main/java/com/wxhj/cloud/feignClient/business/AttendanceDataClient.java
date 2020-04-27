/**
 * @className AttendanceDataClient.java
 * @admin jwl
 * @date 2019年12月27日 下午3:56:44
 */
package com.wxhj.cloud.feignClient.business;

import io.swagger.annotations.ApiOperation;
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
	
	@PostMapping("/attendanceData/listDayAttendanceData")
	WebApiReturnResultModel listDayAttendanceData(
			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData);

//	@PostMapping("/attendanceData/listDayAttendanceMatchingData")
//	WebApiReturnResultModel listDayAttendanceMatchingData(
//			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData);
	
	@PostMapping("/attendanceData/listMonthAttendanceData")
	WebApiReturnResultModel listMonthAttendanceData(
			@Validated @RequestBody ListMonthAttendanceDataRequestDTO listAttendanceData);
	
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
}
