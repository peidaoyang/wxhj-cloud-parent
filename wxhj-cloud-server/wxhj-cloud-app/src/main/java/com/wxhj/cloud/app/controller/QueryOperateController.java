/**
 * @className QueryOperateController.java
 * @admin jwl
 * @date 2020年1月15日 下午3:10:17
 */
package com.wxhj.cloud.app.controller;


import javax.annotation.Resource;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.feignClient.business.EntranceDataClient;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.request.MatchAttendanceDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.ListEntranceDataByAccountVO;
import com.wxhj.cloud.feignClient.business.vo.MatchAttendanceDataByAccountVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AttendanceDataClient;
import com.wxhj.cloud.feignClient.business.request.ListDayDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceByAccountIdRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className QueryOperateController.java
 * @admin jwl
 * @date 2020年1月15日 下午3:10:17
 */
@Api(tags = "考勤门禁业务")
@RestController
@RequestMapping("/queryOperate")
public class QueryOperateController {
	@Resource
	AttendanceDataClient attendanceDataClient;
	@Resource
	EntranceDataClient entranceDataClient;

	@ApiOperation(value = "考勤汇总报表")
	@PostMapping("/listMonthAttendance")
	@LcnTransaction
	public WebApiReturnResultModel listMonthAttendance(
			@Validated @RequestBody ListMonthAttendanceByAccountIdRequestDTO listAttendanceData) {
		return attendanceDataClient.listMonthAttendanceByAccountId(listAttendanceData);
	}

	@ApiOperation(value = "考勤明细报表")
	@PostMapping("/listDayDataByAccount")
	public WebApiReturnResultModel listDayDataByAccount(
			@Validated @RequestBody ListDayDataByAccountRequestDTO listDataByAccount) {
		return attendanceDataClient.listDayDataByAccount(listDataByAccount);
	}

	@ApiOperation(value = "查询门禁明细报表",response = ListEntranceDataByAccountVO.class)
	@PostMapping("/listEntranceDataByAccount")
	public WebApiReturnResultModel listEntranceDataByAccount(@RequestBody @Validated ListEntranceDataByAccountRequestDTO listEntranceDataByAccount){
		return entranceDataClient.listEntranceDataByAccount(listEntranceDataByAccount);
	}

	@ApiOperation(value = "获取打卡记录", response = MatchAttendanceDataByAccountVO.class)
	@PostMapping("/attendanceData/matchAttendanceDataByAccount")
	public WebApiReturnResultModel matchAttendanceDataByAccount(@RequestBody @Validated MatchAttendanceDataByAccountRequestDTO matchAttendanceDataByAccount){
		return attendanceDataClient.matchAttendanceDataByAccount(matchAttendanceDataByAccount);
	}


}
