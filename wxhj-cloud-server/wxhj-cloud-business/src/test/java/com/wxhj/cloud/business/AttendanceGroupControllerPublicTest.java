/**
 * 
 */
package com.wxhj.cloud.business;

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
import com.wxhj.cloud.business.controller.attendance.AttendanceGroupController;
import com.wxhj.cloud.business.vo.AttendanceGroupAllVO;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

/**
 * @ClassName: AttendanceGroupControllerPublicTest.java
 * @author: cya
 * @Date: 2020年3月5日 下午1:11:24 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AttendanceGroupControllerPublicTest {
	@Resource
	AttendanceGroupController attendanceGroupController;
	
	@Test
	public void submit() throws Exception{
		System.out.println("测试查询接口=====>");
		String searchJson = "{\"orgainzeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\"}";
		CommonOrganizeRequestDTO  searchRequest = JSONObject.parseObject(searchJson, CommonOrganizeRequestDTO.class);
		WebApiReturnResultModel searchModel = attendanceGroupController.listAllAttendGroup(searchRequest);
		List<AttendanceGroupAllVO> listAll =FeignUtil.formatArrayClass(searchModel, AttendanceGroupAllVO.class);
		assertThat(true, is(listAll.size()>0));
	}
	
}
