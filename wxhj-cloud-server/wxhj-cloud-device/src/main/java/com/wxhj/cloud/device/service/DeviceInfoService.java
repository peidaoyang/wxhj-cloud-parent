package com.wxhj.cloud.device.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceInfoDO;

/**
 * @className DeviceInfoService.java
 * @author jwl
 * @date 2019年11月28日 下午4:32:24
 */
public interface DeviceInfoService {

	String insertCascade(DeviceInfoDO deviceInfoDO);

	DeviceInfoDO selectByDeviceId(String deviceId);

	void deleteById(String deviceId);

	List<DeviceInfoDO> list();

	PageInfo<DeviceInfoDO> listDevicePage(IPageRequestModel iPageRequestModel, String deviceModel);

	void update(DeviceInfoDO deviceInfoDO);

}
