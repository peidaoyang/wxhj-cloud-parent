/**
 * @fileName: TriggerJobController.java
 * @author: pjf
 * @date: 2019年12月26日 下午3:14:42
 */

package com.wxhj.cloud.business.controller;

import com.wxhj.cloud.business.config.ThreadPoolStaticClass;
import com.wxhj.cloud.business.runnable.SummaryAttendanceRunnable;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author pjf
 * @className TriggerJobController.java
 * @date 2019年12月26日 下午3:14:42
 */
@RestController
@Slf4j
@RequestMapping("/triggerJob")
public class TriggerJobController {

    @Resource
    SummaryAttendanceRunnable summaryAttendanceRunnable;

    @PostMapping("/summaryAttendance")
    public WebApiReturnResultModel summaryAttendance() {
        ThreadPoolStaticClass.summaryAttendanceThreadPool.execute(summaryAttendanceRunnable);
        return WebApiReturnResultModel.ofSuccess();
    }

}
