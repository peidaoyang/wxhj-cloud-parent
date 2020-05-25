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

import com.wxhj.cloud.core.utils.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.business.request.ViewRideSummaryListRequestDTO;
import com.wxhj.cloud.platform.controller.shuttleBus.ViewRideSummaryController;

/**
 * @ClassName: ViewRideSummaryControllerTest.java
 * @author: cya
 * @Date: 2020年3月7日 上午11:10:47 
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewRideSummaryControllerTest {
	@Resource
	ViewRideSummaryController viewRideSummaryController;
	
	@Test
	public void submit() throws Exception{
		String searchJson = "{\"beginTime\":\"2020-02-01\",\"endTime\":\"2020-03-31\",\"nameValue\":\"\",\"orderBy\":\"ride_time\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":50}";
		ViewRideSummaryListRequestDTO searchRequest = JSONObject.parseObject(searchJson, ViewRideSummaryListRequestDTO.class);
		WebApiReturnResultModel searchModel = viewRideSummaryController.viewRideSummaryList(searchRequest);
		PageDefResponseModel pageDefResponseModel = FeignUtil.formatClass(searchModel, PageDefResponseModel.class);
		List<T> datas = JSON.parseArray(pageDefResponseModel.getRows().toString(), T.class);
		assertThat(true, is(datas.size()>0));
	}
}
