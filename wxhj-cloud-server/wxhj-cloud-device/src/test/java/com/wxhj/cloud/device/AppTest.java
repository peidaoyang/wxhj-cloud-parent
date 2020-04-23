package com.wxhj.cloud.device;

import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {


    //@Test
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

    //@Test
    public void test3() {
//		DeviceGlobalParameterScreenBO deviceGlobalParameterScreenBO=new DeviceGlobalParameterScreenBO() ;
//		deviceGlobalParameterScreenBO.setDeviceId("11");
//		TestResultModel<DeviceGlobalParameterScreenBO> testResultModel
//				=new  TestResultModel("34");
//
//		System.out.println(JSON.toJSONString(testResultModel));
//		List<Integer> list1= new ArrayList();
//		List<DeviceGlobalParameterScreenBO> list=new ArrayList(list1);

    }

}
