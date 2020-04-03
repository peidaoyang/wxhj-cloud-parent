/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: VisitorInfoApp.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:58:49 
 */
@ApiModel(value="app访客信息查询请求对象")
@Data
public class VisitorInfoAppRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value="账户id",example = "0000000028")
	@NotNull
	private String accountId;
	
	@ApiModelProperty(value="开始时间",example="2020-01-01")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginTime;
	@ApiModelProperty(value="结束时间",example="2020-01-01")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "creator_time")
	private String orderBy;
}
