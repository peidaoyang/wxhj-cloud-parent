package com.wxhj.cloud.device.aspect;

import com.wxhj.cloud.device.domain.DeviceInfoDO;
import com.wxhj.cloud.device.domain.DeviceParameterDO;
import com.wxhj.cloud.device.service.DeviceInfoService;
import com.wxhj.cloud.device.service.DeviceParameterService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class DeviceInfoAspect {
    @Resource
    DeviceParameterService deviceParameterService;
    @Resource
    DeviceInfoService deviceInfoService;
    @Resource
    DozerBeanMapper dozerBeanMapper;

//    @Pointcut("execution(* com.wxhj.cloud.device.service.DeviceInfoService.insertCascade(..))")
//    public void deviceInfoInsertCut() {
//    }
//
//
//    @After("deviceInfoInsertCut()")
//    public void deviceInfoInsert(JoinPoint joinPoint) {
//        DeviceInfoDO deviceInfo = (DeviceInfoDO) joinPoint.getArgs()[0];
//        DeviceParameterDO deviceParameter = new DeviceParameterDO();
//        deviceParameter.setDeviceId(deviceInfo.getDeviceId());
//        deviceParameter.setParameterVersion(System.currentTimeMillis() / 1000);
//        deviceParameter.setDeviceType(deviceInfo.getDeviceType());
//        deviceParameter.setIsAttendance(0);
//        deviceParameter.setIsEntrance(0);
//        deviceParameter.setIsConsume(0);
//        deviceParameter.setIsFlight(0);
//        deviceParameter.setIsVisit(0);
//        deviceParameterService.insert(deviceParameter);
//    }

}
