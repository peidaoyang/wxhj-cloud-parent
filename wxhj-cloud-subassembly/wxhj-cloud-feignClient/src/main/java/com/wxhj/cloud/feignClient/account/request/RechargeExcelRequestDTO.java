/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListExportRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @ClassName: RechargeExcelRequestDTO.java
 * @author: cya
 * @Date: 2020年2月1日 下午1:32:15 
 */
@Data
@ToString
@ApiModel(description = "充值明细报表导出请求对象")
public class RechargeExcelRequestDTO extends CommonListExportRequestDTO{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "开始时间", example = "2020-01-11")
	private LocalDate startTime;
	@ApiModelProperty(value = "结束时间", example = "2020-06-11")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endTime;
	@ApiModelProperty(value = "充值方类型", example = "50")
	@Max(20)
	@Min(0)
	private Integer type;
	@ApiModelProperty(value = "充值类型", example = "0")
	@Max(20)
	@Min(0)
	private Integer payType;
}
