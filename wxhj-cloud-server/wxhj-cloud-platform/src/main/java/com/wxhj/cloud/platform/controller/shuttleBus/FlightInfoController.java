/**
 * 
 */
package com.wxhj.cloud.platform.controller.shuttleBus;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.FlightInfoClient;
import com.wxhj.cloud.feignClient.business.request.FlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitFlightRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.FlightListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: FlightInfoController.java
 * @author: cya
 * @Date: 2020年2月5日 下午6:28:41
 */
@RestController
@RequestMapping("/shuttleBusManage/flight")
@Api(tags = "班次接口")
public class FlightInfoController {
	@Resource
	FlightInfoClient flightInfoClient;

	@ApiOperation(value="分页查询 班次选项",response = FlightListVO.class)
	@PostMapping("/flightList")
	@LcnTransaction
	public WebApiReturnResultModel flightList(@RequestBody @Validated FlightListRequestDTO flightListRequest) {
		return flightInfoClient.flightList(flightListRequest);
	}

	@ApiOperation("新增/修改 班次")
	@PostMapping("/submitFlight")
	@LcnTransaction
	public WebApiReturnResultModel submitFlight(@RequestBody @Validated SubmitFlightRequestDTO submitFlightRequest) {
		return flightInfoClient.submitFlight(submitFlightRequest);
	}

	@ApiOperation("删除班次")
	@PostMapping("/deleteFlight")
	@LcnTransaction
	public WebApiReturnResultModel deleteFlight(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		return flightInfoClient.deleteFlight(commonIdListRequest);
	}
	
	
//	@ApiOperation("根据班次id查询班次选项")
//	@PostMapping("/flightById")
//	@LcnTransaction
//	public WebApiReturnResultModel flightById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
//		return flightInfoClient.flightById(commonIdRequest);
//	}
}
