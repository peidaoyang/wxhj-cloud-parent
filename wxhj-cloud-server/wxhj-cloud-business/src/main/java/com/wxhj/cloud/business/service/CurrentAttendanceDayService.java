/** 
 * @fileName: CurrentAttendanceDayService.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:40:07 
 */

package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.CurrentAttendanceDayDO;

/**
 * @className CurrentAttendanceDayService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:07   
*/
/** 
 * @className CurrentAttendanceDayService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:07 
*/

public interface CurrentAttendanceDayService {

	List<CurrentAttendanceDayDO> selectByAttendanceId(String attendanceId);
	
	List<CurrentAttendanceDayDO> listById(List<String> id);
	
	String insert(CurrentAttendanceDayDO currentAttendanceDayDO);
	
	void insertList(List<CurrentAttendanceDayDO> listCurrentAttendanceDayDO);
	
	void update(CurrentAttendanceDayDO currentAttendanceDayDO);
	
	void delete(String id);
}
