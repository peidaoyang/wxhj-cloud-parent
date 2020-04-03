/** 
 * @fileName: AttendanceSingleDay.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:32:31 
 */

package com.wxhj.cloud.business.attenance;

import java.util.Date;
import java.util.List;

import com.wxhj.cloud.business.bo.AttendanceDateMatchingBO;
import com.wxhj.cloud.business.bo.AttendanceMatchingBO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className AttendanceSingleDay.java
 * @author pjf
 * @date 2019年12月12日 上午11:32:31
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSingleDayBO {
	private String attendanceId;

	private Integer lastAttendanceSequence;
	// 1~6
	private Integer lastExtentType;

	// 打开规则原始数据
	List<AttendanceDayRecBO> attendanceDayRec;

	// 日期匹配规则
	private List<AttendanceDateMatchingBO> attendanceDateMatchingList;
	// 时间匹配规则
	private List<AttendanceSingleTimeBO> attendanceSingleTimeList;

	public AttendanceMatchingBO matching(Date date, Integer dayTimeStamp, AttendanceMatchingTypeEnum matchingType) {
		AttendanceMatchingBO attendanceMatching = new AttendanceMatchingBO();
		attendanceMatching.setAttendanceId(attendanceId);
		attendanceMatching.setMatchingDate(date);
		attendanceMatching.setMatchingTime(dayTimeStamp);
		Boolean isMatching = false;
		// 匹配日期
		for (AttendanceDateMatchingBO attendanceDateMatchingTemp : attendanceDateMatchingList) {
			isMatching = attendanceDateMatchingTemp.matching(date);
			if (!isMatching) {
				break;
			}
		}
		if (!isMatching) {
			return null;
		}
		isMatching = false;
		for (AttendanceSingleTimeBO attendanceSingleTimeTemp : attendanceSingleTimeList) {
			isMatching = attendanceSingleTimeTemp.matching(dayTimeStamp);
			if (isMatching) {
				attendanceMatching = combinationAttendanceMatching(attendanceMatching,
						attendanceSingleTimeTemp.getAttendanceSequence(), attendanceSingleTimeTemp.getExtentType());
			}
		}
		AttendanceSingleTimeBO attendanceSingleTimeBO = attendanceSingleTimeList
				.get(attendanceSingleTimeList.size() - 1);
		//
		if (dayTimeStamp > attendanceSingleTimeBO.getEndTime() && dayTimeStamp <= AttenanceStaticClass.DAY_MINUTE) {
			attendanceMatching = combinationAttendanceMatching(attendanceMatching, lastAttendanceSequence,
					lastExtentType);
			return attendanceMatching;
		}
		if (AttendanceMatchingTypeEnum.MATCHING_TYPE3.equals(matchingType)) {
			attendanceMatching = combinationAttendanceMatching(attendanceMatching, lastAttendanceSequence,
					lastExtentType);
			return attendanceMatching;
		}

		return attendanceMatching;
	}

	private AttendanceMatchingBO combinationAttendanceMatching(AttendanceMatchingBO attendanceMatching,
			Integer attendanceSequence, Integer extentType) {
		// AttendanceMatchingBO attendanceMatching = new AttendanceMatchingBO();
		attendanceMatching.setAttendanceSequence(attendanceSequence);
		attendanceMatching.setExtentType(extentType);
		int attendanceDayRecIndex = attendanceMatching.getAttendanceSequence() - 1;
		attendanceMatching.setUpTime(attendanceDayRec.get(attendanceDayRecIndex).getUpTime());
		attendanceMatching.setDownTime(attendanceDayRec.get(attendanceDayRecIndex).getDownTime());
		attendanceMatching.setUpExtent(attendanceDayRec.get(attendanceDayRecIndex).getUpExtent());
		attendanceMatching.setDownExtent(attendanceDayRec.get(attendanceDayRecIndex).getDownExtent());

		return attendanceMatching;
	}
}
