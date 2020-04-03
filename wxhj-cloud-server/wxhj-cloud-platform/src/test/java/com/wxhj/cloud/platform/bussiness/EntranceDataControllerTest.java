package com.wxhj.cloud.platform.bussiness;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.feignClient.business.request.ListDetailEntranceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataExcelRequestDTO;
import com.wxhj.cloud.platform.controller.entrance.EntranceDataController;


/**
 * @ClassName: EntranceDataControllerTest.java
 * @author: cya
 * @Date: 2020年3月6日 下午2:33:25 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class EntranceDataControllerTest {
	@Resource
	EntranceDataController entranceDataController;
	
	@Test
	public void submit() throws Exception{
		String searchJson = "{\"beginTime\":\"2020-01-029 00:00:00\",\"endTime\":\"2020-04-30 00:00:00\",\"nameValue\":\"\",\"orderBy\":\"order_number\",\"organizeId\":\"29eec925-a8cd-4e58-af61-5b2347186df8\",\"page\":1,\"rows\":50}";
		ListDetailEntranceDataRequestDTO searchRequest = JSONObject.parseObject(searchJson, ListDetailEntranceDataRequestDTO.class);
		WebApiReturnResultModel searchModel = entranceDataController.listDetailEntranceData(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		PageDefResponseModel search = (PageDefResponseModel)searchModel.getData();
		String searchStr = search.getRows().toString();
		assertThat(true, is(!searchStr.equals("[]")&& searchStr!=null));
		
		String searchJsonExcel = "{\"beginTime\":\"2020-03-06 00:00:00\",\"endTime\":\"2020-03-07 00:00:00\",\"nameValue\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"language\":\"zh_CN\"}";
		ListEntranceDataExcelRequestDTO searchRequestExcel = JSONObject.parseObject(searchJsonExcel, ListEntranceDataExcelRequestDTO.class);
		WebApiReturnResultModel searchModelExcel = entranceDataController.listDetailEntranceDataExcel(searchRequestExcel);
		assertThat(true, is(searchModelExcel.resultSuccess()));
		String searchStrExcel = searchModelExcel.getData().toString();
		assertThat(true, is(!searchStrExcel.equals("[]")&& searchStrExcel!=null));
	}
}
