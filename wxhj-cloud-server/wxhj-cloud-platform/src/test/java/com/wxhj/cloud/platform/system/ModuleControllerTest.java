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
import com.wxhj.cloud.platform.controller.backstage.ModuleController;
import com.wxhj.cloud.platform.dto.request.SysModuleSumbitRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysModuleTreeRequestDTO;

/**
 * @ClassName: ModuleControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 上午11:28:58 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ModuleControllerTest {
	@Resource
	ModuleController moduleController;
	
	private String id;
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"id\":\"\",\"fullName\":\"自动化测试01\",\"urlAddress\":\"/test/test\",\"target\":\"iframe\",\"icon\":\"\",\"sortCode\":1,\"isMenu\":0,\"isPublic\":0,\"isExpand\":1,\"isEnabledMark\":1,\"description\":\"自动化测试\",\"isDeleteMark\":0,\"parentId\":\"0\",\"layers\":0}";
		SysModuleSumbitRequestDTO submitRequest = JSONObject.parseObject(requestJson, SysModuleSumbitRequestDTO.class);
		
		WebApiReturnResultModel submitModel = moduleController.submitSysModule(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		id= result;
		
		String searchJson = "{\"fullName\":\"自动化测试\"}";
		SysModuleTreeRequestDTO searchRequest = JSONObject.parseObject(searchJson, SysModuleTreeRequestDTO.class);
		WebApiReturnResultModel searchModel = moduleController.sysModuleTreeList(searchRequest);
		FeignUtil.formatArrayClass(searchModel, IPageResponseModel.class);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		String deleteJson = "{\"id\":\"06324be3-e581-4bf8-9c0c-ef152d8efb2f\"}";
		CommonIdRequestDTO deleteDTO = JSONObject.parseObject(deleteJson,CommonIdRequestDTO.class);
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = moduleController.deleteSysModule(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
