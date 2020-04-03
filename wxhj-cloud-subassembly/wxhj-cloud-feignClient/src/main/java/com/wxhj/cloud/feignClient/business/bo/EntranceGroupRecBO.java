/**
 * @className SubmitEntranceGroupRecRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午5:02:06
 */
package com.wxhj.cloud.feignClient.business.bo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SubmitEntranceGroupRecRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午5:02:06
 */
@Data
@ApiModel(value = "编辑通行规则详细设计请求对象")
public class EntranceGroupRecBO {
	@ApiModelProperty(value = "通行组编号")
	private String entranceGroupId;
	@ApiModelProperty(value = "顺序")
	@Min(0)
	private Integer serialNumber;
	@ApiModelProperty(value = "通行时间段编号")
	@NotBlank(message = "不能为空")
	private String entranceDayId;
}
