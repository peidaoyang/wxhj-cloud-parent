/**
 * @className AttendanceDayClient.java
 * @admin jwl
 * @date 2019年12月18日 上午9:28:19
 */
package com.wxhj.cloud.feignClient.business;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.AttendanceDayClientFallBack;
//import com.wxhj.cloud.feignClient.business.request.DeleteAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

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
	public WebApiReturnResultModel listAttendanceDay(@RequestBody CommonListPageRequestDTO commonListPageRequest);

	@PostMapping("/attendanceDay/deleteAllAttendanceDay")
	WebApiReturnResultModel deleteAllAttendanceDay(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/attendanceDay/selectAttendanceDayById")
	WebApiReturnResultModel selectAttendanceDayById(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/attendanceDay/listAllAttendDay")
	public WebApiReturnResultModel listAllAttendDay(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest);
}
