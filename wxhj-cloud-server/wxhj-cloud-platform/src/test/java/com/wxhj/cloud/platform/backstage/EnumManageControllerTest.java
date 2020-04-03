///**
// * 
// */
//package com.wxhj.cloud.platform.backstage;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//import javax.annotation.Resource;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.alibaba.fastjson.JSONObject;
//import com.wxhj.cloud.core.model.WebApiReturnResultModel;
//import com.wxhj.cloud.core.utils.FeignUtil;
//import com.wxhj.cloud.platform.controller.backstage.EnumManageController;
//import com.wxhj.cloud.platform.dto.request.DeleteEnumRequestDTO;
//import com.wxhj.cloud.platform.dto.request.EnumManageListRequestDTO;
//import com.wxhj.cloud.platform.dto.request.SubmitEnumInfoRequestDTO;
//
///**
// * @ClassName: EnumManageControllerTest.java
// * @author: cya
// * @Date: 2020年3月4日 下午2:50:19 
// */
//@Transactional
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class EnumManageControllerTest {
//	@Resource
//	EnumManageController enumManageController;
//	
//	private String id;
//	
//	@Test
//	public void submit() throws Exception{
//		String requestJson = "{\"enumCode\":1,\"enumName\":\"设备类型\",\"enumType\":\"7\",\"enumTypeName\":\"自动化测试01\",\"id\":\"\"}";
//		SubmitEnumInfoRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitEnumInfoRequestDTO.class);
//		WebApiReturnResultModel submitModel = enumManageController.submitEnumInfo(submitRequest);
//		assertThat(true, is(submitModel.resultSuccess()));
//		String result = FeignUtil.formatClass(submitModel, String.class);
//		id= result;
//		
//		String updateJson = "{\"enumCode\":1,\"enumName\":\"设备类型\",\"enumType\":\"100\",\"enumTypeName\":\"自动化测试02\",\"id\":\"dc3bb33d-dbf4-461f-856a-8dc39e2d94c1\"}";
//		SubmitEnumInfoRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitEnumInfoRequestDTO.class);
//		updateRequest.setId(id);
//		WebApiReturnResultModel updateModel = enumManageController.submitEnumInfo(updateRequest);
//		assertThat(true, is(updateModel.resultSuccess()));
//		
//		
//		String searchJson = "{\"fullName\":\"\",\"orderBy\":\"enum_code\",\"page\":1,\"rows\":10}";
//		EnumManageListRequestDTO searchRequest = JSONObject.parseObject(searchJson, EnumManageListRequestDTO.class);
//		WebApiReturnResultModel searchModel = enumManageController.enumManageList(searchRequest);
//		assertThat(true, is(searchModel.resultSuccess()));
//		String searchStr = JSONObject.toJSONString(searchModel);
//		int searchIndex = searchStr.indexOf("自动化测试");
//		assertThat(true, is(searchIndex != -1));
//	}
//	
//	
//	@After
//	public void delete() throws Exception {
//		DeleteEnumRequestDTO deleteDTO = new DeleteEnumRequestDTO();
//		deleteDTO.setId(id);
//		WebApiReturnResultModel deleteModel = enumManageController.deleteEnum(deleteDTO);
//		assertThat(true, is(deleteModel.resultSuccess()));
//	}
//}
