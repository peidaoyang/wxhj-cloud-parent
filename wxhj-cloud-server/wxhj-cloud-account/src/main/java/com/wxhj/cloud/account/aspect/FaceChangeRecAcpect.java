package com.wxhj.cloud.account.aspect;

import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.runnable.FaceChangeCacheRunnable;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Aspect
@Component
public class FaceChangeRecAcpect {
    @Resource
    FaceChangeService faceChangeService;

    @Pointcut("execution(* com.wxhj.cloud.account.service.FaceChangeRecService.insertListCascade(..))")
    public void faceChangeRecInserListCut() {
    }

    @Around("faceChangeRecInserListCut()")
    public Object faceChangeRecInserList(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        List<FaceChangeRecDO> faceChangeRecList = (List<FaceChangeRecDO>) args[0];
        faceChangeRecList.forEach(q -> {
            Long currentIndex = faceChangeService.selectCurrentIndex(q.getId());
            q.setCurrentIndex(currentIndex);
        });
        Object retVal = pjp.proceed(args);
        return retVal;
    }

    @Resource
    FaceChangeCacheRunnable faceChangeCacheRunnable;

    @AfterReturning(returning = "rObject", value = "faceChangeRecInserListCut()")
    public void faceChangeRecInserListReturn(JoinPoint joinPoint, Object rObject) {
        List<FaceChangeRecDO> faceChangeRecList = (List<FaceChangeRecDO>) rObject;
        if (faceChangeRecList.size() <= 0) {
            return;
        }

        faceChangeCacheRunnable.syncCacheRec(faceChangeRecList);
    }
//}
//    @AfterReturning(returning = "rObject", value = "authorityGroupInfoInsertCut()")
//    public void insert(JoinPoint joinPoint, Object rObject) {
}
