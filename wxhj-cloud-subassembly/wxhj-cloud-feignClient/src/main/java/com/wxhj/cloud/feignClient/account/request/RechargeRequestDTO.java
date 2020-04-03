package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: RechargeRequestDTO.java
 * @author: cya
 * @Date: 2020年1月31日 下午4:07:09 
 */
@Data
@ToString
@ApiModel(description = "账户充值请求对象")
public class RechargeRequestDTO {
	@ApiModelProperty(value = "账户id", example = "guid")
	@NotBlank(message = "不能为空")
	private String accountId;
	@ApiModelProperty(value="充值金额",example="1")
	@Min(0)
	@Max(100000)
	private Integer amount;
	@ApiModelProperty(value = "操作账户id", example = "guid")
	@NotBlank(message = "不能为空")
	private String userId;
	@ApiModelProperty(value = "当前组织id", example = "guid")
	@NotBlank(message = "不能为空")
	private String currentOrganizeId;
}
