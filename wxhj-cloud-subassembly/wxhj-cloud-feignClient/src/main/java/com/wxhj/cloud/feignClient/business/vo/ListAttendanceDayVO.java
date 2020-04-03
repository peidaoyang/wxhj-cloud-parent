package com.wxhj.cloud.feignClient.business.vo;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListAttendanceDayVO implements IOrganizeModel{
	@ApiModelProperty(value="班次主键")
	private String id;
	@ApiModelProperty(value="班次名称")
	private String fullName;
	@ApiModelProperty(value="班次类型")
	private Integer attendanceType;
	@ApiModelProperty(value="时间说明")
	private String timeDescribe;
	
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序 ）")
	private String organizeName;
}
