
/** 
 * @fileName: AttendanceDataMatchingDO.java  
 * @author: pjf
 * @date: 2019年12月23日 上午9:18:00 
 */

package com.wxhj.cloud.business.domain;


import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @className AttendanceDataMatchingDO.java
 * @author pjf
 * @date 2019年12月23日 上午9:18:00   
*/

@Table(name = "attendance_data_matching")
@Data
@ToString
public class AttendanceDataMatchingDO {
	@Id
	private String orderNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@ColumnType(jdbcType=JdbcType.DATE)
	private Date matchingDate;
	private Integer matchingTime;
	private String attendanceId;
	private Integer attendanceSequence;
	private Integer extentType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date recordDatetime;
	private String accountId;
	private String sceneId;
	private String deviceId;
	private Long serialNumber;
	private String organizeId;
	private Integer upTime;
	private Integer downTime;
	private Long recordTimeStamp;
	private Integer upDownMark;
}
