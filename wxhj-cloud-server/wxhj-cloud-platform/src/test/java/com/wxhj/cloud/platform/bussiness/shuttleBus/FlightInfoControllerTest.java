/**
 * 
 */
package com.wxhj.cloud.platform.bussiness.shuttleBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.business.request.FlightListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitFlightRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.platform.controller.shuttleBus.FlightInfoController;

/**
 * @ClassName: FlightInfoControllerTest.java
 * @author: cya
 * @Date: 2020年3月7日 上午10:17:56 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FlightInfoControllerTest {
	@Resource
	FlightInfoController flightInfoController;
	
	private String id;
	
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"carNumber\":\"0000001\",\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"routeNumber\":\"00001\",\"startTime\":\"540\",\"flightNumber\":\"0005\"}";
		SubmitFlightRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitFlightRequestDTO.class);
		WebApiReturnResultModel submitModel = flightInfoController.submitFlight(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		id= result;
		
		String updateJson = "{\"carNumber\":\"0000001\",\"id\":\"86b6f99b-dfdb-4c2a-9924-c43c6648e8ce\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"routeNumber\":\"00001\",\"startTime\":540,\"flightNumber\":\"0006\"}";
		SubmitFlightRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitFlightRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = flightInfoController.submitFlight(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));
		
		
		String searchJson = "{\"nameValue\":\"0006\",\"orderBy\":\"id asc\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":10,\"type\":1}";
		FlightListRequestDTO searchRequest = JSONObject.parseObject(searchJson, FlightListRequestDTO.class);
		WebApiReturnResultModel searchModel = flightInfoController.flightList(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("0006");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		System.out.println("测试删除接口=====>");
		CommonIdListRequestDTO deleteDTO = new CommonIdListRequestDTO();
		deleteDTO.setIdList(Arrays.asList(id));
		WebApiReturnResultModel deleteModel = flightInfoController.deleteFlight(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
	
}
