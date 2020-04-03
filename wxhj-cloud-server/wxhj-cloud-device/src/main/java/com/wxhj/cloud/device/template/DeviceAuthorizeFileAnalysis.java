/**
 * 
 */
package com.wxhj.cloud.device.template;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.file.AbstractFileAnalysisTemplate;
import com.wxhj.cloud.core.file.IFileAnalysis;
import com.wxhj.cloud.device.domain.DeviceAuthorizeDO;

/**
 * @ClassName: DeviceAuthorizeFileAnalysis.java
 * @author: cya
 * @Date: 2019年12月11日 上午11:25:01 
 */
@Component("deviceAuthorizeFileAnalysis")
public class DeviceAuthorizeFileAnalysis extends AbstractFileAnalysisTemplate<DeviceAuthorizeDO> 
	implements IFileAnalysis<DeviceAuthorizeDO> {
	private static Class<DeviceAuthorizeDO> deviceAuthorizeClass = DeviceAuthorizeDO.class;
	private static List<String> columeList = Arrays.asList("deviceId","authorizeType","authorizeCode","validTime");

	@Override
	protected List<String> getColumeNameList() {
		return columeList;
	}

	@Override
	protected Class<DeviceAuthorizeDO> getTclass() {
		return deviceAuthorizeClass;
	}
	
}
