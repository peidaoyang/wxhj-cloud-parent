package com.wxhj.cloud.platform.controller.shuttleBus;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.vo.AccountInfoVO;
import com.wxhj.cloud.feignClient.business.DriverInfoClient;
import com.wxhj.cloud.feignClient.business.request.DriverListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitDriverInfoRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.DriverListVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: DriverInfoController.java
 * @author: cya
 * @Date: 2020年2月4日 下午6:35:43
 */
@RestController
@RequestMapping("/shuttleBusManage/driver")
@Api(tags = "司机接口")
public class DriverInfoController {
	@Resource
	DriverInfoClient driverInfoClient;

	@PostMapping("/driverList")
	@ApiOperation(value="获取司机信息",response=DriverListVO.class)
	@LcnTransaction
	public WebApiReturnResultModel driverList(@RequestBody @Validated DriverListRequestDTO driverList) {
		return driverInfoClient.driverList(driverList);
	}


	@PostMapping("/submitDriverInfo")
	@ApiOperation("新增/修改 司机信息")
	@LcnTransaction
	public WebApiReturnResultModel submitDriverInfo(
			@RequestBody @Validated SubmitDriverInfoRequestDTO submitDriverInfo) {
		return driverInfoClient.submitDriverInfo(submitDriverInfo);
	}

	@PostMapping("/deleteDriver")
	@ApiOperation(value="删除司机",response = AccountInfoVO.class)
	@LcnTransaction
	public WebApiReturnResultModel deleteDriver(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
		return driverInfoClient.deleteDriver(commonIdListRequest);
	}
	
//	@PostMapping("/driverInfoById")
//	@ApiOperation("根据id获取司机信息")
//	@LcnTransaction
//	public WebApiReturnResultModel driverInfoById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
//		return driverInfoClient.driverInfoById(commonIdRequest);
//	}
}
