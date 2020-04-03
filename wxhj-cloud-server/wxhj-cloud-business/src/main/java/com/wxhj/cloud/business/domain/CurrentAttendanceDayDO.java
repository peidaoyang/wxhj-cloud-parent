/**
 * @className CurrentAttendanceDayDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:55:38
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @className CurrentAttendanceDayDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:55:38
 */
@Data
@Table(name = "current_attendance_day")
public class CurrentAttendanceDayDO {
	@Id
	private String id;
	private String attendanceId;
	private String fullName;
	private String organizeId;
	private Integer attendanceType;
}
