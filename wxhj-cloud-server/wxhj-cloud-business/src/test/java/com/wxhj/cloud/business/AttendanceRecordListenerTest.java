package com.wxhj.cloud.business;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.business.attendance.AttendanceRecordBO;
import com.wxhj.cloud.business.attendance.AttendanceRecordService;
import com.wxhj.cloud.business.attendance.AttendanceSingleDayBO;
import com.wxhj.cloud.business.bo.AttendanceMatchingBO;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.domain.EntranceDataDO;
import com.wxhj.cloud.business.service.AttendanceDataService;
import com.wxhj.cloud.business.service.EntranceDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AttendanceRecordListenerTest {
    @Resource
    AttendanceRecordService attendanceRecordService;
    @Resource
    AttendanceDataService attendanceDataService;
    @Resource
    EntranceDataService entranceDataService;

    @Rollback(false)
    @Test
    public void test1(){
        String bodyStr = "{\n" +
                "\t\"accessDate\": \"2020-04-17\",\n" +
                "\t\"accessTime\": 906,\n" +
                "\t\"accountId\": \"0000000553\",\n" +
                "\t\"accountName\": \"aaaaaaaaaaaa\",\n" +
                "\t\"deviceId\": \"74EE2A93EDCA\",\n" +
                "\t\"deviceName\": \"4.3inch\",\n" +
                "\t\"endDate\": \"2030-04-17 15:01:36\",\n" +
                "\t\"entranceId\": \"f646ddea-8057-4c6d-a4ca-db719f620e6c\",\n" +
                "\t\"entranceSequence\": 1,\n" +
                "\t\"orderNumber\": \"74EE2A93EDCA_1_15871011\",\n" +
                "\t\"organizeId\": \"b320801e-32be-4032-8167-2a7f4775c5ea\",\n" +
                "\t\"recordDatetime\": \"2020-04-29 11:06:04\",\n" +
                "\t\"recordTimeStamp\": 1587107164,\n" +
                "\t\"sceneId\": \"8b9bf8a1-80ae-437d-86bc-fae6f00ce574\",\n" +
                "\t\"serialNumber\": 1,\n" +
                "\t\"startDate\": \"2020-04-29 11:01:36\",\n" +
                "\t\"temperature\": 36.36\n" +
                "}";
        EntranceDataDO entranceDataDO = JSON.parseObject(bodyStr, EntranceDataDO.class);
        entranceDataService.insert(entranceDataDO);
    }
}
