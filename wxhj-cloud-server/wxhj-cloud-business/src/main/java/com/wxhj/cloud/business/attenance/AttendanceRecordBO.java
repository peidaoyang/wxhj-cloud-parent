/** 
 * @fileName: AttendanceRecordBO.java  
 * @author: pjf
 * @date: 2019年12月17日 下午2:18:18 
 */

package com.wxhj.cloud.business.attenance;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceRecordBO.java
 * @author pjf
 * @date 2019年12月17日 下午2:18:18   
*/
/**
 * @className AttendanceRecordBO.java
 * @author pjf
 * @date 2019年12月17日 下午2:18:18
 */
@Data
@ToString
public class AttendanceRecordBO {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordDatetime;

	//设备号_设备流水号_时间戳
	private String orderNumber;
	//设备流水号
	private Long serialNumber;
	//用户id
	private String accountId;
	//场景id
	private String sceneId;
	//设备id
	private String deviceId;
	//组织id
	private String organizeId;
}
