/** 
 * @fileName: AttendanceSingleTime.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:26:38 
 */

package com.wxhj.cloud.business.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className AttendanceSingleTime.java
 * @author pjf
 * @date 2019年12月12日 上午11:26:38   
*/
/**
 * @className AttendanceSingleTime.java
 * @author pjf
 * @date 2019年12月12日 上午11:26:38
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSingleTimeBO {
	private Integer attendanceSequence;
	// 1~6
	private Integer extentType;

	// 分钟
	private Integer startTime;

	private Integer endTime;

	public boolean matching(Integer dayTimeStamp) {
		return  dayTimeStamp >=startTime && dayTimeStamp<=endTime;
	}
}
