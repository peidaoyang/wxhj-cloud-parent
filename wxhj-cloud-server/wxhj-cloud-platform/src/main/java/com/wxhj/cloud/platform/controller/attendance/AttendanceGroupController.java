
package com.wxhj.cloud.platform.controller.attendance;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceGroupClient;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.response.AttendanceGroupResponseDTO;
import com.wxhj.cloud.feignClient.business.vo.ListAttendanceGroupVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className AttendanceGroupController.java
 * @admin jwl
 * @date 2019年12月18日 上午9:48:22
 */
@RestController
@RequestMapping("/attendanceManage/attendanceGroup")
@Api(tags = "考勤规则接口")
public class AttendanceGroupController {
	@Resource
	AttendanceGroupClient attendanceGroupClient;
	
	@PostMapping("/listAttendanceGroup")
	@ApiOperation(value="获取考勤组",response = ListAttendanceGroupVO.class)
	@LcnTransaction
	public WebApiReturnResultModel listAttendanceGroup(
			@Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
		return attendanceGroupClient.listAttendanceGroup(commonListPageRequest);
	}
	
	@PostMapping("/selectAttendanceById")
	@ApiOperation(value="按编号获取考勤组",response = AttendanceGroupResponseDTO.class)
	@LcnTransaction
	public WebApiReturnResultModel selectAttendanceById(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return attendanceGroupClient.selectAttendanceById(commonIdRequest);
	}

	
	@PostMapping("/submitAttendanceGroup")
	@ApiOperation("编辑考勤组")
	@LcnTransaction
	public WebApiReturnResultModel submitAttendanceGroup(
			@Validated @RequestBody SubmitAttendanceGroupRequestDTO submitAttendanceGroup) {
		return attendanceGroupClient.submitAttendanceGroup(submitAttendanceGroup);
	}
	
	@ApiOperation("增加应用考勤权限信息")
	@PostMapping("/insertCurrentAttendance")
	@LcnTransaction
	public WebApiReturnResultModel insertCurrentAttendance(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return attendanceGroupClient.insertCurrentAttendance(commonIdRequest);
	}
	
	@PostMapping("/deleteAttendanceGroup")
	@ApiOperation("删除选中考勤组")
	@LcnTransaction
	public WebApiReturnResultModel deleteAttendanceGroup(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return attendanceGroupClient.deleteAttendanceGroup(commonIdRequest);
	}
	
	
	
	
	
	



//	@PostMapping("/listAllAttendGroup")
//	@ApiOperation("按组织编号获取考勤组")
//	@LcnTransaction
//	public WebApiReturnResultModel listAllAttendGroup(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
//		return attendanceGroupClient.listAllAttendGroup(commonOrganizeRequest);
//	}
//

}
