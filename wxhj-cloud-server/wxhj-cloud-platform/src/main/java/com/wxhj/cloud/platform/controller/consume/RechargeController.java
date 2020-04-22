/**
 * 
 */
package com.wxhj.cloud.platform.controller.consume;

import javax.annotation.Resource;

import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.account.vo.AppRechargeInfoVO;
import com.wxhj.cloud.feignClient.account.vo.PersonRechargeVO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.RechargeClient;
import com.wxhj.cloud.feignClient.account.vo.ListRechargeInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: RechargeController.java
 * @author: cya
 * @Date: 2020年2月2日 上午11:54:36 
 */
@RestController
@RequestMapping("/consumeManage/rechargeInfo")
@Api(tags="充值接口")
public class RechargeController {
	@Resource
	RechargeClient rechargeClient;
	
	@PostMapping("/listRechargeInfo")
	@ApiOperation(value="分页查询充值信息",response = ListRechargeInfoVO.class)
	@LcnTransaction
	public WebApiReturnResultModel listRechargeInfo(@RequestBody @Validated ListRechargeInfoRequestDTO listRechargeInfo) {
		return rechargeClient.listRechargeInfo(listRechargeInfo);
	}
	
	@PostMapping("/rechargeExcel")
	@ApiOperation("充值信息报表导出")
	@LcnTransaction
	public WebApiReturnResultModel rechargeExcel(@RequestBody @Validated RechargeExcelRequestDTO rechargeExcel) {
		return rechargeClient.rechargeExcel(rechargeExcel);
	}


	@PostMapping("/refund")
	@ApiOperation("充值退款")
	@Transactional
	public WebApiReturnResultModel refund(@RequestBody @Validated RefundRequestDTO refundRequest){
		return rechargeClient.refund(refundRequest);
	}

	@PostMapping("/personRechargeInfo")
	@ApiOperation(value = "个人充值信息查询",response = PersonRechargeVO.class)
	public WebApiReturnResultModel appRechargeInfo(@RequestBody @Validated PersonRechargeRequestDTO appRechargeInfo){
		return rechargeClient.personRecharge(appRechargeInfo);
	}

}
