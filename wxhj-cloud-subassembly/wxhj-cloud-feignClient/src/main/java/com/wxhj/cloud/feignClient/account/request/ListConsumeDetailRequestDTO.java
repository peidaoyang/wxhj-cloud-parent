package com.wxhj.cloud.feignClient.account.request;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
//implements IPageRequestModel
public class ListConsumeDetailRequestDTO extends CommonListPageRequestDTO {
	@ApiModelProperty(value = "开始时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate beginTime;
	@ApiModelProperty(value = "结束时间", example = "2020-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endTime;
}
