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
import com.wxhj.cloud.feignClient.device.request.SubmitDeviceGlobalParameterRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.backstage.DeviceGlobalParameterController;

/**
 * @ClassName: DeviceGlobalParameterControllerTest.java
 * @author: cya
 * @Date: 2020年3月4日 下午3:13:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DeviceGlobalParameterControllerTest {
	@Resource
	DeviceGlobalParameterController deviceGlobalParameterController;

	private String id;

	@Test
	public void submit() throws Exception {
		String requestJson = "{\"deviceIdListPlus\":[\"00b86427-572e-43cc-afe2-0eb7b069df41\"],\"deviceType\":1,\"endDatetime\":\"2020-03-27 00:00:00\",\"fullName\":\"自动化测试01\",\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"parameterFileUrl\":\"a780b577-e849-4df1-8e58-70c484e6b546.txt\",\"parameterType\":-1,\"sceneIdList\":[\"289d238b-a486-4d21-a0cc-af4731499379\"],\"startDatetime\":\"2020-03-04 00:00:00\"}";
		SubmitDeviceGlobalParameterRequestDTO submitRequest = JSONObject.parseObject(requestJson,
				SubmitDeviceGlobalParameterRequestDTO.class);
		WebApiReturnResultModel submitModel = deviceGlobalParameterController
				.submitDeviceGlobalParameter(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		id = result;

		String updateJson = "{\"deviceIdListPlus\":[\"00b86427-572e-43cc-afe2-0eb7b069df41\"],\"deviceType\":1,\"endDatetime\":\"2020-03-27 00:00:00\",\"fullName\":\"自动化测试02\",\"id\":\"48243d69-279d-433d-a542-494870dccde1\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"parameterFileUrl\":\"a780b577-e849-4df1-8e58-70c484e6b546.txt\",\"parameterType\":-1,\"sceneIdList\":[\"289d238b-a486-4d21-a0cc-af4731499379\"],\"startDatetime\":\"2020-03-04 00:00:00\"}";
		SubmitDeviceGlobalParameterRequestDTO updateRequest = JSONObject.parseObject(updateJson,
				SubmitDeviceGlobalParameterRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = deviceGlobalParameterController
				.submitDeviceGlobalParameter(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));

		String searchJson = "{\"nameValue\":\"自动化测试02\",\"orderBy\":\"id\",\"page\":1,\"rows\":10,\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		CommonListPageRequestDTO searchRequest = JSONObject.parseObject(searchJson,CommonListPageRequestDTO.class);
		WebApiReturnResultModel searchModel = deviceGlobalParameterController.listDeviceGlobalParameter(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}

	@After
	public void delete() throws Exception {
		CommonIdRequestDTO deleteDTO = new CommonIdRequestDTO();
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = deviceGlobalParameterController.deleteDeviceGlobalRarameter(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
