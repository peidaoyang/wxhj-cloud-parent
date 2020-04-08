///**
// * @fileName: TriggerJobController.java
// * @author: pjf
// * @date: 2019年11月21日 上午11:02:23
// */
//
//package com.wxhj.cloud.faceServer.controller;
//
//import javax.annotation.Resource;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wxhj.cloud.component.job.AsynJobPoolHelper;
//import com.wxhj.cloud.core.model.WebApiReturnResultModel;
//import com.wxhj.cloud.faceServer.handle.CacheSyncFaceChangeHandle;
//import com.wxhj.cloud.faceServer.handle.FaceChangeSynchHandle;
//import com.wxhj.cloud.feignClient.dto.CommonJobRequestDTO;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @className TriggerJobController.java
// * @author pjf
// * @date 2019年11月21日 上午11:02:23
// */
//
//@RestController
//@Slf4j
//@RequestMapping("/triggerJob")
//public class TriggerJobController {
//	@Resource
//	AsynJobPoolHelper asynJobPoolHelper;
//	@Resource
//	FaceChangeSynchHandle fcaeChangeSynchHandle;
//	@Resource
//	CacheSyncFaceChangeHandle cacheSyncFaceChangeHandle;
//
//	@PostMapping("/faceChangeSynch")
//	public WebApiReturnResultModel faceChangeSynch(@RequestBody CommonJobRequestDTO commonJobRequest) {
//		fcaeChangeSynchHandle.setCommonJobRequest(commonJobRequest);
//		asynJobPoolHelper.addTrigger(fcaeChangeSynchHandle);
//		return WebApiReturnResultModel.ofSuccess();
//	}
//
//	@PostMapping("/cacheSyncFaceChange")
//	public WebApiReturnResultModel cacheSyncFaceChange(@RequestBody CommonJobRequestDTO commonJobRequest) {
//
//		cacheSyncFaceChangeHandle.setCommonJobRequest(commonJobRequest);
//		asynJobPoolHelper.addTrigger(cacheSyncFaceChangeHandle);
//		return WebApiReturnResultModel.ofSuccess();
//	}
//}
