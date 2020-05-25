/** 
 * @fileName: AttendanceDayService.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:00:41 
 */

package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
import com.wxhj.cloud.business.domain.AttendanceDayDO;
import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.util.List;
import java.util.Set;

/**
 * @className AttendanceDayService.java
 * @author pjf
 * @date 2019年12月12日 上午11:00:41
 */

public interface AttendanceDayService {

	String insertCascade(AttendanceDayDO attendanceDay,List<AttendanceDayRecDO> attendanceDayRecList);
	
	void updateCascade(AttendanceDayDO attendanceDay,List<AttendanceDayRecDO> attendanceDayRecList);
	
	void delete(String id);
	
	void delete(List<String> id);
	
	List<AttendanceDayDO> listByOrganizeIdAndStudentAttendance(String organizeId, Integer studentAttendance);

	List<AttendanceDayDO> listByIdList(List<String> idList);

	PageInfo<AttendanceDayDO> listByFullNameAndStudentAttendance(IPageRequestModel pageRequestModel,
																 String fullName, String organizeId, Integer studentAttendance);

	AttendanceDayDO selectById(String id);
	
}
