/** 
 * @fileName: DeviceStateService.java  
 * @author: pjf
 * @date: 2020年2月11日 下午2:15:31 
 */

package com.wxhj.cloud.device.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceStateDO;

import java.util.Date;

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

	/**
	 *	根据连接时间查询设备（半小时内在线，半小时外离线）
	 * @param pageRequestModel
	 * @param organizeId
	 * @param lineType: 0，全部设备，1：上次连接时间半小时内，2：上次连接时间半小时外
	 * @return
	 */
	PageInfo<DeviceStateDO> listPage(IPageRequestModel pageRequestModel, String organizeId, Integer lineType);

	int countGreaterThanLastTime(Date time,String organizeId);

	int countDevice(String organizeId);
}
