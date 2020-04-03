package com.wxhj.cloud.device.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.device.domain.DeviceInfoDO;
import com.wxhj.cloud.device.domain.DeviceParameterDO;
import com.wxhj.cloud.device.service.DeviceInfoService;
import com.wxhj.cloud.device.service.DeviceParameterService;

@Aspect
@Component
public class DeviceInfoAspect {
	@Resource
	DeviceParameterService deviceParameterService;
	@Resource
	DeviceInfoService deviceInfoService;
	@Resource
	DozerBeanMapper dozerBeanMapper;

	@Pointcut("execution(* com.wxhj.cloud.device.service.DeviceInfoService.insertCascade(..))")
	public void deviceInfoInsertCut() {
	}

	
	@After("deviceInfoInsertCut()")
	public void deviceInfoInsert(JoinPoint joinPoint)
	{
		DeviceInfoDO deviceInfo = (DeviceInfoDO) joinPoint.getArgs()[0];
		DeviceParameterDO deviceParameter = new DeviceParameterDO();
		deviceParameter.setDeviceId(deviceInfo.getDeviceId());
		deviceParameter.setParameterVersion(System.currentTimeMillis()/1000);
		deviceParameter.setDeviceType(deviceInfo.getDeviceType());
		deviceParameterService.insert(deviceParameter);
	}

}
