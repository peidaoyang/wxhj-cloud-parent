/**
 * @className ListDetailEntranceDataRequestDTO.java
 * @admin jwl
 * @date 2020年1月19日 下午3:58:36
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className ListDetailEntranceDataRequestDTO.java
 * @admin jwl
 * @date 2020年1月19日 下午3:58:36
 */
@Data
@ApiModel(value = "门禁明细报表请求对象")
public class ListDetailEntranceDataRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "用户名称", example = "")
	private String nameValue;
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "order_number")
	@NotNull
	private String orderBy;
	@ApiModelProperty(value = "开始时间", example = "2019-12-22 14:28:34")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	@ApiModelProperty(value = "结束时间", example = "2019-12-22 14:28:34")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	@ApiModelProperty(value = "组织编号",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
}
