/** 
 * @fileName: AttendanceGroupRecDO.java  
 * @author: pjf
 * @date: 2019年12月12日 下午3:45:52 
 */

package com.wxhj.cloud.feignClient.business.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceGroupRecDO.java
 * @author pjf
 * @date 2019年12月12日 下午3:45:52   
*/
/**
 * @className AttendanceGroupRecDO.java
 * @author pjf
 * @date 2019年12月12日 下午3:45:52
 */
@Data
@ToString
public class AttendanceGroupRecBO {
	private String attendanceGroupId;
	private Integer serialNumber;
	private String attendanceDayId;
	private String dayDescribe;
}
