/**
 * @className CurrentAttendanceGroupRecDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:58:56
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className CurrentAttendanceGroupRecDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:58:56
 */
@Data
@ToString
@Table(name = "current_attendance_group_rec")
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAttendanceGroupRecDO {
	@Id
	private String attendanceGroupId;
	@Id
	private Integer serialNumber;
	private String attendanceDayId;
}
