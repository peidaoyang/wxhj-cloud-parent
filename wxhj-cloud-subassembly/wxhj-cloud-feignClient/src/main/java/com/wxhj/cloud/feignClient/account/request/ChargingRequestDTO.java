/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ChargingRequestDTO.java
 * @author: cya
 * @Date: 2020年3月6日 下午3:41:19 
 */
@Data
@ApiModel(description = "账户扣费请求对象")
public class ChargingRequestDTO {
	@ApiModelProperty(value = "账户id", example = "0000000029")
	@NotBlank(message = "不能为空")
	private String accountId;
	@ApiModelProperty(value="扣费金额",example="-100")
	@Min(-100000)
	@Max(0)
	private Integer amount;
	@ApiModelProperty(value="充值订单号",example="guid")
	private String orderId;
}
