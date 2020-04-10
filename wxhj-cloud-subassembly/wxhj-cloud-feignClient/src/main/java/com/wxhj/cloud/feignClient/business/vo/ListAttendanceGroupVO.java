package com.wxhj.cloud.feignClient.business.vo;

import java.util.Date;

import com.wxhj.cloud.feignClient.bo.IAuthoritySynchroModel;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListAttendanceGroupVO implements IOrganizeModel, IAuthoritySynchroModel {
	@ApiModelProperty(value="考勤规则id")
	private String id;
	@ApiModelProperty(value="名称")
	private String fullName;
	@ApiModelProperty(value="组类型0为按周，1为按月")
	private Integer groupType;
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	
	@ApiModelProperty(value="应用时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date applyDate;

	@ApiModelProperty(value="自动同步类型：0，不同步，1，同步")
	private Integer autoSynchro;
}
