/**
 * 
 */
package com.wxhj.cloud.platform.device;

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
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.device.DeviceStateController;

/**
 * @ClassName: DeviceStateControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 下午1:55:42 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DeviceStateControllerTest {
	@Resource
	DeviceStateController deviceStateController;
	
	@Test
	public void search() throws Exception{
		String searchJson = "{\"nameValue\":\"\",\"orderBy\":\"device_id\",\"rows\":10,\"page\":1,\"organizeId\":\"dfaea5be-8273-4bdd-bd6f-4f66eaadd509\"}";
		CommonListPageRequestDTO searchRequest = JSONObject.parseObject(searchJson, CommonListPageRequestDTO.class);
		WebApiReturnResultModel searchModel = deviceStateController.listDeviceState(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("820");
		assertThat(true, is(searchIndex != -1));
	}
}
