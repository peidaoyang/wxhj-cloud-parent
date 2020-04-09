/**
 * @className CurrentAttendanceDayRecDO.java
 * @admin jwl
 * @date 2019年12月19日 下午3:02:11
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @className CurrentAttendanceDayRecDO.java
 * @admin jwl
 * @date 2019年12月19日 下午3:02:11
 */
@Data
@ToString
@Table(name = "current_attendance_day_rec")
public class CurrentAttendanceDayRecDO {
	@Id
	//主表id并非主表的attendanceId
	private String dayId;
	@Id
	private Integer sequence;
	@Id
	//该值为主表的attendanceId
	private String groupId;
	private Integer upTime;
	private Integer downTime;
	private Integer upExtent;
	private Integer downExtent;
}
