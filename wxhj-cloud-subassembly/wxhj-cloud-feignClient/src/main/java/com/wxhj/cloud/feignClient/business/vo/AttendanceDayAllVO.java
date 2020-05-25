package com.wxhj.cloud.feignClient.business.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "班次全部返回对象")
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDayAllVO {
	@ApiModelProperty(value = "班次编号")
	private String id;
	@ApiModelProperty(value = "班次名称")
	private String fullName;
	@ApiModelProperty(value = "班次描述")
	private String timeDescribe;
	@ApiModelProperty(value = "是否是学生考勤。0：不是；1：是")
	private Integer studentAttendance;
}
