/** 
 * @fileName: DeviceGlobalParameterAspect.java  
 * @author: pjf
 * @date: 2020年2月26日 下午1:51:28 
 */

package com.wxhj.cloud.device.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.device.domain.DeviceGlobalParameterDO;
import com.wxhj.cloud.device.service.DeviceGlobalParameterService;

/**
 * @className DeviceGlobalParameterAspect.java
 * @author pjf
 * @date 2020年2月26日 下午1:51:28   
*/
/**
 * @className DeviceGlobalParameterAspect.java
 * @author pjf
 * @date 2020年2月26日 下午1:51:28
 */
@Aspect
@Component
public class DeviceGlobalParameterAspect {
	@Resource
	DeviceGlobalParameterService deviceGlobalParameterService;
	@Resource
	FileStorageService fileStorageService;

	@Pointcut("execution(* com.wxhj.cloud.device.service.DeviceGlobalParameterService.updateCascade(..))")
	public void deviceGlobalParameterUpdateCut() {
	}

	@Pointcut("execution(* com.wxhj.cloud.device.service.DeviceGlobalParameterService.deleteCascade(..))")
	public void deviceGlobalParameterDeleteCut() {
	}
	// 对设备静态参数进行设置是,需要同步对资源文件进行操作

	@Before("deviceGlobalParameterUpdateCut()")
	public void deviceGlobalParameterUpdate(JoinPoint joinPoint) {
		DeviceGlobalParameterDO deviceGlobalParameter = (DeviceGlobalParameterDO) joinPoint.getArgs()[0];
		DeviceGlobalParameterDO selectById = deviceGlobalParameterService.selectById(deviceGlobalParameter.getId());
		if (selectById != null && selectById.getParameterFileUrl() != null
				&& !selectById.getParameterFileUrl().equals(deviceGlobalParameter.getParameterFileUrl())) {
			fileStorageService.deleteFile(selectById.getParameterFileUrl());
		}
	}

	@Before("deviceGlobalParameterDeleteCut()")
	public void deviceGlobalParameterDelete(JoinPoint joinPoint) {
		String id = (String) joinPoint.getArgs()[0];
		DeviceGlobalParameterDO selectById = deviceGlobalParameterService.selectById(id);
		if (selectById != null && selectById.getParameterFileUrl() != null) {
			fileStorageService.deleteFile(selectById.getParameterFileUrl());
		}
	}
}
