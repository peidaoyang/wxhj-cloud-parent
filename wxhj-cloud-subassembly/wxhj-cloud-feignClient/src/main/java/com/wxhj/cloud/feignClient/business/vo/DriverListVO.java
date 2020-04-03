package com.wxhj.cloud.feignClient.business.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DriverListVO implements IOrganizeModel{
	@ApiModelProperty(value="主键")
	private String id;
	@ApiModelProperty(value="工号")
	private String jobNumber;
	@ApiModelProperty(value="驾驶证号码")
	private String driverNumber;
	@ApiModelProperty(value="账号id")
	private String accountId;
	@ApiModelProperty(value="姓名")
	private String name;
	@ApiModelProperty(value="身份证 编号")
	private String idNumber;
	
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(value="创建时间")
	private Date creatorTime;
	@ApiModelProperty(value="操作人员")
	private String creatorUserId;
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
}
