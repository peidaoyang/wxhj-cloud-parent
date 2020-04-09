/** 
 * @fileName: AttendanceDayService.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:00:41 
 */

package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
import com.wxhj.cloud.business.domain.AttendanceDayDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.util.List;
import java.util.Set;

/**
 * @className AttendanceDayService.java
 * @author pjf
 * @date 2019年12月12日 上午11:00:41
 */

public interface AttendanceDayService {

	String insertCascade(AttendanceDayBO attendanceDay);
	
	void updateCascade(AttendanceDayBO attendanceDay);
	
	void delete(String id);
	
	void delete(List<String> id);
	
	List<AttendanceDayDO> listByOrganizeId(String organizeId);

	List<AttendanceDayDO> listById(Set<String> idList);

	PageInfo<AttendanceDayDO> listByFullName(IPageRequestModel pageRequestModel,
			String fullName, String organizeId);

	AttendanceDayDO selectById(String id);
	
}
