package com.wxhj.cloud.platform.bussiness;

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
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceDayRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.entrance.EntranceDayController;

/**
 * @ClassName: EntranceDayControllerTest.java
 * @author: cya
 * @Date: 2020年3月6日 上午9:34:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class EntranceDayControllerTest {
	@Resource
	EntranceDayController entranceDayController;

	private String id;

	@Test
	public void submit() throws Exception {
		// 有问题，返回的id为null
		String requestJson = "{\"fullName\":\"自动化测试01\",\"id\":\"\",\"listEntranceDayRec\":[{\"beginTime\":60,\"endTime\":120,\"sequence\":1},{\"beginTime\":240,\"endTime\":722,\"sequence\":2}],\"matchType\":0,\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"timeDescribe\":\"自动化测试\"}";
		SubmitEntranceDayRequestDTO submitRequest = JSONObject.parseObject(requestJson,
				SubmitEntranceDayRequestDTO.class);
		WebApiReturnResultModel submitModel = entranceDayController.submitEntranceDay(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>" + result);
		id = result;

		String updateJson = "{\"fullName\":\"自动化测试02\",\"id\":\"80804b03-9d24-46a7-9cb1-d02c0f846c17\",\"listEntranceDayRec\":[{\"beginTime\":60,\"endTime\":120,\"sequence\":1},{\"beginTime\":240,\"endTime\":662,\"sequence\":2}],\"matchType\":0,\"organizeId\":\"29eec925-a8cd-4e58-af61-5b2347186df8\",\"timeDescribe\":\"自动化测试\"}";
		SubmitEntranceDayRequestDTO updateRequest = JSONObject.parseObject(updateJson,
				SubmitEntranceDayRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = entranceDayController.submitEntranceDay(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));

		String searchJson = "{\"nameValue\":\"自动化测试\",\"orderBy\":\"id\",\"organizeId\":\"29eec925-a8cd-4e58-af61-5b2347186df8\",\"page\":1,\"rows\":50}";
		CommonListPageRequestDTO searchRequest = JSONObject.parseObject(searchJson, CommonListPageRequestDTO.class);
		WebApiReturnResultModel searchModel = entranceDayController.listEntranceDay(searchRequest);
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));

		CommonIdRequestDTO searchRequest2 = new CommonIdRequestDTO();
		searchRequest2.setId(id);
		WebApiReturnResultModel searchModel2 = entranceDayController.selectEntranceDayById(searchRequest2);
		searchStr = searchModel2.getData().toString();
		int searchIndex2 = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex2 != -1));

		CommonIdRequestDTO searchRequest3 = new CommonIdRequestDTO();
		searchRequest3.setId(id);
		WebApiReturnResultModel searchModel3 = entranceDayController.selectEntranceDayById(searchRequest3);

		assertThat(true,
				is(!searchModel3.getData().toString().equals("[]") && searchModel3.getData().toString() != null));
	}

	@After
	public void delete() throws Exception {
		String deleteJson = "{\"id\":\"06324be3-e581-4bf8-9c0c-ef152d8efb2f\"}";
		CommonIdListRequestDTO deleteDTO = JSONObject.parseObject(deleteJson, CommonIdListRequestDTO.class);
		deleteDTO.setIdList(Arrays.asList(id));
		WebApiReturnResultModel deleteModel = entranceDayController.deleteEntranceDay(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}

}
