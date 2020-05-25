package com.wxhj.cloud.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import com.wxhj.cloud.account.bo.AccountConsumeRocjetBO;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.core.utils.JSON;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
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

import java.time.LocalDateTime;

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
	@Resource
	RocketMqProducer rocketMqProducer;

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

	@Test
	public void test() {
		AccountConsumeRocjetBO accountConsumeRocjet = new AccountConsumeRocjetBO();
		accountConsumeRocjet.setAccountId("0000001699");
		accountConsumeRocjet.setDeviceId("8667930313020061");
		accountConsumeRocjet.setOrderNumber("86679303130200611586324055");
		accountConsumeRocjet.setConsumeMoney(200);
		accountConsumeRocjet.setConsumeDate(LocalDateTime.now());
		accountConsumeRocjet.setOrganizeId("98035095-861f-41c9-898a-2ed68104c40b");
		accountConsumeRocjet.setSceneId("2ad65997-0279-49f3-aec8-b425f97e6cab");
		rocketMqProducer.sendMessage(RocketMqTopicStaticClass.ACCOUNT_CONSUME_TOPIC, JSON.toJSONString(accountConsumeRocjet));
	}
}
