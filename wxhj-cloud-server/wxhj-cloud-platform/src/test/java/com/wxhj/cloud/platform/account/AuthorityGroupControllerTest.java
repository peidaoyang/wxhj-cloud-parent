///**
// * 
// */
//package com.wxhj.cloud.platform.account;
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
//import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
//import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
//import com.wxhj.cloud.platform.controller.system.AuthorityGroupController;
//
///**
// * @ClassName: AuthorityGroupControllerTest.java
// * @author: cya
// * @Date: 2020年3月4日 上午10:18:53
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Transactional
//public class AuthorityGroupControllerTest {
//	@Resource
//	AuthorityGroupController authorityGroupController;
//
//	private String id;
//
//	@Test
//	public void submit() throws Exception {
//		String requestJson = "{\"fullName\":\"自动化测试01\",\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"sceneIdList\":[\"289d238b-a486-4d21-a0cc-af4731499379\",\"77b22afb-0fc6-45ca-821d-f11e8e6f01c0\"],\"type\":0}";
//		SubmitAuthorityGroupInfoRequestDTO submitRequest = JSONObject.parseObject(requestJson,
//				SubmitAuthorityGroupInfoRequestDTO.class);
//
//		WebApiReturnResultModel submitModel = authorityGroupController.submitAuthorityGroupInfo(submitRequest);
//		assertThat(true, is(submitModel.resultSuccess()));
//		String result = FeignUtil.formatClass(submitModel, String.class);
//		id = result;
//
//		String updateJson = "{\"fullName\":\"自动化测试01\",\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"sceneIdList\":[\"77b22afb-0fc6-45ca-821d-f11e8e6f01c0\"],\"type\":0}";
//		SubmitAuthorityGroupInfoRequestDTO updateRequest = JSONObject.parseObject(updateJson,
//				SubmitAuthorityGroupInfoRequestDTO.class);
//		updateRequest.setId(id);
//		WebApiReturnResultModel updateModel = authorityGroupController.submitAuthorityGroupInfo(updateRequest);
//		assertThat(true, is(updateModel.resultSuccess()));
//
////		String searchJson = "{\"nameValue\":\"自动化测试\",\"orderBy\":\"id\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":50}";
////		ListAuthorityGroupPageRequestDTO searchRequest = JSONObject.parseObject(searchJson,
////				ListAuthorityGroupPageRequestDTO.class);
////		WebApiReturnResultModel searchModel = authorityGroupController.listAuthorityGroupPage(searchRequest);
////		assertThat(true, is(searchModel.resultSuccess()));
////		String searchStr = JSONObject.toJSONString(searchModel);
////		int searchIndex = searchStr.indexOf("自动化测试");
////		assertThat(true, is(searchIndex != -1));
//	}
//
//	@After
//	public void delete() throws Exception {
//		CommonIdRequestDTO deleteDTO = new CommonIdRequestDTO(id);
//
//		WebApiReturnResultModel deleteModel = authorityGroupController.deleteAuthorityGroupInfo(deleteDTO);
//		assertThat(true, is(deleteModel.resultSuccess()));
//	}
//}
