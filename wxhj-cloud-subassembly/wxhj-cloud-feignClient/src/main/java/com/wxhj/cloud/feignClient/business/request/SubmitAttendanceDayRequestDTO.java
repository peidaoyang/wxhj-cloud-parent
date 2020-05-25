/**
 * @className SubmitAttendanceDayRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 下午1:55:37
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SubmitAttendanceDayRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 下午1:55:37
 */
@Data
@ToString
@ApiModel(value="编辑班次请求对象")
public class SubmitAttendanceDayRequestDTO {
	@ApiModelProperty(value = "班次编号")
	private String id;
	@ApiModelProperty(value = "班次名称",example="班次1")
	@NotBlank
	private String fullName;
	@ApiModelProperty(value = "组织编号",example="guid")
	@NotNull
	private String organizeId;
	@ApiModelProperty(value = "班次类型",example="0")
	@Min(0)
	@Max(1)
	private Integer attendanceType;
	@ApiModelProperty(value = "时间范围描述",example="09:00-12:00 13:30-16:30")
	private String timeDescribe;
	@ApiModelProperty(value = "是否是学生考勤。0：不是；1：是",example="0")
	private Integer studentAttendance;
	@ApiModelProperty(value = "班次详情设计")
	private List<SubmitAttendanceDayRecRequestDTO> attendanceDayRec;
}
