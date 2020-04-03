/**
 * 
 */
package com.wxhj.cloud.business.domain.view;

import javax.persistence.Table;

import lombok.Data;

/**
 * @ClassName: ViewAttendanceGroupAttendance.java
 * @author: cya
 * @Date: 2020年3月14日 下午2:13:17 
 */
@Data
@Table(name="view_attendance_group_attendance")
public class ViewAttendanceGroupAttendanceDO {
	private String attendanceGroupId;
	private Integer serialNumber;
	private String attendanceDayId;
	private String dayDescribe;
}
