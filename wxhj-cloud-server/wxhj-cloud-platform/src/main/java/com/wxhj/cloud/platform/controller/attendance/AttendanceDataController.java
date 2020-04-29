/**
 * @className AttendanceDataController.java
 * @admin admin
 * @date 2019年12月27日  下午4:02:25
 */
package com.wxhj.cloud.platform.controller.attendance;

import javax.annotation.Resource;

import com.wxhj.cloud.feignClient.business.vo.ViewAttendanceSummaryMatchingFinalVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceDataClient;
import com.wxhj.cloud.feignClient.business.request.DayAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListDayAttendanceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.ListDayAttendanceDataVO;
import com.wxhj.cloud.feignClient.business.vo.ListMonthAttendanceDataVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className AttendanceDataController.java
 * @admin admin
 * @date 2019年12月27日  下午4:02:25
 */
@RestController
@RequestMapping("/attendanceManage/attendanceData")
@Api(tags="考勤报表接口")
public class AttendanceDataController {
	@Resource
	AttendanceDataClient attendanceDataClient;
	
	@ApiOperation(value="分页查询考勤明细",response = ListDayAttendanceDataVO.class)
	@PostMapping("/listDayAttendanceData")
	@LcnTransaction
	public WebApiReturnResultModel listDayAttendanceData(
			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData) {
		return attendanceDataClient.listDayAttendanceData(listAttendanceData);
	}
	
	@ApiOperation("导出考勤明细报表")
	@PostMapping("/exportDayAttendanceDataExcel")
	@LcnTransaction
	public WebApiReturnResultModel exportDayAttendanceDataExcel(
			@Validated @RequestBody DayAttendanceDataExcelRequestDTO dayAttendanceDataExcel) {
		return attendanceDataClient.exportDayAttendanceDataExcel(dayAttendanceDataExcel);
	}

	@ApiOperation(value = "分页查询考勤汇总信息",response = ViewAttendanceSummaryMatchingFinalVO.class)
	@PostMapping("/listDayAttendanceMatchingData")
	public WebApiReturnResultModel listDayAttendanceMatchingData(@Validated @RequestBody ListDayAttendanceDataRequestDTO listDayAttendanceDataRequest)
	{
		return attendanceDataClient.listDayAttendanceMatchingData(listDayAttendanceDataRequest);
	}



	@ApiOperation(value = "分页查询考勤汇总信息",response = ListMonthAttendanceDataVO.class)
	@PostMapping("/listMonthAttendanceData")
	@LcnTransaction
	public WebApiReturnResultModel listMonthAttendanceData(
			@Validated @RequestBody ListMonthAttendanceDataRequestDTO listAttendanceData) {
		return attendanceDataClient.listMonthAttendanceData(listAttendanceData);
	}
	
	
	@ApiOperation("考勤汇总报表导出")
	@PostMapping("/listMonthAttendanceDataExcel")
	@LcnTransaction
	public WebApiReturnResultModel listMonthAttendanceDataExcel(@Validated @RequestBody 
			ListMonthAttendanceDataExcelRequestDTO listAttendanceExcelData) {
		return attendanceDataClient.listMonthAttendanceDataExcel(listAttendanceExcelData);
	}
	
	
}
