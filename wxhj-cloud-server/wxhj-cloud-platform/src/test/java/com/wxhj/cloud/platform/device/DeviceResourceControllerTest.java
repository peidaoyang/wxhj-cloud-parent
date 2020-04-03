/**
 * 
 */
package com.wxhj.cloud.platform.device;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.device.request.ListDeviceResourceRequestDTO;
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceResourceRequestDTO;
import com.wxhj.cloud.platform.controller.backstage.DeviceResourceController;


/**
 * @ClassName: DeviceResourceControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 下午3:18:50 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DeviceResourceControllerTest {
	@Resource
	DeviceResourceController deviceResourceController;
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"posId\":[\"820123458\"],\"versionId\":\"4d06f4b3-d49e-4808-b434-e1239e956cee\"}";
		SubmitDeviceResourceRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitDeviceResourceRequestDTO.class);
		
		WebApiReturnResultModel submitModel = deviceResourceController.submitDeviceResource(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		List<String> result = FeignUtil.formatArrayClass(submitModel, String.class);
		System.out.println("返回值=====>"+result);
		
		String searchJson = "{\"orderBy\":\"pos_id\",\"page\":1,\"rows\":10,\"deviceType\":-1,\"nameValue\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		ListDeviceResourceRequestDTO searchRequest = JSONObject.parseObject(searchJson, ListDeviceResourceRequestDTO.class);
		WebApiReturnResultModel searchModel = deviceResourceController.listDeviceResource(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("分配");
		assertThat(true, is(searchIndex != -1));
	}
	
}
