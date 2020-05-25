/** 
 * @fileName: AttendanceGroupDO.java  
 * @author: pjf
 * @date: 2019年12月12日 下午3:35:36 
 */

package com.wxhj.cloud.business.domain;



import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @className AttendanceGroupDO.java
 * @author pjf
 * @date 2019年12月12日 下午3:35:36
 */

@ToString
@Table(name = "attendance_group")
@Data
public class AttendanceGroupDO {
	@Id
	private String id;
	private String fullName;
	private Integer groupType;
	private String organizeId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime applyDate;
	private Integer studentGroup;
	private String organizeYearScheduleId;
}
