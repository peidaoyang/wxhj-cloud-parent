/**
 * @className ViewDeviceResourceService.java
 * @admin jwl
 * @date 2020年1月7日 下午2:27:36
 */
package com.wxhj.cloud.device.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.view.ViewDeviceResourceDO;

/**
 * @className ViewDeviceResourceService.java
 * @admin jwl
 * @date 2020年1月7日 下午2:27:36
 */
public interface ViewDeviceResourceService {
	PageInfo<ViewDeviceResourceDO> listPage(IPageRequestModel pageRequestModel, String organizeId, Integer deviceType,
			String deviceName);
	
	List<ViewDeviceResourceDO> listByDeviceId(String deviceId);
}
