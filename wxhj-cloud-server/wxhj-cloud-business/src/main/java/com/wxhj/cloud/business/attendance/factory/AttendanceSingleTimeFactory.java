/** 
 * @fileName: AttendanceSingleDayFactory.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:50:42 
 */

package com.wxhj.cloud.business.attendance.factory;

import java.util.ArrayList;
import java.util.List;

import com.wxhj.cloud.business.attendance.AttendanceDayRecBO;
import com.wxhj.cloud.business.attendance.AttendanceSingleTimeBO;
import com.wxhj.cloud.business.bo.AttendanceDayBO;

/**
 * @className AttendanceSingleDayFactory.java
 * @author pjf
 * @date 2019年12月12日 上午11:50:42
 */
public class AttendanceSingleTimeFactory {
	public static List<AttendanceSingleTimeBO> createAttendanceSingleTimeList(AttendanceDayRecBO attendanceDayRec,
			Integer attendanceType) {
		List<AttendanceSingleTimeBO> attendanceSingleTimeList = new ArrayList<>();
		// 周末逻辑
		if (attendanceType == 1) {
			attendanceSingleTimeList.add(new AttendanceSingleTimeBO(attendanceDayRec.getSequence(), 6,
					attendanceDayRec.getUpTime(), attendanceDayRec.getDownTime()));
		}
		// 工作日逻辑
		else {
			attendanceSingleTimeList.add(new AttendanceSingleTimeBO(attendanceDayRec.getSequence(), 2,
					attendanceDayRec.getStartUpTime(), attendanceDayRec.getEndUpTime()));
			if (attendanceDayRec.getStartMiddleTime() <= attendanceDayRec.getEndMiddleTime()) {
				attendanceSingleTimeList.add(new AttendanceSingleTimeBO(attendanceDayRec.getSequence(), 3,
						attendanceDayRec.getStartMiddleTime(), attendanceDayRec.getEndMiddleTime()));
			}
			attendanceSingleTimeList.add(new AttendanceSingleTimeBO(attendanceDayRec.getSequence(), 4,
					attendanceDayRec.getStartDownTime(), attendanceDayRec.getEndDownTime()));
		}

		return attendanceSingleTimeList;
	}

	public static List<AttendanceSingleTimeBO> createAttendanceSingleTimeList(AttendanceDayBO attendanceDay) {
		List<AttendanceSingleTimeBO> attendanceSingleTimeList = new ArrayList<>();
		attendanceDay.getAttendanceDayRec().forEach(q -> {
			List<AttendanceSingleTimeBO> attendanceSingleTimeListTemp = createAttendanceSingleTimeList(q,
					attendanceDay.getAttendanceType());
			int lastIndex = attendanceSingleTimeList.size() - 1;
			if (lastIndex >= 0 && attendanceSingleTimeList.get(lastIndex).getEndTime()
					- attendanceSingleTimeListTemp.get(0).getStartTime() < 0) {
				AttendanceSingleTimeBO lastAttendanceSingleTime = attendanceSingleTimeList.get(lastIndex);
				attendanceSingleTimeList.add(new AttendanceSingleTimeBO(
						lastAttendanceSingleTime.getAttendanceSequence(), 5, lastAttendanceSingleTime.getEndTime() + 1,
						attendanceSingleTimeListTemp.get(0).getStartTime() - 1));
			}
			attendanceSingleTimeList.addAll(attendanceSingleTimeListTemp);
		});

		if (attendanceSingleTimeList.get(0).getStartTime() != 0) {
			AttendanceSingleTimeBO lastAttendanceSingleTime = attendanceSingleTimeList.get(0);
			attendanceSingleTimeList.add(0, new AttendanceSingleTimeBO(lastAttendanceSingleTime.getAttendanceSequence(),
					1, 0, lastAttendanceSingleTime.getStartTime() - 1));
		}

		return attendanceSingleTimeList;
	}

}
