package com.wxhj.cloud.device.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceAuthorizeDO;

/**
 * @className DeviceAuthorizeService.java
 * @author jwl
 * @date 2019年11月28日 下午4:33:31
 */
public interface DeviceAuthorizeService {
	
	String insert(DeviceAuthorizeDO devicceAuthorizeDO);
	
	void insert(List<DeviceAuthorizeDO> deviceAuthorizeDO);
	
	void deleteById(String authorizeId);
	
	void update(DeviceAuthorizeDO deviceAuthorize);
	
	List<DeviceAuthorizeDO> list();
	
	PageInfo<DeviceAuthorizeDO> listDeviceAuthorize(IPageRequestModel pageRequestModel);
	
	DeviceAuthorizeDO selectByDeviceIdAndType(String deviceId,Integer authorizeType);
	
	DeviceAuthorizeDO selectByDeviceIdIsNullAndType(Integer authorizeType);
}
