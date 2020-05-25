/**
 * @className AttendanceDayClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:55:04
 */
package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceDayClient;
import com.wxhj.cloud.feignClient.business.dto.GetAttendanceDaysDTO;
import com.wxhj.cloud.feignClient.business.request.ListAllAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @className AttendanceDayClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:55:04
 */
@Component
public class AttendanceDayClientFallBack implements AttendanceDayClient {

	@Override
	public WebApiReturnResultModel submitAttendanceDay(SubmitAttendanceDayRequestDTO submitAttendanceDay) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listAttendanceDay(ListAttendanceDayRequestDTO listAttendanceDayRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteAllAttendanceDay(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel selectAttendanceDayById(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listAllAttendDay(ListAllAttendanceDayRequestDTO listAllAttendanceDayRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel getAttendanceDays(GetAttendanceDaysDTO getAttendanceDaysDTO) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
