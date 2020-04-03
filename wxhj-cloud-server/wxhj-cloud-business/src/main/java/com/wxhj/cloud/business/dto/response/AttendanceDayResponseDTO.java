/**
 * @className AttendanceDayResponseDTO.java
 * @admin jwl
 * @date 2019年12月16日 上午8:56:05
 */
package com.wxhj.cloud.business.dto.response;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wxhj.cloud.business.domain.AttendanceDayRecDO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className AttendanceDayResponseDTO.java
 * @admin jwl
 * @date 2019年12月16日 上午8:56:05
 */
@Data
@ToString
@ApiModel(value="班次返回对象")
public class AttendanceDayResponseDTO {
	@ApiModelProperty(value = "班次编号")
	private String id;
	@ApiModelProperty(value = "班次名称",example="班次1")
	@NotNull
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
	@ApiModelProperty(value = "班次详情设计")
	private List<AttendanceDayRecDO> listAttendanceDayRec;
}
