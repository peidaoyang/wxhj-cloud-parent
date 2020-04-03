package com.wxhj.cloud.device.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceParameterDO;

/**
 * @className DeviceParameterService.java
 * @author jwl
 * @date 2019年12月2日 上午8:38:18
 */
public interface DeviceParameterService {

	void insert(DeviceParameterDO deviceParameter);

//	void delete(String deviceId);

	void update(DeviceParameterDO deviceParameter);

	PageInfo<DeviceParameterDO> listPage(IPageRequestModel pageRequestModel,String valueName,
			String organizeId, String type);

	DeviceParameterDO selectByDeviceId(String deviceId);

	List<DeviceParameterDO> listByDeviceId(List<String> deviceId);
	
	List<DeviceParameterDO> listByOrganizeId(String organizeId);
}
