/** 
 * @fileName: AttendanceDayRecDO.java  
 * @author: pjf
 * @date: 2019年12月12日 上午10:54:38 
 */

package com.wxhj.cloud.business.attendance;

import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceDayRecDO.java
 * @author pjf
 * @date 2019年12月12日 上午10:54:38
 */

@Data
@ToString
public class AttendanceDayRecBO {
	private String groupId;

	private String dayId;

	private Integer sequence;
	private Integer upTime;
	private Integer downTime;
	private Integer upExtent;
	private Integer downExtent;

	public Integer getStartUpTime() {
		return upTime - upExtent;
	}

	public Integer getEndUpTime() {
		return upTime;
	}

	public Integer getStartMiddleTime() {
		return upTime + 1;
	}

	public Integer getEndMiddleTime() {
		return downTime - 1;
	}

	public Integer getStartDownTime() {
		return downTime;
	}

	public Integer getEndDownTime() {
		return downTime + downExtent;
	}
}
