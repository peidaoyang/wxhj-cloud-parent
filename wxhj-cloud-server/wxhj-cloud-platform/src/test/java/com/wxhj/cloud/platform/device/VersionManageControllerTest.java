/**
 * 
 */
package com.wxhj.cloud.platform.device;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

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
import com.wxhj.cloud.feignClient.device.request.SubmitVerManageRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.backstage.VersionManageController;

/**
 * @ClassName: VersionManageControllerTest.java
 * @author: cya
 * @Date: 2020年3月4日 下午1:56:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VersionManageControllerTest {
	@Resource
	VersionManageController versionManageController;

	private String id;

	@Test
	public void submit() throws Exception {
		String requestJson = "{\"deviceType\":1,\"fileName\":\"\",\"id\":\"\",\"organizeId\":0,\"releaseState\":0,\"resourceType\":1,\"updateDescribe\":\"自动化测试01\",\"versionNumber\":\"99.99.99.99\",\"userId\":\"c3975b9e-fe2c-4f23-96d0-910ac4d03f7a\"}";
		SubmitVerManageRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitVerManageRequestDTO.class);
		WebApiReturnResultModel submitModel = versionManageController.submitVerManage(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>" + result);
		id = result;

		String updateJson = "{\"id\":\"4afad735-195f-4d3f-8553-e301594ca969\",\"deviceId\":\"820134\",\"deviceModel\":\"自动化测试01\",\"deviceType\":0,\"imei\":\"自动化测试\"}";
		SubmitVerManageRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitVerManageRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = versionManageController.submitVerManage(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));

		String searchJson = "{\"orderBy\":\"id asc\",\"page\":1,\"rows\":10,\"deviceType\":-1}";
		VersionManageListRequestDTO searchRequest = JSONObject.parseObject(searchJson,
				VersionManageListRequestDTO.class);
		WebApiReturnResultModel searchModel = versionManageController.versionManageList(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));

	}

	@After
	public void delete() throws Exception {
		// DeleteVerManageRequestDTO deleteDTO = new DeleteVerManageRequestDTO();
		List<String> idList = new ArrayList<String>();
		idList.add(id);
		// deleteDTO.setId(idList);
		WebApiReturnResultModel deleteModel = versionManageController
				.deleteVerManage(new CommonIdListRequestDTO(idList));
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
