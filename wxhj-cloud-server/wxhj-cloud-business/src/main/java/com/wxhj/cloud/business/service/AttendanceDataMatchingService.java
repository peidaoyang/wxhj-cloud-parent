/** 
 * @fileName: AttendanceDataMatchingService.java  
 * @author: pjf
 * @date: 2019年12月23日 上午9:23:35 
 */

package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.AttendanceDataMatchingDO;

/**
 * @className AttendanceDataMatchingService.java
 * @author pjf
 * @date 2019年12月23日 上午9:23:35   
*/
/**
 * @className AttendanceDataMatchingService.java
 * @author pjf
 * @date 2019年12月23日 上午9:23:35
 */

public interface AttendanceDataMatchingService {

	List<AttendanceDataMatchingDO> list(AttendanceDataMatchingDO attendanceDataMatching);

	void insert(AttendanceDataMatchingDO attendanceDataMatching);

	void deleteByOrderNumber(String orderNumber);
}
