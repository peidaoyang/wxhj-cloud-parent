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
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.system.UserController;
import com.wxhj.cloud.platform.dto.request.AccountImportRequestDTO;
//import com.wxhj.cloud.platform.dto.request.SysUserPageRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysUserSubmitRequestDTO;

/**
 * @ClassName: UserControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 上午10:31:35 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserControllerTest {
	@Resource
	UserController userController;
	
	private String id;

	@Test
	public void submit() throws Exception{
		String requestJson = "{\r\n" + 
				"	\"id\":\"0000000028\",\r\n" + 
				"	\"roleId\":\"bb5533ba-9ee7-4729-829c-aca36593b9fa\",\r\n" + 
				"	\"userId\":\"c3975b9e-fe2c-4f23-96d0-910ac4d03f7a\"\r\n" + 
				"}";
		AccountImportRequestDTO submitRequest = JSONObject.parseObject(requestJson, AccountImportRequestDTO.class);
		WebApiReturnResultModel submitModel = userController.accountImport(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		id= result;
		
		String updateJson = "{\r\n" + 
				"	\"id\":\"0000000028\",\r\n" + 
				"	\"sysUserSubmitRequestList\":[{\"organizeId\":\"49833238-a264-4e16-8bb9-2ff7b6fd235a\",\"roleId\":\"3cf2b280-13e7-4869-83fa-2d86ffee216f\"}],\r\n" + 
				"	\"userId\":\"da478096-9894-4c1a-a984-bb56da98a68d\"\r\n" + 
				"}";
		SysUserSubmitRequestDTO updateRequest = JSONObject.parseObject(updateJson, SysUserSubmitRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = userController.submitSysUser(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));
		
		String searchJson = "{\r\n" + 
				"	\"nameValue\":\"\",\r\n" + 
				"	\"rows\":10,\r\n" + 
				"	\"page\":1,\r\n" + 
				"	\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"\r\n" + 
				"}";
		CommonListPageRequestDTO searchRequest = JSONObject.parseObject(searchJson, CommonListPageRequestDTO.class);
		WebApiReturnResultModel searchModel = userController.sysUserList(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("id");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		//需要修改
		String deleteJson = "{\"id\":\"2fca977e-3412-4fd1-b618-02b92c4d843e\"}";
		CommonIdRequestDTO deleteDTO = JSONObject.parseObject(deleteJson,CommonIdRequestDTO.class);
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = userController.deleteSysUser(deleteDTO);
		assertThat(true, is(deleteModel.getCode()==200));
	}
}
