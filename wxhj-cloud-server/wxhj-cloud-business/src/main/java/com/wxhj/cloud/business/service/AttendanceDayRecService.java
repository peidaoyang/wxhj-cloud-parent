/** 
 * @fileName: AttendanceDayRecService.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:01:28 
 */

package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.attenance.AttendanceDayRecBO;
import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @className AttendanceDayRecService.java
 * @author pjf
 * @date 2019年12月12日 上午11:01:28
 */

public interface AttendanceDayRecService {
	void insertList(List<AttendanceDayRecBO> attendanceDayRecList);
	
	void insert(AttendanceDayRecDO attendanceDayRec);
	
	void updateList(List<AttendanceDayRecBO> attendanceDayRecList);
	
	void update(AttendanceDayRecDO attendanceDayRec);
	
	void delete(String attendanceId);
	
	IPageResponseModel listAttendanceDayRec(IPageRequestModel pageRequestModel, String attendanceId);
	
	List<AttendanceDayRecDO> listAttendanceDayRecByAttendanceId(String attendanceId);
	
	List<AttendanceDayRecDO> listAttendanceDayRecById(String Id);
}
