package com.wxhj.cloud.feignClient.account.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("app个人消费汇总查询 请求对象")
public class AppConsumeInfoSummaryRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "账户id",example = "0000000052")
	@NotBlank
	private String accountId;
	@ApiModelProperty(value = "开始时间", example = "2019-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@ApiModelProperty(value = "结束时间", example = "2020-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	@ApiModelProperty(value = "单页行数", example = "50")
	@Max(100)
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "consume_date")
	@NotBlank
	private String orderBy;
	@ApiModelProperty(value = "组织id",example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
}
