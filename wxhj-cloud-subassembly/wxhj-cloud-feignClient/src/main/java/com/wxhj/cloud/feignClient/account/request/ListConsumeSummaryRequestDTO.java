package com.wxhj.cloud.feignClient.account.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@ApiModel("消费汇总报表")
public class ListConsumeSummaryRequestDTO extends CommonListPageRequestDTO {

	@ApiModelProperty(value = "开始时间", example = "2020-1-22")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate beginTime;
	@ApiModelProperty(value = "结束时间", example = "2020-3-22")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endTime;
}
