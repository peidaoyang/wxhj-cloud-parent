/**
 * 
 */
package com.wxhj.cloud.app.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.FlightInfoClient;
import com.wxhj.cloud.feignClient.business.RideInfoClient;
import com.wxhj.cloud.feignClient.business.request.AppFlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.RideInfoListByAccoutIdRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: FlightController.java
 * @author: cya
 * @Date: 2020年2月6日 下午6:22:16
 */
@RestController
@RequestMapping("/flight")
@Api(tags = "班车信息接口")
public class FlightController {
	@Resource
	FlightInfoClient flightInfoClient;
	@Resource
	RideInfoClient rideInfoClient;

	@PostMapping("/appFlightList")
	@ApiOperation("班车信息分页查询")
	public WebApiReturnResultModel appFlightList(@RequestBody @Validated AppFlightListRequestDTO appFlightListRequest) {
		return flightInfoClient.appFlightList(appFlightListRequest);
	}

	@PostMapping("/rideInfoListByAccoutId")
	@ApiOperation("乘车信息查询")
	public WebApiReturnResultModel rideInfoListByAccoutId(
			@RequestBody @Validated RideInfoListByAccoutIdRequestDTO rideInfoListByAccoutIdRequest) {
		return rideInfoClient.rideInfoListByAccoutId(rideInfoListByAccoutIdRequest);
	}
}
