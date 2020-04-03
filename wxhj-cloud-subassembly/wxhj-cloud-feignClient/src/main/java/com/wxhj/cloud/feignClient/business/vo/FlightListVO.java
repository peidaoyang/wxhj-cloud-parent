package com.wxhj.cloud.feignClient.business.vo;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FlightListVO implements IOrganizeModel{
	@ApiModelProperty(value="主键")
	private String id;
	@ApiModelProperty(value="班次编号")
	private String flightNumber;
	@ApiModelProperty(value="线路编号")
	private String routeNumber;
	@ApiModelProperty(value="车号")
	private String carNumber;
	@ApiModelProperty(value="开始时间")
	private Integer startTime;
	
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
}