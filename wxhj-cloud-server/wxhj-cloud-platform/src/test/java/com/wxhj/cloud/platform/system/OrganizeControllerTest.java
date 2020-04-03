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
import com.wxhj.cloud.platform.controller.system.OrganizeController;
import com.wxhj.cloud.platform.dto.request.SysOrgOptimizeSubmitRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysOrgaDeleteRequestDTO;
import com.wxhj.cloud.platform.mapper.SysOrganizeMapper;
import com.wxhj.cloud.platform.service.SysOrganizeService;

/**
 * @ClassName: OrganizeTest.java
 * @author: cya
 * @Date: 2020年3月2日 下午3:58:59 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrganizeControllerTest {
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	SysOrganizeMapper sysOrganizeMapper;
	@Resource
	OrganizeController organizeController;
	
	private String id;

	@Test
	public void submitOrganize() throws Exception{
		String requestJson = "{\"parentId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"parentIdList\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"id\":\"\",\"categoryId\":\"operator\",\"fullName\":\"自动化测试01\",\"encode\":\"\",\"managerId\":\"13912345678\",\"mobilePhone\":\"0\",\"wechat\":\"0\",\"telephone\":\"0\",\"email\":\"0\",\"fax\":\"0\",\"address\":\"0\",\"isEnabledMark\":1,\"description\":\"cya测试\",\"layers\":1,\"authenticationToken\":{\"userName\":\"cya\",\"userId\":\"c3975b9e-fe2c-4f23-96d0-910ac4d03f7a\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"mapId\":\"86494408-cd2e-4813-a350-a3905f338207\",\"currentOrganizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"organizeChildList\":[\"b4793021-ddd0-4ea4-b1a6-4eeb33486c4a\",\"d33e9701-2965-44f9-9c2a-82be10e10c5e\",\"86655dab-1973-4741-8751-386bb49a102e\",\"d248bd04-84f4-45f0-aa74-4d976e00c6de\",\"e5386b48-8861-4bc2-baaa-7c3b82e06d87\",\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"],\"isSystem\":true,\"sessionId\":\"cya_bb08113ddbb947059217f8b80753479b\"}}";
		SysOrgOptimizeSubmitRequestDTO submitRequest = JSONObject.parseObject(requestJson, SysOrgOptimizeSubmitRequestDTO.class);
		WebApiReturnResultModel submitModel = organizeController.submitSysOrganizeOptimize(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		
		id= result;
	}
	
	
	@After
	public void deleteSysOrganize() throws Exception {
		String deleteJson = "{\"id\":\"2fca977e-3412-4fd1-b618-02b92c4d843e\",\"authenticationToken\":{\"userName\":\"cya\",\"userId\":\"c3975b9e-fe2c-4f23-96d0-910ac4d03f7a\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"mapId\":\"86494408-cd2e-4813-a350-a3905f338207\",\"currentOrganizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"organizeChildList\":[\"2fca977e-3412-4fd1-b618-02b92c4d843e\",\"b4793021-ddd0-4ea4-b1a6-4eeb33486c4a\",\"d33e9701-2965-44f9-9c2a-82be10e10c5e\",\"86655dab-1973-4741-8751-386bb49a102e\",\"d248bd04-84f4-45f0-aa74-4d976e00c6de\",\"e5386b48-8861-4bc2-baaa-7c3b82e06d87\",\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"],\"isSystem\":true,\"sessionId\":\"cya_2d32144310ad430a9d5aedbc30d84f9a\"}}";
		SysOrgaDeleteRequestDTO deleteDTO = JSONObject.parseObject(deleteJson,SysOrgaDeleteRequestDTO.class);
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = organizeController.deleteSysOrganize(deleteDTO);
		
		sysOrganizeMapper.deleteByPrimaryKey(id);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
