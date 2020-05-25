package com.wxhj.cloud.feignClient.account.request;



import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel("消费明细报表DTO")
public class ListConsumeDetailRequestDTO extends CommonListPageRequestDTO {
	@ApiModelProperty(value = "开始时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate beginTime;
	@ApiModelProperty(value = "结束时间", example = "2020-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endTime;
	@ApiModelProperty(value = "卡类型", example = "0")
	private Integer cardType;
}
