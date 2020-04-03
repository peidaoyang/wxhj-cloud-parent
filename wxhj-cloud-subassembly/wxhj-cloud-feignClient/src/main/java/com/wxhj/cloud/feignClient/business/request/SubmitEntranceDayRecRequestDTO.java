/**
 * @className SubmitEntranceDayRecRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午1:31:48
 */
package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SubmitEntranceDayRecRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午1:31:48
 */
@Data
@ApiModel("编辑时间段详细请求对象")
public class SubmitEntranceDayRecRequestDTO {
	@ApiModelProperty(value = "顺序")
	@Min(0)
	private Integer sequence;
	@ApiModelProperty(value = "开始时间")
	@Min(1)
	private Integer beginTime;
	@ApiModelProperty(value = "结束时间")
	@Max(1359)
	private Integer endTime;
}
