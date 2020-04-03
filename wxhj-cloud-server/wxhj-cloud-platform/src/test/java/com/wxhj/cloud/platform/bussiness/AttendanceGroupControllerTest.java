package com.wxhj.cloud.platform.bussiness;

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
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceGroupRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.controller.attendance.AttendanceGroupController;

/**
 * @ClassName: AttendanceGroupControllerTest.java
 * @author: cya
 * @Date: 2020年3月5日 上午11:03:33 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AttendanceGroupControllerTest {
	@Resource
	AttendanceGroupController attendanceGroupController;
	
	private String id;
	
	@Test
	public void submit() throws Exception{
		String requestJson = "{\"fullName\":\"自动化测试01\",\"groupType\":0,\"id\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"submitAttendanceGroupRecList\":[{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":1},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":2},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":3},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":4},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":5},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":6},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":7}]}";
		SubmitAttendanceGroupRequestDTO submitRequest = JSONObject.parseObject(requestJson, SubmitAttendanceGroupRequestDTO.class);
		WebApiReturnResultModel submitModel = attendanceGroupController.submitAttendanceGroup(submitRequest);
		assertThat(true, is(submitModel.resultSuccess()));
		String result = FeignUtil.formatClass(submitModel, String.class);
		id= result;
		
		String updateJson = "{\"fullName\":\"自动化测试01\",\"groupType\":0,\"id\":\"7936c0bc-a91d-4195-90a5-096e12001c93\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"submitAttendanceGroupRecList\":[{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":1},{\"attendanceDayId\":\"f10e7a5e-29fc-44d2-89a3-d84213006e90\",\"serialNumber\":2},{\"attendanceDayId\":\"f10e7a5e-29fc-44d2-89a3-d84213006e90\",\"serialNumber\":3},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":4},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":5},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":6},{\"attendanceDayId\":\"eae7da55-6ad9-417e-a956-23848d1fdf08\",\"serialNumber\":7}]}";
		SubmitAttendanceGroupRequestDTO updateRequest = JSONObject.parseObject(updateJson, SubmitAttendanceGroupRequestDTO.class);
		updateRequest.setId(id);
		WebApiReturnResultModel updateModel = attendanceGroupController.submitAttendanceGroup(updateRequest);
		assertThat(true, is(updateModel.resultSuccess()));
		
		
		String searchJson = "{\"nameValue\":\"自动化测试\",\"orderBy\":\"id asc\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":10}";
		CommonListPageRequestDTO searchRequest = JSONObject.parseObject(searchJson, CommonListPageRequestDTO.class);
		WebApiReturnResultModel searchModel = attendanceGroupController.listAttendanceGroup(searchRequest);
		assertThat(true, is(searchModel.resultSuccess()));
		String searchStr = JSONObject.toJSONString(searchModel);
		int searchIndex = searchStr.indexOf("自动化测试");
		assertThat(true, is(searchIndex != -1));
	}
	
	
	@After
	public void delete() throws Exception {
		CommonIdRequestDTO deleteDTO = new CommonIdRequestDTO();
		deleteDTO.setId(id);
		WebApiReturnResultModel deleteModel = attendanceGroupController.deleteAttendanceGroup(deleteDTO);
		assertThat(true, is(deleteModel.resultSuccess()));
	}
}
