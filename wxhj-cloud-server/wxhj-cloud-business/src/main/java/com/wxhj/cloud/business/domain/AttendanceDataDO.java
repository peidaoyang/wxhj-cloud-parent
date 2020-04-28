
/** 
 * @fileName: AttendanceDataDO.java  
 * @author: pjf
 * @date: 2019年12月20日 下午4:25:59 
 */

package com.wxhj.cloud.business.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;

import lombok.Data;
import lombok.ToString;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @className AttendanceDataDO.java
 * @author pjf
 * @date 2019年12月20日 下午4:25:59
 */
@Table(name = "attendance_data")
@Data
@ToString
@ExcelDocumentAnnotation
public class AttendanceDataDO {
	@Id
	@ExcelColumnAnnotation(columnName = "orderNumber")
	private String orderNumber;
	@ExcelColumnAnnotation(columnName = "matchingDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ColumnType(jdbcType = JdbcType.DATE)
	private Date matchingDate;
	@ExcelColumnAnnotation(columnName = "matchingTime")
	private Integer matchingTime;
	@ExcelColumnAnnotation(columnName = "attendanceId")
	private String attendanceId;
	@ExcelColumnAnnotation(columnName = "attendanceSequence")
	private Integer attendanceSequence;
	@ExcelColumnAnnotation(columnName = "extentType")
	private Integer extentType;
	@ExcelColumnAnnotation(columnName = "recordDatetime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordDatetime;
	@ExcelColumnAnnotation(columnName = "accountId")
	private String accountId;
	@ExcelColumnAnnotation(columnName = "sceneId")
	private String sceneId;
	@ExcelColumnAnnotation(columnName = "deviceId")
	private String deviceId;
	@ExcelColumnAnnotation(columnName = "serialNumber")
	private Long serialNumber;
	@ExcelColumnAnnotation(columnName = "organizeId")
	private String organizeId;
	@ExcelColumnAnnotation(columnName = "upTime")
	private Integer upTime;
	@ExcelColumnAnnotation(columnName = "downTime")
	private Integer downTime;
	@ExcelColumnAnnotation(columnName = "recordTimeStamp")
	private Long recordTimeStamp;

//	private Integer upDownMark;
}
