/**
 * 
 */
package com.wxhj.cloud.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.account.controller.AccountConsumeController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.request.ListConsumeDetailExcelRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeDetailRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListConsumeSummaryRequestDTO;

/**
 * @ClassName: AccountConsumeControllerTest.java
 * @author: cya
 * @Date: 2020年3月6日 下午4:37:33 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AccountConsumeControllerTest {
	@Resource
	AccountConsumeController accountConsumeController;
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"beginTime\":\"2020-01-01\",\"endTime\":\"2020-04-30\",\"nameValue\":\"\",\"orderBy\":\"account_id\",\"organizeId\":\"dfaea5be-8273-4bdd-bd6f-4f66eaadd509\",\"page\":1,\"rows\":50}";
		ListConsumeDetailRequestDTO submitRequest = JSONObject.parseObject(requestJson, ListConsumeDetailRequestDTO.class);
		WebApiReturnResultModel submitModel = accountConsumeController.listConsumeDetail(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		PageDefResponseModel resultModel = FeignUtil.formatClass(submitModel, PageDefResponseModel.class);
		String resultModelStr = resultModel.getRows().toString();
		assertThat(true, is(!resultModelStr.equals("[]") && resultModelStr.toString()!=null));
		
		String updateJson = "{\"beginTime\":\"2020-01-06\",\"endTime\":\"2020-03-07\",\"nameValue\":\"\",\"orderBy\":\"account_id\",\"organizeId\":\"29eec925-a8cd-4e58-af61-5b2347186df8\",\"language\":\"zh_CN\"}";
		ListConsumeDetailExcelRequestDTO excelRequest = JSONObject.parseObject(updateJson, ListConsumeDetailExcelRequestDTO.class);
		WebApiReturnResultModel excelModel = accountConsumeController.listConsumeDetailExcel(excelRequest);
		assertThat(true, is(!excelModel.getData().equals("[]") && excelModel.getData().toString()!=null));
		
	}
	
	@After
	public void search() throws Exception{
		String requestJson = "{\"beginTime\":\"2020-02-21\",\"endTime\":\"2020-02-22\",\"nameValue\":\"\",\"orderBy\":\"consume_date\",\"page\":1,\"rows\":50,\"organizeId\":\"dfaea5be-8273-4bdd-bd6f-4f66eaadd509\"}";
		ListConsumeSummaryRequestDTO submitRequest = JSONObject.parseObject(requestJson, ListConsumeSummaryRequestDTO.class);
		WebApiReturnResultModel submitModel = accountConsumeController.listConsumeSummary(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		PageDefResponseModel resultModel = FeignUtil.formatClass(submitModel, PageDefResponseModel.class);
		String resultModelStr = resultModel.getRows().toString();
		assertThat(true, is(!resultModelStr.equals("[]") && resultModelStr.toString()!=null));
	}
}
