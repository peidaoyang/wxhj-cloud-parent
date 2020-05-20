/**
 * @className ListDetailEntranceDataRequestDTO.java
 * @admin jwl
 * @date 2020年1月19日 下午3:58:36
 */
package com.wxhj.cloud.feignClient.business.request;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @className ListDetailEntranceDataRequestDTO.java
 * @admin jwl
 * @date 2020年1月19日 下午3:58:36
 */
@Data
@ApiModel(value = "门禁明细报表请求对象")
public class ListDetailEntranceDataRequestDTO extends CommonListPageRequestDTO {
	@ApiModelProperty(value = "开始时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate beginTime;
	@ApiModelProperty(value = "结束时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endTime;
}
