/** 
 * @fileName: AttendanceMatchingBO.java  
 * @author: pjf
 * @date: 2019年12月18日 上午9:28:07 
 */

package com.wxhj.cloud.business.bo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceMatchingBO.java
 * @author pjf
 * @date 2019年12月18日 上午9:28:07   
*/
/**
 * @className AttendanceMatchingBO.java
 * @author pjf
 * @date 2019年12月18日 上午9:28:07
 */
@Data
@ToString
public class AttendanceMatchingBO {
	private Date matchingDate;
	private Integer matchingTime;

	private String attendanceId;

	private Integer attendanceSequence;

	private Integer extentType;

	private Integer upTime;
	private Integer downTime;
	private Integer upExtent;
	private Integer downExtent;

}
