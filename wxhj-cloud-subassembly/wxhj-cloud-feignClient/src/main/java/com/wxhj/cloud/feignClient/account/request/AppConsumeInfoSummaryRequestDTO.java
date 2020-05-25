package com.wxhj.cloud.feignClient.account.request;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("个人消费汇总查询 请求对象")
public class AppConsumeInfoSummaryRequestDTO extends CommonListPageRequestDTO {
	@ApiModelProperty(value = "账户id",example = "0000000052")
	@NotBlank
	private String accountId;
	@ApiModelProperty(value = "开始时间", example = "2019-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;
	@ApiModelProperty(value = "结束时间", example = "2020-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
}
