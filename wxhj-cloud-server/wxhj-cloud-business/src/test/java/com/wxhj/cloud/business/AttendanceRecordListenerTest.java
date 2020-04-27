package com.wxhj.cloud.business;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.business.attendance.AttendanceRecordBO;
import com.wxhj.cloud.business.attendance.AttendanceRecordService;
import com.wxhj.cloud.business.attendance.AttendanceSingleDayBO;
import com.wxhj.cloud.business.bo.AttendanceMatchingBO;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.service.AttendanceDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void test1(){
        AttendanceDataDO attendanceDataDO =  attendanceDataService.select("74EE2A940C43_134_1587880483376");
        String bodyStr = JSON.toJSONString(attendanceDataDO);

        AttendanceRecordBO attendanceRecord = JSON.parseObject(bodyStr, AttendanceRecordBO.class);
        AttendanceMatchingBO attendanceMatching = null;

        // 查找当前规则
        CurrentAccountAuthorityDO currentAccountAuthority = attendanceRecordService
                .selectCurrentAccountAuthority(attendanceRecord);
        if (currentAccountAuthority != null) {
            List<AttendanceSingleDayBO> attendanceSingleDayList = attendanceRecordService
                    .listAttendanceSingleDay(currentAccountAuthority);
            attendanceMatching = attendanceRecordService.mathingAttendanceMatching(attendanceRecord,
                    attendanceSingleDayList);
        }
        // 无匹配规则或者未匹配成功
        if (attendanceMatching == null) {
            attendanceMatching = attendanceRecordService.defAttendanceMatching(attendanceRecord);
        }
        AttendanceDataDO attendanceData = attendanceRecordService.composeAttendanceData(attendanceRecord,
                attendanceMatching);
        attendanceDataService.insertCascade(attendanceData);
    }
}
