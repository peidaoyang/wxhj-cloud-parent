//package com.wxhj.cloud.account.aspect;
//
//import com.wxhj.cloud.account.service.FaceChangeRecService;
//import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Aspect
//@Component
//public class FaceChangeAcpect {
//    @Resource
//    RedisTemplate redisTemplate;
//
//
//    @Pointcut("execution(* com.wxhj.cloud.account.service.FaceChangeService.deleteCascade(..))")
//    public void faceChangeDeleteCut() {
//    }
//
//    @After("faceChangeDeleteCut()")
//    public void faceChangeDelete(JoinPoint joinPoint) {
//        String id = (String) joinPoint.getArgs()[0];
//        //删除缓存
//        String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(id);
//        redisTemplate.delete(redisKey);
//    }
//}
