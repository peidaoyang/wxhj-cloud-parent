/**
 * @className AttendanceDataClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:53:39
 */
package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceDataClient;
import com.wxhj.cloud.feignClient.business.request.*;
import org.springframework.stereotype.Component;

/**
 * @className AttendanceDataClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:53:39
 */
@Component
public class AttendanceDataClientFallBack implements AttendanceDataClient {

	@Override
	public WebApiReturnResultModel listDayAttendanceData(ListDayAttendanceDataRequestDTO listAttendanceData) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listDayAttendanceMatchingData(ListDayAttendanceDataRequestDTO listAttendanceData) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listAccountAttendanceMatchingData(ListDayAttendanceDataRequestDTO listAttendanceData) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel listMonthAttendanceData(ListMonthAttendanceDataRequestDTO listAttendanceData) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel exportDayAttendanceDataExcel(DayAttendanceDataExcelRequestDTO dayAttendanceDataExcel) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listMonthDataByAccount(ListMonthDataByAccountRequestDTO listDataByAccount) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listDayDataByAccount(ListDayDataByAccountRequestDTO listDataByAccount) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listMonthAttendanceDataExcel(
			ListMonthAttendanceDataExcelRequestDTO listAttendanceExcelData) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listMonthAttendanceByAccountId(
			ListMonthAttendanceByAccountIdRequestDTO listAttendanceData) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
