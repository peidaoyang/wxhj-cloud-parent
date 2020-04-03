/**
 * @className AttendanceGroupRecService.java
 * @admin jwl
 * @date 2019年12月13日 上午11:38:57
 */
package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.AttendanceGroupRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @className AttendanceGroupRecService.java
 * @admin jwl
 * @date 2019年12月13日 上午11:38:57
 */
public interface AttendanceGroupRecService {
	
	void insertList(List<AttendanceGroupRecDO> attendanceGroupList);
	
	void insert(AttendanceGroupRecDO attendanceGroupRec);
	
	void updateList(List<AttendanceGroupRecDO> attendanceGroupList);
	
	void update(AttendanceGroupRecDO attendanceGroupRec);
	
	void delete(String id);
	
	IPageResponseModel listAttendanceGroupRec(IPageRequestModel pageRequestModel);
	
	List<AttendanceGroupRecDO> listById(String id);
}
