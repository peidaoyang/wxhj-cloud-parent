package com.wxhj.cloud.faceServer.acpect;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.faceServer.domian.FaceAccountInfoDO;

@Aspect
@Component
public class FaceAccountAcpect {

	@Resource
	FileStorageService fileStorageService;

	@Pointcut("execution(public void com.wxhj.cloud.faceServer.service.FaceAccountInfoService.deleteCascade(..))")
	public void faceAcountInfoDeleteCut() {

	}

	@Before("faceAcountInfoDeleteCut()")
	public void faceAcountInfoDelete(JoinPoint joinPoint) {
		FaceAccountInfoDO faceAcountInfo = (FaceAccountInfoDO) joinPoint.getArgs()[0];
		fileStorageService.deleteFile(faceAcountInfo.getImageName());
	}
}
