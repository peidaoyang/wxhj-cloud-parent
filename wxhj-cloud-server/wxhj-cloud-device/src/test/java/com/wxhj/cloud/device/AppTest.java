package com.wxhj.cloud.device;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.device.domain.DeviceStateDO;
import com.wxhj.cloud.device.service.DeviceStateService;

@SpringBootTest
public class AppTest {


	@Test
	public void test2() {
		WebApiReturnResultModel webApiReturnResultModel = new WebApiReturnResultModel();
		webApiReturnResultModel.setData("123");
		webApiReturnResultModel.setCode(200);
		try {
			String formatClass = FeignUtil.formatClass(webApiReturnResultModel, String.class);
			System.out.println(formatClass);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
