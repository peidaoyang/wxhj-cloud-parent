/**
 * 
 */
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
import com.wxhj.cloud.account.controller.RechargeInfoController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.request.ListRechargeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RechargeExcelRequestDTO;

/**
 * @ClassName: RechargeInfoControllerTest.java
 * @author: cya
 * @Date: 2020年3月6日 下午4:10:50 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RechargeInfoControllerTest {
	@Resource
	RechargeInfoController rechargeInfoController;
	
	@Test
	public void search() throws Exception{
		System.out.println("测试查询接口=====>");
		String requestJson = "{\"beginTime\":\"2020-03-06\",\"endTime\":\"2020-04-30\",\"nameValue\":\"\",\"orderBy\":\"creator_time\",\"page\":1,\"payType\":0,\"rows\":10,\"type\":0,\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		ListRechargeInfoRequestDTO submitRequest = JSONObject.parseObject(requestJson, ListRechargeInfoRequestDTO.class);
		WebApiReturnResultModel submitModel = rechargeInfoController.listRechargeInfo(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		PageDefResponseModel resultModel = FeignUtil.formatClass(submitModel, PageDefResponseModel.class);
		String resultModelStr = resultModel.getRows().toString();
		assertThat(true, is(!resultModelStr.equals("[]") && resultModelStr.toString()!=null));
		
		System.out.println("测试报表导出接口=====>");
		String excelJson = "{\"beginTime\":\"2020-03-06\",\"endTime\":\"2020-04-30\",\"nameValue\":\"\",\"language\":\"zh_CN\",\"payType\":0,\"type\":0,\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		RechargeExcelRequestDTO excelRequest = JSONObject.parseObject(excelJson, RechargeExcelRequestDTO.class);
		WebApiReturnResultModel excelModel = rechargeInfoController.rechargeExcel(excelRequest);
		assertThat(true, is(!excelModel.getData().equals("[]") && excelModel.getData().toString()!=null));
	}
}
