/** 
 * @fileName: ListEntranceDataExcalRequestDTO.java  
 * @author: pjf
 * @date: 2020年2月6日 下午4:39:23 
 */

package com.wxhj.cloud.feignClient.business.request;



import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className ListEntranceDataExcalRequestDTO.java
 * @author pjf
 * @date 2020年2月6日 下午4:39:23   
*/
/**
 * @className ListEntranceDataExcalRequestDTO.java
 * @author pjf
 * @date 2020年2月6日 下午4:39:23
 */
@Data
public class ListEntranceDataExcelRequestDTO {
	@ApiModelProperty(value = "用户名称", example = "")
	private String nameValue;

	@ApiModelProperty(value = "开始时间", example = "2019-12-22 14:28:34")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginTime;
	@ApiModelProperty(value = "结束时间", example = "2020-12-22 14:28:34")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	@ApiModelProperty(value = "组织编号",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
	
	@ApiModelProperty(value = "导出的语音标准",example="zh_CN" )
	@NotBlank
	private String language;
}
