/**
 * @className ViewDeviceResourceServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 下午2:29:10
 */
package com.wxhj.cloud.device.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.view.ViewDeviceResourceDO;
import com.wxhj.cloud.device.mapper.ViewDeviceResourceMapper;
import com.wxhj.cloud.device.service.ViewDeviceResourceService;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className ViewDeviceResourceServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 下午2:29:10
 */
@Service
public class ViewDeviceResourceServiceImpl implements ViewDeviceResourceService {

	@Resource
	ViewDeviceResourceMapper viewDeviceResourceMapper;

	@Override
	public PageInfo<ViewDeviceResourceDO> listPage(IPageRequestModel pageRequestModel, String organizeId,
			Integer deviceType, String deviceName) {
		Example example = new Example(ViewDeviceResourceDO.class);
		if (deviceType == -1) {
			example.createCriteria().andEqualTo("organizeId", organizeId).andLike("deviceName", "%" + deviceName + "%");
		} else {
			example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("deviceType", deviceType)
					.andLike("deviceName", "%" + deviceName + "%");
		}
		PageInfo<ViewDeviceResourceDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
				() -> viewDeviceResourceMapper.selectByExample(example));
		return pageAttendanceData;
	}

	@Override
	public List<ViewDeviceResourceDO> listByDeviceId(String deviceId) {
		Example example = new Example(ViewDeviceResourceDO.class);
		example.createCriteria().andEqualTo("deviceId", deviceId);
		return viewDeviceResourceMapper.selectByExample(example);
	}
}
