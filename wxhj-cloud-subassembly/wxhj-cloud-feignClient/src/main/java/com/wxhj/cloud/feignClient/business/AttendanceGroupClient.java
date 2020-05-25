/**
 * @className AttendanceGroupClient.java
 * @admin jwl
 * @date 2019年12月18日 上午9:27:32
 */
package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.AttendanceGroupClientFallBack;
import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceGroupRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @className AttendanceGroupClient.java
 * @admin jwl
 * @date 2019年12月18日 上午9:27:32
 */
@Component
@FeignClient(name = "businessServer", fallback = AttendanceGroupClientFallBack.class)
public interface AttendanceGroupClient {
	@PostMapping("/attendanceGroup/listAttendanceGroup")
	WebApiReturnResultModel listAttendanceGroup(@RequestBody ListAttendanceDayRequestDTO listAttendanceDayRequest);
	
	@PostMapping("/attendanceGroup/submitAttendanceGroup")
	WebApiReturnResultModel submitAttendanceGroup(@RequestBody SubmitAttendanceGroupRequestDTO submitAttendanceGroup);

	@PostMapping("/attendanceGroup/deleteAttendanceGroup")
	WebApiReturnResultModel deleteAttendanceGroup(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/attendanceGroup/insertCurrentAttendance")
	WebApiReturnResultModel insertCurrentAttendance(@RequestBody CommonIdRequestDTO commonIdRequest);
	
	
	
	
	@PostMapping("/attendanceGroup/selectAttendanceById")
	WebApiReturnResultModel selectAttendanceById(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/attendanceGroup/listAllAttendGroup")
	WebApiReturnResultModel listAllAttendGroup(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest);
}
