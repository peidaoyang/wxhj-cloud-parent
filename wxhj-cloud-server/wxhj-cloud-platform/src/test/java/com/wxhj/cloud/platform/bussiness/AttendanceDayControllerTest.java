/**
 * 
 */
package com.wxhj.cloud.platform.bussiness;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import javax.annotation.Resource;

import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.attendance.AttendanceDayController;

/**
 * @ClassName: AttendanceDayControllerTest.java
 * @author: cya
 * @Date: 2020年3月4日 下午5:23:40 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AttendanceDayControllerTest {
	@Resource
	AttendanceDayController attendanceDayController;
	
	private String id;
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"attendanceDayRec\":[{\"downExtent\":10,\"downTime\":660,\"sequence\":1,\"upExtent\":10,\"upTime\":540},{\"downExtent\":10,\"downTime\":1080,\"sequence\":2,\"upExtent\":10,\"upTime\":840}],\"attendanceType\":0,\"fullName\":\"自动化测试01\",\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"timeDescribe\":\"09:00-11:00,14:00-18:00\"}";
		SubmitAttendanceDayRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitAttendanceDayRequestDTO.class);
		WebApiReturnResultModel submitModel = attendanceDayController.submitAttendanceDay(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		id= result;
		
		String updateJson = "{\"attendanceDayRec\":[{\"downExtent\":0,\"downTime\":0,\"sequence\":1,\"upExtent\":0,\"upTime\":0}],\"attendanceType\":1,\"fullName\":\"自动化测试01\",\"id\":\"c82cac3b-c4de-4599-ad33-3ab406f452a3\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"timeDescribe\":\"05:00\"}";
		SubmitAttendanceDayRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitAttendanceDayRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = attendanceDayController.submitAttendanceDay(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));
		
		
		String searchJson = "{\"nameValue\":\"自动化测试\",\"orderBy\":\"id asc\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":10}";
		ListAttendanceDayRequestDTO searchRequest = JSONObject.parseObject(searchJson, ListAttendanceDayRequestDTO.class);
		WebApiReturnResultModel searchModel = attendanceDayController.listAttendanceDay(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		CommonIdListRequestDTO deleteDTO = new CommonIdListRequestDTO();
		deleteDTO.setIdList(Arrays.asList(id));
		WebApiReturnResultModel deleteModel = attendanceDayController.deleteAllAttendanceDay(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
