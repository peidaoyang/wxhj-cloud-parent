/**
 * @className CurrentAttendanceGroupDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:57:41
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @className CurrentAttendanceGroupDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:57:41
 */
@Data
@ToString
@Table(name = "current_attendance_group")
public class CurrentAttendanceGroupDO {
	@Id
	private String id;
	private String fullName;
	private Integer groupType;
	private String organizeId;
}
