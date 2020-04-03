/**
 * @className DeviceGlobalParameterService.java
 * @admin jwl
 * @date 2019年12月10日 上午11:54:53
 */
package com.wxhj.cloud.device.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.bo.DeviceGlobalParameterScreenBO;
import com.wxhj.cloud.device.domain.DeviceGlobalParameterDO;

/**
 * @className DeviceGlobalParameterService.java
 * @admin jwl
 * @date 2019年12月10日 上午11:54:53
 */
public interface DeviceGlobalParameterService {
	String insert(DeviceGlobalParameterDO deviceGlobalParameter);

	void updateCascade(DeviceGlobalParameterDO deviceGlobalParameter);

	void deleteCascade(String id);

	List<DeviceGlobalParameterDO> selectByDeviceState(DeviceGlobalParameterScreenBO deviceGlobalParameterScreen);

	PageInfo<DeviceGlobalParameterDO> listDeviceGlobalParameter(IPageRequestModel pageRequestModel, String nameValue,
			String organizeId);

	DeviceGlobalParameterDO selectById(String id);
}
