/**
 * @className AttendanceDayClient.java
 * @admin jwl
 * @date 2019年12月18日 上午9:28:19
 */
package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.dto.GetAttendanceDaysDTO;
import com.wxhj.cloud.feignClient.business.fallback.AttendanceDayClientFallBack;
import com.wxhj.cloud.feignClient.business.request.ListAllAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.GetAttendanceDaysVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.wxhj.cloud.feignClient.business.request.DeleteAttendanceDayRequestDTO;

/**
 * @className AttendanceDayClient.java
 * @admin jwl
 * @date 2019年12月18日 上午9:28:19
 */
@Component
@FeignClient(name = "businessServer", fallback = AttendanceDayClientFallBack.class)
public interface AttendanceDayClient {
	@PostMapping("/attendanceDay/submitAttendanceDay")
	WebApiReturnResultModel submitAttendanceDay(@RequestBody SubmitAttendanceDayRequestDTO submitAttendanceDay);

	@PostMapping("/attendanceDay/listAttendanceDay")
	WebApiReturnResultModel listAttendanceDay(@RequestBody ListAttendanceDayRequestDTO listAttendanceDayRequest);

	@PostMapping("/attendanceDay/deleteAllAttendanceDay")
	WebApiReturnResultModel deleteAllAttendanceDay(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/attendanceDay/selectAttendanceDayById")
	WebApiReturnResultModel selectAttendanceDayById(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/attendanceDay/listAllAttendDay")
	WebApiReturnResultModel listAllAttendDay(@RequestBody ListAllAttendanceDayRequestDTO listAllAttendanceDayRequest);

	@ApiOperation(value = "根据账户id获取时间段内考勤规则", response = GetAttendanceDaysVO.class)
	@PostMapping("/attendanceDay/getAttendanceDays")
	WebApiReturnResultModel getAttendanceDays(@RequestBody @Validated GetAttendanceDaysDTO getAttendanceDaysDTO);
}
