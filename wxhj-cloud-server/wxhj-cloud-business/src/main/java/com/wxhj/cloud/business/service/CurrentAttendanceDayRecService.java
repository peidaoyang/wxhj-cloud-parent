/** 
 * @fileName: CurrentAttendanceDayRecService.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:40:36 
 */

package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.CurrentAttendanceDayRecDO;

/**
 * @className CurrentAttendanceDayRecService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:36   
*/
/**
 * @className CurrentAttendanceDayRecService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:36
 */

public interface CurrentAttendanceDayRecService {

	List<CurrentAttendanceDayRecDO> selectByAttendanceIdAndId(String attendanceId, String id);
	
	String insert(CurrentAttendanceDayRecDO cuttentAttendanceDayRecDO);
	
	void insertList(List<CurrentAttendanceDayRecDO> listCurrent);
	
	void update(CurrentAttendanceDayRecDO currentAttendanceDayRecDO);
	
	void delete(String id);
}
