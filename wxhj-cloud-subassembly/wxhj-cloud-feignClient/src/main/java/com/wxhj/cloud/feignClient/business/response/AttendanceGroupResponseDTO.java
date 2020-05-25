package com.wxhj.cloud.feignClient.business.response;

import java.util.List;

import com.wxhj.cloud.feignClient.business.bo.AttendanceGroupRecBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "考勤组返回对象")
public class AttendanceGroupResponseDTO {
	@ApiModelProperty(value = "考勤组编号")
	private String id;
	@ApiModelProperty(value = "考勤组名称")
	private String fullName;
	@ApiModelProperty(value = "考勤类型")
	private Integer groupType;
	
	@ApiModelProperty(value = "组织编号")
	private String organizeId;
	@ApiModelProperty(value = "关联的组织年度安排id，只有在groupType=2时，该字段有值")
	private String organizeYearScheduleId;

	@ApiModelProperty(value = "考勤组详情列表")
	private List<AttendanceGroupRecBO> attendanceGroupRec;
	

	@ApiModelProperty(value = "用户集合")
	private List<String> accountList;
	@ApiModelProperty(value = "场景集合")
	private List<String> sceneList;
}
