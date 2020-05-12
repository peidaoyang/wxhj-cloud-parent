/** 
 * @fileName: AttendanceDataMatchingService.java  
 * @author: pjf
 * @date: 2019年12月23日 上午9:23:35 
 */

package com.wxhj.cloud.business.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceDataMatchingDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className AttendanceDataMatchingService.java
 * @author pjf
 * @date 2019年12月23日 上午9:23:35   
*/
/**
 * @className AttendanceDataMatchingService.java
 * @author pjf
 * @date 2019年12月23日 上午9:23:35
 */

public interface AttendanceDataMatchingService {
	PageInfo<AttendanceDataMatchingDO> listPage(IPageRequestModel pageRequestModel, Date matchingDate, String accountId);

	List<AttendanceDataMatchingDO> list(AttendanceDataMatchingDO attendanceDataMatching);

	void insert(AttendanceDataMatchingDO attendanceDataMatching);

	void deleteByOrderNumber(String orderNumber);
}
