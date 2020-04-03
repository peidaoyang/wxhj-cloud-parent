package com.wxhj.cloud.feignClient.account.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: ListRechargeInfoRequestDTO.java
 * @author: cya
 * @Date: 2020年2月1日 下午1:22:27
 */
@Data
@ToString
@ApiModel(description = "根据账户id分页查询充值信息 请求对象")
public class ListRechargeInfoRequestDTO extends CommonListPageRequestDTO {
	@ApiModelProperty(value = "充值方类型", example = "0")
	@Max(20)
	@Min(0)
	private Integer type;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "开始时间", example = "2020-01-11")
	private Date startTime;
	@ApiModelProperty(value = "结束时间", example = "2020-06-11")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	@ApiModelProperty(value = "充值类型", example = "50")
	@Max(20)
	@Min(0)
	private Integer payType;

}
