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
import lombok.ToString;

/**
 * @ClassName: rechargeExcelByAccountIdRequestDTO.java
 * @author: cya
 * @Date: 2020年2月1日 下午1:33:58 
 */
@Data
@ToString
@ApiModel(description = "账户充值明细报表导出请求对象")
public class rechargeExcelByAccountIdRequestDTO {
	@ApiModelProperty(value = "条件参数", example = "")
	private String nameValue;
	@ApiModelProperty(value = "充值方类型", example = "50")
	@Max(20)
	@Min(1)
	private Integer type;
	@ApiModelProperty(value = "充值类型", example = "50")
	@Max(20)
	@Min(1)
	private Integer payType;
	@ApiModelProperty(value = "账户id", example = "50")
	@NotBlank
	private String accountId;
}
