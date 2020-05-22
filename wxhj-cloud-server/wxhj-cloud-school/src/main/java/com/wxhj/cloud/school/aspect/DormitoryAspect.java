package com.wxhj.cloud.school.aspect;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.device.DeviceGlobalParameterClient;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.school.service.RoomService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Aspect
@Component
public class DormitoryAspect {
    @Resource
    RoomService roomService;
    @Resource
    AuthorityGroupClient authorityGroupClient;

    @Pointcut("execution(public void com.wxhj.cloud.school.service.DormitoryService.deleteCascade(..))")
    public void dormitoryDeleteCut(){}

    @Before(value = "dormitoryDeleteCut()")
    public void dormitoryDeleteCutBefore(JoinPoint joinPoint) throws Throwable{
        String id = (String)joinPoint.getArgs()[0];
        // 对应删除权限组
        WebApiReturnResultModel deleteAuthorityGroupInfo = authorityGroupClient.deleteAuthorityGroupInfo(new CommonIdRequestDTO(id));
        if (!deleteAuthorityGroupInfo.resultSuccess()) { throw new Throwable("删除权限组失败");}
    }

    @After(value = "dormitoryDeleteCut()")
    public void dormitoryDeleteCutAfter(JoinPoint joinPoint){
        String id = (String)joinPoint.getArgs()[0];
        roomService.deleteCascade(id);
    }
}
