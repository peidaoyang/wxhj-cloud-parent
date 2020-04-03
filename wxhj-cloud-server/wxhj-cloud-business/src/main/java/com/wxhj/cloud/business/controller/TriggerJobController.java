/** 
 * @fileName: TriggerJobController.java  
 * @author: pjf
 * @date: 2019年12月26日 下午3:14:42 
 */

package com.wxhj.cloud.business.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.business.handle.SummaryAttendanceHandle;
import com.wxhj.cloud.component.job.AsynJobPoolHelper;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonJobRequestDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @className TriggerJobController.java
 * @author pjf
 * @date 2019年12月26日 下午3:14:42
 */
@RestController
@Slf4j
@RequestMapping("/triggerJob")
public class TriggerJobController {
	@Resource
	AsynJobPoolHelper asynJobPoolHelper;
	@Resource
	SummaryAttendanceHandle summaryAttendanceHandle;

	@PostMapping("/summaryAttendance")
	public WebApiReturnResultModel summaryAttendance(@RequestBody CommonJobRequestDTO commonJobRequest) {
		summaryAttendanceHandle.setCommonJobRequest(commonJobRequest);
		asynJobPoolHelper.addTrigger(summaryAttendanceHandle);

		return WebApiReturnResultModel.ofSuccess();
	}

}
