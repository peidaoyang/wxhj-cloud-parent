/** 
 * @fileName: AttendanceDateMatchingFactory.java  
 * @author: pjf
 * @date: 2019年12月12日 下午3:49:01 
 */

package com.wxhj.cloud.business.attendance.factory;

import java.util.List;
import java.util.stream.Collectors;

import com.wxhj.cloud.business.bo.AttendanceDateMatchingBO;
import com.wxhj.cloud.feignClient.business.bo.AttendanceGroupRecBO;

/**
 * @className AttendanceDateMatchingFactory.java
 * @author pjf
 * @date 2019年12月12日 下午3:49:01
 */

public class AttendanceDateMatchingFactory {
	/*
	 * 当前提供两个简单标准模板按周和按月
	 */
	public static AttendanceDateMatchingBO createAttendanceDateMatchingList(
			List<AttendanceGroupRecBO> attendanceGroupRecList, Integer groupType, String attendanceDayId) {
		List<String> matchingValueList = attendanceGroupRecList.stream()
				.filter(q -> q.getAttendanceDayId().equals(attendanceDayId)).map(q -> q.getSerialNumber().toString())
				.collect(Collectors.toList());
		return new AttendanceDateMatchingBO(groupType == 0 ? "W" : "D", true, matchingValueList);
	}
}
