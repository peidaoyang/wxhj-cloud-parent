/**
 * 
 */
package com.wxhj.cloud.platform.bussiness.visitor;

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
import com.wxhj.cloud.feignClient.business.request.CheckVisRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitVisitorRequestDTO;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.platform.controller.visitor.VisitorController;

/**
 * @ClassName: VisitorInfoControllerTest.java
 * @author: cya
 * @Date: 2020年3月7日 上午11:39:18 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VisitorInfoControllerTest {
	@Resource
	VisitorController visitorInfoController;
	
	private String id;
	
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"accountId\":\"0000000028\",\"accountName\":\"用户1\",\"beginTime\":\"2020-03-07 00:00:00\",\"company\":\"自动化测试公司\",\"endTime\":\"2020-04-30 00:00:00\",\"id\":\"\",\"idNumber\":\"321183199212021111\",\"name\":\"自动化测试01\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"phone\":\"13912345678\",\"position\":\"员工\",\"reason\":\"自动化测试01\",\"sceneId\":\"289d238b-a486-4d21-a0cc-af4731499379\"}";
		SubmitVisitorRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitVisitorRequestDTO.class);
		WebApiReturnResultModel submitModel = visitorInfoController.submitVisitor(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		id= result;
		
		String updateJson = "{\"accountId\":\"0000000028\",\"accountName\":\"用户1\",\"beginTime\":\"2020-03-07 00:00:00\",\"company\":\"自动化测试公司\",\"endTime\":\"2020-04-29 00:00:00\",\"id\":\"765b0b26-e68d-45c0-b7a4-30c86ce7f68e\",\"idNumber\":\"321183199212021111\",\"name\":\"自动化测试02\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"phone\":\"13912345678\",\"position\":\"员工\",\"reason\":\"自动化测试02\",\"sceneId\":\"77b22afb-0fc6-45ca-821d-f11e8e6f01c0\"}";
		SubmitVisitorRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitVisitorRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = visitorInfoController.submitVisitor(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));
		
		
		CheckVisRequestDTO checkRequest = new CheckVisRequestDTO();
		checkRequest.setId(id);
		checkRequest.setIsCheck(1);
		WebApiReturnResultModel checkModel = visitorInfoController.checkVis(checkRequest);
		assertThat(true, is(checkModel.resultSuccess()));
		
		String searchJson = "{\"nameValue\":\"自动化测试\",\"orderBy\":\"creator_time asc\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":10,\"type\":1,\"isCheck\":1}";
		VisitorInfoListRequestDTO searchRequest = JSONObject.parseObject(searchJson, VisitorInfoListRequestDTO.class);
		WebApiReturnResultModel searchModel = visitorInfoController.visitorInfoList(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		CommonIdListRequestDTO deleteDTO = new CommonIdListRequestDTO();
		deleteDTO.setIdList(Arrays.asList(id));
		WebApiReturnResultModel deleteModel = visitorInfoController.deleteVisitor(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
