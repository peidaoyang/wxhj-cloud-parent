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
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.system.RoleController;
import com.wxhj.cloud.platform.dto.request.SysRoleSubmitRequestDTO;

/**
 * @ClassName: RoleControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 上午10:37:40 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleControllerTest {
	@Resource
	RoleController roleController;
	
	private String id;

	@Test
	public void submit() throws Exception{
		String requestJson = "{\"parentIdList\":[\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"],\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"fullName\":\"自动化测试01\",\"encode\":\"\",\"type\":1,\"sortCode\":1,\"isEnabledMark\":1,\"description\":\"\",\"moduleIdList\":[\"07036d43-c8a7-4f3b-afd8-1b74c0a26d9f\",\"1202078c-3141-437c-97d7-385c953db1a9\",\"59f38995-edca-480f-85b8-a6c1a41d7fc0\",\"5d889324-5dbe-4152-b198-6e75cc496ea2\",\"5fec056a-fbe0-4d0f-8155-0940dae4f2d8\",\"697d62d7-d157-4668-877f-c1e3e8e51e0e\",\"78a37b4e-a75e-489c-b050-27d57f6a36d2\",\"ca34c323-9ffd-45bb-99ff-1f3a5d5ab763\",\"f1737e23-2d97-481c-8c82-8d1889611a2c\"],\"authenticationToken\":{\"userName\":\"cya\",\"userId\":\"c3975b9e-fe2c-4f23-96d0-910ac4d03f7a\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"mapId\":\"86494408-cd2e-4813-a350-a3905f338207\",\"currentOrganizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"organizeChildList\":[\"2fca977e-3412-4fd1-b618-02b92c4d843e\",\"b4793021-ddd0-4ea4-b1a6-4eeb33486c4a\",\"d33e9701-2965-44f9-9c2a-82be10e10c5e\",\"86655dab-1973-4741-8751-386bb49a102e\",\"d248bd04-84f4-45f0-aa74-4d976e00c6de\",\"e5386b48-8861-4bc2-baaa-7c3b82e06d87\",\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"],\"isSystem\":true,\"sessionId\":\"cya_2d32144310ad430a9d5aedbc30d84f9a\"}}";
		SysRoleSubmitRequestDTO submitRequest = JSONObject.parseObject(requestJson, SysRoleSubmitRequestDTO.class);
		
		WebApiReturnResultModel submitModel = roleController.submitSysRole(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		id= result;
		
		String searchJson = "{\"nameValue\":\"自动化测试01\",\"rows\":10,\"page\":1,\"orderBy\":\"id asc\",\"authenticationToken\":{\"userName\":\"cya\",\"userId\":\"c3975b9e-fe2c-4f23-96d0-910ac4d03f7a\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"mapId\":\"86494408-cd2e-4813-a350-a3905f338207\",\"currentOrganizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"organizeChildList\":[\"2fca977e-3412-4fd1-b618-02b92c4d843e\",\"b4793021-ddd0-4ea4-b1a6-4eeb33486c4a\",\"d33e9701-2965-44f9-9c2a-82be10e10c5e\",\"86655dab-1973-4741-8751-386bb49a102e\",\"d248bd04-84f4-45f0-aa74-4d976e00c6de\",\"e5386b48-8861-4bc2-baaa-7c3b82e06d87\",\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"],\"isSystem\":true,\"sessionId\":\"cya_2d32144310ad430a9d5aedbc30d84f9a\"}}";
		CommonListPageRequestDTO searchRequest = JSONObject.parseObject(searchJson, CommonListPageRequestDTO.class);
		WebApiReturnResultModel searchModel = roleController.sysRoleListByOrgId(searchRequest);
		FeignUtil.formatArrayClass(searchModel, IPageResponseModel.class);
		assertThat(true, is(searchModel.getCode()==200));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		String deleteJson = "{\"id\":\"3f751360-2b82-4234-b386-f3419652d53c\",\"authenticationToken\":{\"userName\":\"cya\",\"userId\":\"c3975b9e-fe2c-4f23-96d0-910ac4d03f7a\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"mapId\":\"86494408-cd2e-4813-a350-a3905f338207\",\"currentOrganizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"organizeChildList\":[\"2fca977e-3412-4fd1-b618-02b92c4d843e\",\"b4793021-ddd0-4ea4-b1a6-4eeb33486c4a\",\"d33e9701-2965-44f9-9c2a-82be10e10c5e\",\"86655dab-1973-4741-8751-386bb49a102e\",\"d248bd04-84f4-45f0-aa74-4d976e00c6de\",\"e5386b48-8861-4bc2-baaa-7c3b82e06d87\",\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"],\"isSystem\":true,\"sessionId\":\"cya_2d32144310ad430a9d5aedbc30d84f9a\"}}";
		CommonIdRequestDTO deleteDTO = JSONObject.parseObject(deleteJson,CommonIdRequestDTO.class);
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = roleController.deleteSysRole(deleteDTO);
		assertThat(true, is(deleteModel.getCode()==200));
	}
}
