/** 
 * @fileName: AttendanceDayService.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:00:41 
 */

package com.wxhj.cloud.business.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
import com.wxhj.cloud.business.domain.AttendanceDayDO;
import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

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
	
	List<AttendanceDayDO> listByOrganizeId(String organizeId);
	
	PageInfo<AttendanceDayDO> listByFullName(IPageRequestModel pageRequestModel,
			String fullName, String organizeId);
	
	AttendanceDayDO selectById(String id);
	
}
