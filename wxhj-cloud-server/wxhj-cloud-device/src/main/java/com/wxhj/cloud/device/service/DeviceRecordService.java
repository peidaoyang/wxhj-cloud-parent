/** 
 * @fileName: DeviceRecordService.java  
 * @author: pjf
 * @date: 2020年2月11日 下午3:26:35 
 */

package com.wxhj.cloud.device.service;

import java.util.List;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.device.domain.DeviceRecordDO;

/**
 * @className DeviceRecordService.java
 * @author pjf
 * @date 2020年2月11日 下午3:26:35   
*/
/**
 * @className DeviceRecordService.java
 * @author pjf
 * @date 2020年2月11日 下午3:26:35
 */

public interface DeviceRecordService {
	void insert(DeviceRecordDO deviceRecord);
	// void update(DeviceRecordDO deviceRecord);

	void updateByDeviceIdAndNoAndStamp(DeviceRecordDO deviceRecord, String deviceId, Long serialNumber,
			Long recordTimeStamp);

	IPageResponseModel listByRecordTypeAndOragnizeId(Integer recordType, String oraginzeId,
			IPageRequestModel pageRequestModel);

	// void update(String id);

	void updateByIdList(DeviceRecordDO deviceRecord, List<Long> id);
}
