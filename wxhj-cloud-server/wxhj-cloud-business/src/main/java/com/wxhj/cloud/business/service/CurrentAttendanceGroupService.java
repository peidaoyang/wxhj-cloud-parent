/** 
 * @fileName: CurrentAttendanceGroupService.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:40:14 
 */

package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;

/**
 * @className CurrentAttendanceGroupService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:14   
*/


public interface CurrentAttendanceGroupService {
	CurrentAttendanceGroupDO selectById(String id);
	
	String insert(CurrentAttendanceGroupDO currentAttendanceGroupDO);
	
	//void update(CurrentAttendanceGroupDO currentAttendanceGroupDO);
	
	void delete(String id);
	
	List<CurrentAttendanceGroupDO> listById(List<String> id);
}
