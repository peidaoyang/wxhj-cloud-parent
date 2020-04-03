/** 
 * @fileName: AttendanceGroupRecDO.java  
 * @author: pjf
 * @date: 2019年12月12日 下午3:45:52 
 */

package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceGroupRecDO.java
 * @author pjf
 * @date 2019年12月12日 下午3:45:52   
*/

@Data
@ToString
@Table(name = "attendance_group_rec")
public class AttendanceGroupRecDO {
	@Id
	private String attendanceGroupId;
	@Id
	private Integer serialNumber;
	private String attendanceDayId;
	
	@Override
	public boolean equals(Object obj) {
		AttendanceGroupRecDO attendanceGroupRecTemp = (AttendanceGroupRecDO) obj;
		return this.attendanceDayId.equals(attendanceGroupRecTemp.getAttendanceDayId());
	}
}
