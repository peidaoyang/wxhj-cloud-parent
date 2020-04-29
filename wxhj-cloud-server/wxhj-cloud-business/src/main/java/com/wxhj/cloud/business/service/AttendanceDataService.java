/**
 * @className AttendanceDataService.java
 * @author jwl
 * @date 2019年12月23日 下午3:42:51
 */
package com.wxhj.cloud.business.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @className AttendanceDataService.java
 * @author jwl
 * @date 2019年12月23日 下午3:42:51
 */
public interface AttendanceDataService {

	void insertCascade(AttendanceDataDO attendanceData);

	AttendanceDataDO select(String id);
	
//	List<AttendanceDataDO> listByDayDate(Date beginTime, Date endTime,
//			String organizeId, List<String> accountId);
//
//	IPageResponseModel listByMonthDate(IPageRequestModel pageRequestModel, Date beginTime, Date endTime,
//			List<String> accountId);

	PageInfo<AttendanceDataDO> listPage(IPageRequestModel pageRequestModel,Date beginTime, Date endTime,String organizeId,String nameValue);
	List<AttendanceDataDO> list(Date beginTime, Date endTime,String organizeId);
}

