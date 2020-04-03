/**
 * 
 */
package com.wxhj.cloud.platform.bussiness.shuttleBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.business.request.RideInfoListRequestDTO;
import com.wxhj.cloud.platform.controller.shuttleBus.RideInfoController;

/**
 * @ClassName: RideInfoControllerTest.java
 * @author: cya
 * @Date: 2020年3月7日 上午11:00:26 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RideInfoControllerTest {
	@Resource
	RideInfoController rideInfoController;
	
	@Test
	public void submit() throws Exception{
		String searchJson = "{\"beginTime\":\"2020-02-01 00:00:00\",\"endTime\":\"2020-03-31 00:00:00\",\"nameValue\":\"\",\"orderBy\":\"creator_time\",\"organizeId\":\"29eec925-a8cd-4e58-af61-5b2347186df8\",\"page\":1,\"rows\":50,\"type\":1}";
		RideInfoListRequestDTO searchRequest = JSONObject.parseObject(searchJson, RideInfoListRequestDTO.class);
		WebApiReturnResultModel searchModel = rideInfoController.rideInfoList(searchRequest);
		PageDefResponseModel pageDefResponseModel = FeignUtil.formatClass(searchModel, PageDefResponseModel.class);
		List<T> datas = JSON.parseArray(pageDefResponseModel.getRows().toString(), T.class);
		assertThat(true, is(datas.size()>0));
	}
}
