/**
 * 
 */
package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.view.ViewAttendanceGroupAttendanceDO;

/**
 * @ClassName: ViewAttendanceGroupAttendanceService.java
 * @author: cya
 * @Date: 2020年3月14日 下午2:19:09 
 */
public interface ViewAttendanceGroupAttendanceService {
	List<ViewAttendanceGroupAttendanceDO> listById(String id);
}
