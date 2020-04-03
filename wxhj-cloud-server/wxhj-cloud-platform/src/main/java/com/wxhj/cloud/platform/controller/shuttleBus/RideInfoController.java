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
import com.wxhj.cloud.feignClient.business.RideInfoClient;
import com.wxhj.cloud.feignClient.business.request.RideInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.RideInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: RideInfoController.java
 * @author: cya
 * @Date: 2020年2月6日 下午1:45:28 
 */
@RestController
@RequestMapping("/shuttleBusManage/ride")
@Api(tags="乘车记录")
public class RideInfoController {
	@Resource
	RideInfoClient rideInfoClient;
	
	@ApiOperation(value="乘车记录分页查询",response = RideInfoVO.class)
	@PostMapping("/rideInfoList")
	@LcnTransaction
	public WebApiReturnResultModel rideInfoList(@RequestBody @Validated RideInfoListRequestDTO rideInfoListRequest) {
		return rideInfoClient.rideInfoList(rideInfoListRequest);
	}
}
