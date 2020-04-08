///**
// * @fileName: FaceChangeRecAcpect.java
// * @author: pjf
// * @date: 2019年11月26日 下午3:44:14
// */
//
//package com.wxhj.cloud.faceServer.acpect;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import com.wxhj.cloud.faceServer.domian.FaceChangeRecDO;
//import com.wxhj.cloud.faceServer.service.FaceChangeService;
//
///**
// * @className FaceChangeRecAcpect.java
// * @author pjf
// * @date 2019年11月26日 下午3:44:14
// */
//
//@Aspect
//@Component
//public class FaceChangeRecAcpect {
//	@Resource
//	FaceChangeService faceChangeService;
//
//	@Pointcut("execution(public void com.wxhj.cloud.faceServer.service.FaceChangeRecService.insertListCascade(..))")
//	public void faceChangeRecInserListCut() {
//	}
//
//	@Around("faceChangeRecInserListCut()")
//	public Object faceChangeRecInserList(ProceedingJoinPoint pjp) throws Throwable {
//		Object[] args = pjp.getArgs();
//		List<FaceChangeRecDO> faceChangeRecList = (List<FaceChangeRecDO>) args[0];
//		faceChangeRecList.forEach(q -> {
//			Long currentIndex = faceChangeService.selectCurrentIndex(q.getId());
//			q.setCurrentIndex(currentIndex);
//		});
//		Object retVal = pjp.proceed(args);
//		return retVal;
//	}
//}
