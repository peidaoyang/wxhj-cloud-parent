/** 
 * @fileName: CurrentAttendanceGroupRecService.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:40:20 
 */

package com.wxhj.cloud.business.service;
/**
 * @className CurrentAttendanceGroupRecService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:20   
*/
/** 
 * @className CurrentAttendanceGroupRecService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:20 
*/

import java.util.List;

import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;

public interface CurrentAttendanceGroupRecService {

	List<CurrentAttendanceGroupRecDO> selectByAttendanceGroupId(String attendanceGroupId);
	
	String insert(CurrentAttendanceGroupRecDO currentAttendanceGroupRecDO);
	
	void insertList(List<CurrentAttendanceGroupRecDO> listCurrentAttendanceGroupRecDO);
	
	void update(CurrentAttendanceGroupRecDO currentAttendanceGroupRecDO);
	
	void delete(String id);
}
