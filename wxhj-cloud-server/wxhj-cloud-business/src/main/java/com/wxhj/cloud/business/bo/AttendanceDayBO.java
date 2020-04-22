/** 
 * @fileName: AttendanceDayDO.java  
 * @author: pjf
 * @date: 2019年12月12日 上午10:51:59 
 */

package com.wxhj.cloud.business.bo;

import java.util.List;

import com.wxhj.cloud.business.attendance.AttendanceDayRecBO;

import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceDayDO.java
 * @author pjf
 * @date 2019年12月12日 上午10:51:59
 */

@Data
@ToString
public class AttendanceDayBO {

	private String groupId;

	private String dayId;

	private String fullName;

	private String organizeId;

	private Integer attendanceType;
	
	//private String timeDescribe;
	
	private List<AttendanceDayRecBO> attendanceDayRec;

}
