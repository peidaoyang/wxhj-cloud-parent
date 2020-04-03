/**
 * 
 */
package com.wxhj.cloud.platform.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.platform.request.EnumTypeListRequestDTO;
import com.wxhj.cloud.platform.controller.backstage.EnumManageController;

/**
 * @ClassName: EnumManageControllerPublicTest.java
 * @author: cya
 * @Date: 2020年3月4日 上午11:05:02 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class EnumManageControllerPublicTest {
	@Resource
	EnumManageController enumManageController;
	
	@Test
	public void search() {
		String requestJson = "{\"enumCode\":1}";
		EnumTypeListRequestDTO searchRequest = JSONObject.parseObject(requestJson, EnumTypeListRequestDTO.class);
		WebApiReturnResultModel searchModel = enumManageController.enumTypeList(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("enumCode");
		assertThat(true, is(searchIndex != -1));
		
	}
}
