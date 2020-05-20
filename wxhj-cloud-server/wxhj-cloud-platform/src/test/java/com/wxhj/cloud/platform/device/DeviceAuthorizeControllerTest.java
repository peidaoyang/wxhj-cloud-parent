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

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.device.request.InsertDeviceAuthorizeRequestDTO;
import com.wxhj.cloud.platform.controller.backstage.DeviceAuthorizeController;

import java.time.LocalDateTime;

/**
 * @ClassName: DeviceAuthorizeControllerTest.java
 * @author: cya
 * @Date: 2020年3月4日 下午1:11:04 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DeviceAuthorizeControllerTest {
	@Resource
	DeviceAuthorizeController deviceAuthorizeController;
	
	
	@Test
	public void submit() throws Exception{
		InsertDeviceAuthorizeRequestDTO submitRequest = new InsertDeviceAuthorizeRequestDTO();
		submitRequest.setAuthorizeCode("自动化测试");
		submitRequest.setAuthorizeType(0);
		submitRequest.setValidTime(LocalDateTime.now());
		WebApiReturnResultModel submitModel = deviceAuthorizeController.insertDeviceAuthorize(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		FeignUtil.formatClass(submitModel, String.class);
		
//		System.out.println("测试查询接口=====>");
//		String searchJson = "{\"nameValue\":\"\",\"orderBy\":\"device_id\",\"page\":1,\"rows\":50}";
//		ListDeviceAuthorizePageRequestDTO searchRequest = JSONObject.parseObject(searchJson, ListDeviceAuthorizePageRequestDTO.class);
//		WebApiReturnResultModel searchModel = deviceAuthorizeController.listDeviceAuthorizePage(searchRequest);
//		assertThat(true, is(searchModel.resultSuccess()));
//		String searchStr = JSONObject.toJSONString(searchModel);
//		int searchIndex = searchStr.indexOf("自动化测试");
//		assertThat(true, is(searchIndex != -1));
	}

}
