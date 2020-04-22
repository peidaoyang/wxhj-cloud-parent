/** 
 * @fileName: AttendanceSingleDayFactory.java  
 * @author: pjf
 * @date: 2019年12月12日 下午4:32:57 
 */

package com.wxhj.cloud.business.attendance.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.wxhj.cloud.business.attendance.AttendanceStaticClass;
import com.wxhj.cloud.business.attendance.AttendanceSingleDayBO;
import com.wxhj.cloud.business.attendance.AttendanceSingleTimeBO;
import com.wxhj.cloud.business.bo.AttendanceDateMatchingBO;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
import com.wxhj.cloud.business.bo.AttendanceGroupBO;
import com.wxhj.cloud.feignClient.business.bo.AttendanceGroupRecBO;

/**
 * @className AttendanceSingleDayFactory.java
 * @author pjf
 * @date 2019年12月12日 下午4:32:57
 */

public class AttendanceSingleDayFactory {
	public static List<AttendanceSingleDayBO> createAttendanceDateMatching(AttendanceGroupBO attendanceGroup,
			List<AttendanceDayBO> attendanceDayList) {

		List<AttendanceSingleDayBO> attendanceSingleDayList = new ArrayList<>();

		List<AttendanceGroupRecBO> attendanceGroupRecList = attendanceGroup.getAttendanceGroupRecList();
		Integer groupType = attendanceGroup.getGroupType();
		List<String> attendanceDayIdList = attendanceGroupRecList.stream().map(q -> q.getAttendanceDayId()).distinct()
				.collect(Collectors.toList());
		for (String attendanceDayIdTemp : attendanceDayIdList) {
			//
			List<AttendanceGroupRecBO> attendanceGroupRecListTemp = attendanceGroupRecList.stream()
					.filter(q -> q.getAttendanceDayId().equals(attendanceDayIdTemp)).collect(Collectors.toList());
			AttendanceDateMatchingBO attendanceDateMatching = AttendanceDateMatchingFactory
					.createAttendanceDateMatchingList(attendanceGroupRecListTemp, groupType, attendanceDayIdTemp);
			//
			List<AttendanceSingleTimeBO> attendanceSingleTimeList = new ArrayList<>();

			AttendanceDayBO attendanceDay = attendanceDayList.stream()
					.filter(q -> q.getDayId().equals(attendanceDayIdTemp)).findFirst().get();

			attendanceSingleTimeList.addAll(AttendanceSingleTimeFactory.createAttendanceSingleTimeList(attendanceDay));

			Integer lastAttendanceOrder = attendanceSingleTimeList.get(attendanceSingleTimeList.size() - 1)
					.getAttendanceSequence();
			// 如果是工作日默认最后类型为5,如果为节假日则为6
//			Integer lastExtentType = 5;
			Integer lastExtentType = AttendanceStaticClass.AFTER_DOWN_WORK;
			// attendanceDayList.get(0)
			//
			if (attendanceDay.getAttendanceType() > 0) {
				lastExtentType = AttendanceStaticClass.MID_WEEK;
				// lastExtentType = 6;
			}
			//
			attendanceSingleDayList.add(new AttendanceSingleDayBO(attendanceGroup.getId(), lastAttendanceOrder,
					lastExtentType, attendanceDay.getAttendanceDayRec(), Arrays.asList(attendanceDateMatching),
					attendanceSingleTimeList));
		}
		return attendanceSingleDayList;
	}
}
