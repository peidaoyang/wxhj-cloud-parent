/**
 * 
 */
package com.wxhj.cloud.platform.system;

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
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.platform.controller.system.AnnouncementController;
import com.wxhj.cloud.platform.dto.request.AnnouncementListRequestDTO;
import com.wxhj.cloud.platform.dto.request.SubmitAnnouncementRequestDTO;

/**
 * @ClassName: AnnouncementControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 下午1:06:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AnnouncementControllerTest {
	@Resource
	AnnouncementController announcementController;

	private String id;

	@Test
	public void submit() throws Exception {
		String requestJson = "{\"id\":\"\",\"content\":\"<p>自动化测试</p>\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"title\":\"通知：自动化测试01\",\"userId\":\"9661ef43-82c0-cf27-4472-08e5a42c24cb\"}";
		SubmitAnnouncementRequestDTO submitRequest = JSONObject.parseObject(requestJson,
				SubmitAnnouncementRequestDTO.class);

		WebApiReturnResultModel submitModel = announcementController.submitAnnouncement(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>" + result);
		id = result;

		String searchJson = "{\"orderBy\":\"id\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":10}";
		AnnouncementListRequestDTO searchRequest = JSONObject.parseObject(searchJson, AnnouncementListRequestDTO.class);
		WebApiReturnResultModel searchModel = announcementController.announcementList(searchRequest);
		FeignUtil.formatArrayClass(searchModel, IPageResponseModel.class);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}

	@After
	public void delete() throws Exception {
		String deleteJson = "{\"id\":\"5a8176f0-4d7a-4e80-a8a2-c4bdd4b90fdc\"}";
		// DeleteAnnouncementRequestDTO deleteDTO =
		// JSONObject.parseObject(deleteJson,DeleteAnnouncementRequestDTO.class);
		// deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = announcementController.deleteAnnouncement(new CommonIdRequestDTO(id));
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
