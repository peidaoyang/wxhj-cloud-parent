/**
 * 
 */
package com.wxhj.cloud.platform.device;

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
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.device.SceneController;
import com.wxhj.cloud.platform.dto.request.SubmitSceneInfoRequestDTO;

/**
 * @ClassName: SceneControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 下午2:33:47 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SceneControllerTest {
	@Resource
	SceneController sceneController;
	
	private String id;
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"authGroupId\":[\"0c14819d-602e-4595-bc58-15ce236cebc7\",\"32c42116-b654-4009-af2d-a8e900511216\"],\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"sceneName\":\"自动化测试01\"}";
		SubmitSceneInfoRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitSceneInfoRequestDTO.class);
		
		WebApiReturnResultModel submitModel = sceneController.submitSceneInfo(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		id= result;
		
		String searchJson = "{\"nameValue\":\"\",\"orderBy\":\"id\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":50}";
		CommonListPageRequestDTO searchRequest = JSONObject.parseObject(searchJson, CommonListPageRequestDTO.class);
		WebApiReturnResultModel searchModel = sceneController.listScenePage(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		CommonIdRequestDTO deleteDTO = new CommonIdRequestDTO();
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = sceneController.deleteSceneInfo(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
