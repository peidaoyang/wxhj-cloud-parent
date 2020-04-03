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
import com.wxhj.cloud.feignClient.business.request.RouteInfoListRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitRoutInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.platform.controller.shuttleBus.RouteInfoController;

/**
 * @ClassName: RouteInfoControllerTest.java
 * @author: cya
 * @Date: 2020年3月7日 上午9:34:40 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RouteInfoControllerTest {
	@Resource
	RouteInfoController routeInfoController;
	
	private String id;
	
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"channelSite\":\"\",\"endSite\":\"b\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"routeName\":\"自动化测试01\",\"routeNumber\":\"00005\",\"startSite\":\"a\"}";
		SubmitRoutInfoRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitRoutInfoRequestDTO.class);
		WebApiReturnResultModel submitModel = routeInfoController.submitRoutInfo(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		id= result;
		
		String updateJson = "{\"id\":\"634d94d4-30a5-4650-8957-2f79a79ec20f\",\"channelSite\":\"d\",\"endSite\":\"b\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"routeName\":\"自动化测试02\",\"routeNumber\":\"00005\",\"startSite\":\"c\"}";
		SubmitRoutInfoRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitRoutInfoRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = routeInfoController.submitRoutInfo(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));
		
		
		String searchJson = "{\"nameValue\":\"\",\"orderBy\":\"id asc\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":10,\"type\":1}";
		RouteInfoListRequestDTO searchRequest = JSONObject.parseObject(searchJson, RouteInfoListRequestDTO.class);
		WebApiReturnResultModel searchModel = routeInfoController.routeInfoList(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		System.out.println("测试删除接口=====>");
		CommonIdListRequestDTO deleteDTO = new CommonIdListRequestDTO();
		deleteDTO.setIdList(Arrays.asList(id));
		WebApiReturnResultModel deleteModel = routeInfoController.deleteRouteInfo(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
