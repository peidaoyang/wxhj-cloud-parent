package com.wxhj.cloud.business;

import com.wxhj.cloud.business.runnable.SummaryAttendanceRunnable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author daxiong
 * @date 2020/4/15 10:52 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceSummaryTest {

    @Resource
    SummaryAttendanceRunnable summaryAttendanceHandle;

    @Test
    public void testImport() {
        summaryAttendanceHandle.run();
    }
}
