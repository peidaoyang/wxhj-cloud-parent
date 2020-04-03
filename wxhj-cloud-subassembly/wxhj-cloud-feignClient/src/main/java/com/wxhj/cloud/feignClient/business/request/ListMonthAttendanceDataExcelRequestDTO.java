/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ListMonthAttendanceDataExcelRequestDTO.java
 * @author: cya
 * @Date: 2020年2月24日 下午4:15:03 
 */
@Data
@ApiModel(value = "汇总报表信息请求对象")
public class ListMonthAttendanceDataExcelRequestDTO {
	@ApiModelProperty(value = "用户名称", example = "")
	private String nameValue;
	@ApiModelProperty(value = "开始时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginTime;
	@ApiModelProperty(value = "结束时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	@ApiModelProperty(value = "组织编号",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value = "导出的语音标准",example="zh_CN" )
	@NotBlank
	private String language;
}
