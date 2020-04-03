/** 
 * @fileName: ViewAttendanceAsyncSummaryService.java  
 * @author: pjf
 * @date: 2020年2月20日 下午4:12:18 
 */

package com.wxhj.cloud.business.service;

import java.util.Date;
import java.util.List;

import com.wxhj.cloud.business.domain.view.ViewAttendanceAsyncSummaryDO;

/**
 * @className ViewAttendanceAsyncSummaryService.java
 * @author pjf
 * @date 2020年2月20日 下午4:12:18   
*/
/**
 * @className ViewAttendanceAsyncSummaryService.java
 * @author pjf
 * @date 2020年2月20日 下午4:12:18
 */

public interface ViewAttendanceAsyncSummaryService {
	List<ViewAttendanceAsyncSummaryDO> listByDatetimeAndOrganize(Date startDate, Date endDate, String organizeId);

	List<ViewAttendanceAsyncSummaryDO> listByDatetimeAndAccount(Date startDate, Date endDate, String accountId);
}
