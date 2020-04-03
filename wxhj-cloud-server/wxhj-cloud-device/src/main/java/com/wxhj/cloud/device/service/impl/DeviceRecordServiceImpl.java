/** 
 * @fileName: DeviceRecordServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月11日 下午3:26:53 
 */

package com.wxhj.cloud.device.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.DeviceRecordDO;
import com.wxhj.cloud.device.mapper.DeviceRecordMapper;
import com.wxhj.cloud.device.service.DeviceRecordService;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className DeviceRecordServiceImpl.java
 * @author pjf
 * @date 2020年2月11日 下午3:26:53
 */

@Service
public class DeviceRecordServiceImpl implements DeviceRecordService {
	@Resource
	DeviceRecordMapper deviceRecordMapper;

	@Override
	public void insert(DeviceRecordDO deviceRecord) {
		deviceRecordMapper.insert(deviceRecord);

	}

	@Override
	public IPageResponseModel listByRecordTypeAndOragnizeId(Integer recordType, String organizeId,
			IPageRequestModel pageRequestModel) {
		Example example = new Example(DeviceRecordDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("recordType", recordType);

		PageInfo<DeviceRecordDO> deviceRecordList = PageUtil.selectPageList(pageRequestModel,
				() -> deviceRecordMapper.selectByExample(example));
		return PageUtil.initPageResponseModel(deviceRecordList, new PageDefResponseModel());

	}

	@Override
	public void updateByIdList(DeviceRecordDO deviceRecord, List<Long> id) {
		Example example = new Example(DeviceRecordDO.class);
		example.createCriteria().andIn("id", id);
		deviceRecordMapper.updateByExampleSelective(deviceRecord, example);
	}

	@Override
	public void updateByDeviceIdAndNoAndStamp(DeviceRecordDO deviceRecord, String deviceId, Long serialNumber,
			Long recordTimeStamp) {
		Example example = new Example(DeviceRecordDO.class);
		example.createCriteria().andEqualTo("deviceId", deviceId).andEqualTo("serialNumber", serialNumber)
				.andEqualTo("recordTimeStamp", recordTimeStamp);
		deviceRecordMapper.updateByExampleSelective(deviceRecord, example);
	}

}
