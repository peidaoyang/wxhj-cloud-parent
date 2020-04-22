package com.wxhj.cloud.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.account.controller.AccountController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.request.ChargingRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RechargeRequestDTO;

/**
 * @ClassName: AccountControllerTest.java
 * @author: cya
 * @Date: 2020年3月6日 下午3:14:59 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountControllerTest {
	@Resource
	AccountController accountController;
	
	private String id;
	
	@Test
	public void submit() throws Exception{
		System.out.println("测试充值接口=====>");
		String requestJson = "{\"accountId\":\"0000000029\",\"amount\":1000,\"userId\":\"c4675665-ee06-440d-8c2d-5a1c463d52f6\",\"currentOrganizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		RechargeRequestDTO submitRequest = JSONObject.parseObject(requestJson, RechargeRequestDTO.class);
		WebApiReturnResultModel submitModel = accountController.recharge(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		id= result;
		
//		ChargingRequestDTO chargingRequest = new ChargingRequestDTO();
//		chargingRequest.setAccountId(submitRequest.getAccountId());
//		chargingRequest.setOrderId(id);
//		chargingRequest.setAmount(submitRequest.getAmount());
//		WebApiReturnResultModel chargModel = accountController.charging(chargingRequest);
//		assertThat(true, is(chargModel.resultSuccess()));
	}
}
