/**
 * 
 */
package com.wxhj.cloud.platform.device;

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
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import com.wxhj.cloud.platform.controller.backstage.DeviceInfoController;


/**
 * @ClassName: DeviceInfoControllerTest.java
 * @author: cya
 * @Date: 2020年3月4日 上午11:32:42 
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceInfoControllerTest {
	@Resource
	DeviceInfoController deviceInfoController;
	
	private String id;
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"id\":\"\",\"deviceId\":\"820134\",\"deviceModel\":\"自动化测试\",\"deviceType\":0,\"imei\":\"自动化测试\"}";
		SubmitDeviceInfoRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitDeviceInfoRequestDTO.class);
		WebApiReturnResultModel submitModel = deviceInfoController.submitDeviceInfo(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		id= result;
		
		String updateJson = "{\"id\":\"4afad735-195f-4d3f-8553-e301594ca969\",\"deviceId\":\"820134\",\"deviceModel\":\"自动化测试01\",\"deviceType\":0,\"imei\":\"自动化测试\"}";
		SubmitDeviceInfoRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitDeviceInfoRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = deviceInfoController.submitDeviceInfo(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));
		
		
		String searchJson = "{\"nameValue\":\"\",\"orderBy\":\"device_id\",\"page\":1,\"rows\":10}";
		CommonPageRequestDTO searchRequest = JSONObject.parseObject(searchJson, CommonPageRequestDTO.class);
		WebApiReturnResultModel searchModel = deviceInfoController.listDeviceInfo(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		CommonIdRequestDTO deleteDTO = new CommonIdRequestDTO();
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = deviceInfoController.deleteDeviceInfo(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
