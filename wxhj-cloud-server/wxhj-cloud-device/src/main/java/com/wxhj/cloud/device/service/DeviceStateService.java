/** 
 * @fileName: DeviceStateService.java  
 * @author: pjf
 * @date: 2020年2月11日 下午2:15:31 
 */

package com.wxhj.cloud.device.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceStateDO;

/**
 * @className DeviceStateService.java
 * @author pjf
 * @date 2020年2月11日 下午2:15:31
 */

public interface DeviceStateService {
	void insert(DeviceStateDO deviceState);

	void update(DeviceStateDO deviceState);

	void replace(DeviceStateDO deviceState);

	DeviceStateDO selectOneById(String deviceId);

	public PageInfo<DeviceStateDO> listPage(IPageRequestModel pageRequestModel, String organizeId);
}
