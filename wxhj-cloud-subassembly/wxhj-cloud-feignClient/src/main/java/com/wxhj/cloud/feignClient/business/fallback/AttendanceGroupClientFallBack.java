package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceGroupClient;
import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceGroupRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @className AttendanceGroupClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:56:49
 */
@Component
public class AttendanceGroupClientFallBack implements AttendanceGroupClient {

	@Override
	public WebApiReturnResultModel submitAttendanceGroup(SubmitAttendanceGroupRequestDTO submitAttendanceGroup) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	
	@Override
	public WebApiReturnResultModel deleteAttendanceGroup(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel listAttendanceGroup(ListAttendanceDayRequestDTO listAttendanceDayRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel selectAttendanceById(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel listAllAttendGroup(CommonOrganizeRequestDTO commonOrganizeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel insertCurrentAttendance(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
