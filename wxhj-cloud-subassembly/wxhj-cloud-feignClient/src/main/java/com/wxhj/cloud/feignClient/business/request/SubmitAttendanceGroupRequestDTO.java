/**
 * @className SubmitAttendanceGroupRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 下午2:46:45
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.wxhj.cloud.feignClient.business.bo.AttendanceGroupRecBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SubmitAttendanceGroupRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 下午2:46:45
 */
@Data
@ApiModel(value = "编辑考勤组请求对象")
public class SubmitAttendanceGroupRequestDTO {
	@ApiModelProperty(value = "考勤组编号")
	@NotNull
	private String id;
	@ApiModelProperty(value = "考勤组名称")
	@NotBlank
	private String fullName;
	@ApiModelProperty(value = "考勤类型")
	@Min(0)
	@Max(1)
	private Integer groupType;
	@ApiModelProperty(value = "组织id")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value = "考勤组详情列表")
	@NotEmpty
	private List<AttendanceGroupRecBO> attendanceGroupRecList;
	// 以上为原有部分
	@ApiModelProperty(value = "场景idList")
	private List<String> sceneIdList;
	@ApiModelProperty(value = "用户编号")
	private List<String> accountIdList;

	@ApiModelProperty(value="是否自动同步人员权限,0：不自动，1：自动")
	@Min(0)
	@Max(1)
	private Integer autoSynchro;
}
