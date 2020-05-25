package com.wxhj.cloud.platform.controller.attendance;
/**
 * @className AttendanceDayController.java
 * @admin jwl
 * @date 2019年12月18日  上午9:47:03
 */

import javax.annotation.Resource;

import com.wxhj.cloud.feignClient.business.dto.GetAttendanceDaysDTO;
import com.wxhj.cloud.feignClient.business.request.ListAllAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.GetAttendanceDaysVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceDayClient;
//import com.wxhj.cloud.feignClient.business.request.DeleteAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.AttendanceDayAllVO;
import com.wxhj.cloud.feignClient.business.vo.ListAttendanceDayVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className AttendanceDayController.java
 * @admin jwl
 * @date 2019年12月18日  上午9:47:03
 */
@RestController
@RequestMapping("/attendanceManage/attendanceDay")
@Api(tags="班次接口")
public class AttendanceDayController {
	@Resource
	AttendanceDayClient attendanceDayClient;
	
	@ApiOperation(value="分页查询班次信息",response = ListAttendanceDayVO.class)
	@PostMapping("/listAttendanceDay")
	@LcnTransaction
	public WebApiReturnResultModel listAttendanceDay(
			@Validated @RequestBody ListAttendanceDayRequestDTO listAttendanceDayRequest) {
		return attendanceDayClient.listAttendanceDay(listAttendanceDayRequest);
	}
	
	
	@ApiOperation("编辑班次")
	@PostMapping("/submitAttendanceDay")
	@LcnTransaction
	public WebApiReturnResultModel submitAttendanceDay(
			@Validated @RequestBody SubmitAttendanceDayRequestDTO submitAttendanceDay) {
		return attendanceDayClient.submitAttendanceDay(submitAttendanceDay);
	}
	
	@ApiOperation("根据编号获取班次")
	@PostMapping("/selectAttendanceDayById")
	@LcnTransaction
	public WebApiReturnResultModel selectAttendanceDayById(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return attendanceDayClient.selectAttendanceDayById(commonIdRequest);
	}
	
	@ApiOperation("删除选中班次")
	@PostMapping("/deleteAllAttendanceDay")
	@LcnTransaction
	public WebApiReturnResultModel deleteAllAttendanceDay(
			@Validated @RequestBody CommonIdListRequestDTO commonIdListRequest) {
		return attendanceDayClient.deleteAllAttendanceDay(commonIdListRequest);
	}
	
	
	@PostMapping("/listAllAttendDay")
	@ApiOperation(value="按组织编号获取班次",response=AttendanceDayAllVO.class)
	@LcnTransaction
	public WebApiReturnResultModel listAllAttendDay(
			@RequestBody ListAllAttendanceDayRequestDTO listAllAttendanceDayRequest) {
		return attendanceDayClient.listAllAttendDay(listAllAttendanceDayRequest);
	}

	@ApiOperation(value = "根据账户id获取时间段内考勤规则", response = GetAttendanceDaysVO.class)
	@PostMapping("/getAttendanceDays")
	public WebApiReturnResultModel getAttendanceDays(@RequestBody @Validated GetAttendanceDaysDTO getAttendanceDaysDTO) {
		return attendanceDayClient.getAttendanceDays(getAttendanceDaysDTO);
	}
}
