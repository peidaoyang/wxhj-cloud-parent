/**
 * @className SubmitAttendanceDayRecRequestDTO.java
 * @admin jwl
 * @date 2019年12月18日 下午3:06:37
 */
package com.wxhj.cloud.feignClient.business.request;


import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SubmitAttendanceDayRecRequestDTO.java
 * @admin jwl
 * @date 2019年12月18日 下午3:06:37
 */
@Data
@ToString
@ApiModel(value="班次详细信息请求对象")
public class SubmitAttendanceDayRecRequestDTO {
	@ApiModelProperty(value="班次顺序",example="1")
	@Min(1)
	private Integer sequence;
	@ApiModelProperty(value="上班时间",example="0")
	@Min(0)
	private Integer upTime;
	@ApiModelProperty(value="下班时间",example="0")
	@Min(0)
	private Integer downTime;
	@ApiModelProperty(value="上班提前时间(分钟)",example="1")
	@Min(0)
	private Integer upExtent;
	@ApiModelProperty(value="下班提前时间(分钟)",example="1")
	@Min(0)
	private Integer downExtent;
}
