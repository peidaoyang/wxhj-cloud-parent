/** 
 * @fileName: AttendanceDayDO.java  
 * @author: pjf
 * @date: 2019年12月12日 上午10:51:59 
 */

package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;

import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceDayDO.java
 * @author pjf
 * @date 2019年12月12日 上午10:51:59
 */

@Table(name = "attendance_day")
@Data
@ToString
@ExcelDocumentAnnotation
public class AttendanceDayDO {
	@Id
	@ExcelColumnAnnotation(columnName = "id")
	private String id;
	@ExcelColumnAnnotation(columnName = "fullName")
	private String fullName;
	@ExcelColumnAnnotation(columnName = "organizeId")
	private String organizeId;
	@ExcelColumnAnnotation(columnName = "attendanceType")
	private Integer attendanceType;
	@ExcelColumnAnnotation(columnName = "timeDescribe")
	private String timeDescribe;
}
