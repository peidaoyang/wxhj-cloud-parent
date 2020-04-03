package com.wxhj.cloud.platform.bussiness;

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
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.business.request.ListEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitEntranceGroupRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.platform.controller.entrance.EntranceGroupController;

/**
 * @ClassName: EntranceGroupControllerTest.java
 * @author: cya
 * @Date: 2020年3月6日 上午11:11:42
 */
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class EntranceGroupControllerTest {
	@Resource
	EntranceGroupController entranceGroupController;

	private String id;

	@Test
	public void submit() throws Exception {
		String requestJson = "{\"fullName\":\"自动化测试01\",\"id\":\"\",\"listEntranceGroupRec\":[{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":1},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":2},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":3},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":4},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":5},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":6},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":7}],\"groupType\":0,\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		SubmitEntranceGroupRequestDTO submitRequest = JSONObject.parseObject(requestJson,
				SubmitEntranceGroupRequestDTO.class);
		WebApiReturnResultModel submitModel = entranceGroupController.submitEntranceGroup(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>" + result);
		assertThat(true, is(result != null));
		id = result;

		String updateJson = "{\"fullName\":\"自动化测试02\",\"id\":\"04744f69-b591-4eeb-b153-83dfd60c2251\",\"listEntranceGroupRec\":[{\"entranceDayId\":\"2dc14487-b030-4def-842a-5ae316d80c37\",\"serialNumber\":1},{\"entranceDayId\":\"2dc14487-b030-4def-842a-5ae316d80c37\",\"serialNumber\":2},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":3},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":4},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":5},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":6},{\"entranceDayId\":\"1e4e265f-e932-4752-89ef-6d865807b4c4\",\"serialNumber\":7}],\"groupType\":0,\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		SubmitEntranceGroupRequestDTO updateRequest = JSONObject.parseObject(updateJson,
				SubmitEntranceGroupRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = entranceGroupController.submitEntranceGroup(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));

		String searchJson = "{\"nameValue\":\"自动化测试\",\"orderBy\":\"id\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":10}";
		ListEntranceGroupRequestDTO searchRequest = JSONObject.parseObject(searchJson,
				ListEntranceGroupRequestDTO.class);
		WebApiReturnResultModel searchModel = entranceGroupController.listEntranceGroup(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));

		CommonIdRequestDTO searchRequest2 = new CommonIdRequestDTO();
		searchRequest2.setId(id);
		WebApiReturnResultModel searchModel2 = entranceGroupController.listEntranceGroup(searchRequest);
		assertThat(true, is(searchModel2.resultSuccess()));
		String searchStr2 = JSONObject.toJSONString(searchModel2);
		int searchIndex2 = searchStr2.indexOf("自动化测试");
		assertThat(true, is(searchIndex2 != -1));

	}

	@After
	public void delete() throws Exception {
		WebApiReturnResultModel deleteModel = entranceGroupController.deleteEntranceGroup(new CommonIdRequestDTO(id));
		assertThat(true, is(deleteModel.resultSuccess()));
	}

}
