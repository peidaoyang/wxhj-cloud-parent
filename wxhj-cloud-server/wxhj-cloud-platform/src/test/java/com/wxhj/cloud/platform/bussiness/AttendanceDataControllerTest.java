/**
 *
 */
package com.wxhj.cloud.platform.bussiness;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.business.request.DayAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListDayAttendanceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.platform.controller.attendance.AttendanceDataController;
import org.apache.poi.ss.formula.functions.T;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @ClassName: AttendanceDataControllerTest.java
 * @author: cya
 * @Date: 2020年3月5日 下午5:04:17 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AttendanceDataControllerTest {
    @Resource
    AttendanceDataController attendanceDataController;

    @Test
    public void search() throws Exception {
        String searchJson = "{\"beginTime\":\"2020-01-28\",\"endTime\":\"2020-04-30\",\"nameValue\":\"\",\"orderBy\":\"datetime\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":50}";
        ListDayAttendanceDataRequestDTO searchRequest = JSONObject.parseObject(searchJson, ListDayAttendanceDataRequestDTO.class);
        WebApiReturnResultModel searchModel = attendanceDataController.listDayAttendanceData(searchRequest);
        PageDefResponseModel pageDefResponseModel = FeignUtil.formatClass(searchModel, PageDefResponseModel.class);
        List<T> datas = JSON.parseArray(pageDefResponseModel.getRows().toString(), T.class);
        assertThat(true, is(datas.size() > 0));

        //需要修改
        System.out.println("测试报表导出接口=====>");
        String excelJson = "{\"beginTime\":\"2020-01-28\",\"endTime\":\"2020-04-30\",\"nameValue\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"language\":\"zh_CN\"}";
        DayAttendanceDataExcelRequestDTO excelRequest = JSONObject.parseObject(excelJson, DayAttendanceDataExcelRequestDTO.class);
        WebApiReturnResultModel excelModel = attendanceDataController.exportDayAttendanceDataExcel(excelRequest);
        assertThat(true, is(!excelModel.getData().equals("[]") && excelModel.getData().toString() != null));
    }

    @After
    public void searchMonth() throws Exception {
//        String searchJson = "{\"beginTime\":\"2020-01-06\",\"endTime\":\"2020-03-07\",\"nameValue\":\"\",\"orderBy\":\"id\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"page\":1,\"rows\":50,\"groupType\":0}";
//        ListMonthAttendanceDataRequestDTO searchRequest = JSONObject.parseObject(searchJson, ListMonthAttendanceDataRequestDTO.class);
//        WebApiReturnResultModel searchModel = attendanceDataController.listMonthAttendanceData(searchRequest);
//        assertThat(true, is(searchModel.resultSuccess()));
//        PageDefResponseModel pageDefResponseModel = FeignUtil.formatClass(searchModel, PageDefResponseModel.class);
//        List<T> datas = JSON.parseArray(pageDefResponseModel.getRows().toString(), T.class);
//        assertThat(true, is(datas.size() > 0));

        //需要修改
        String excelJson = "{\"beginTime\":\"2020-01-28\",\"endTime\":\"2020-04-30\",\"nameValue\":\"\",\"organizeId\":\"f8b89131-de13-4dc2-b5bb-b117e12c23bc\",\"language\":\"zh_CN\"}";
        ListMonthAttendanceDataExcelRequestDTO excelRequest = JSONObject.parseObject(excelJson, ListMonthAttendanceDataExcelRequestDTO.class);
        WebApiReturnResultModel excelModel = attendanceDataController.listMonthAttendanceDataExcel(excelRequest);
        System.out.println(excelModel.getData());
        excelModel.getData();
    }
}
