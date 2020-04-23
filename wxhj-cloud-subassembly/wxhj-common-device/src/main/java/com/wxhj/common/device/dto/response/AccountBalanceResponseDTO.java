package com.wxhj.common.device.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("账户余额查询返回对象")
public class AccountBalanceResponseDTO {
	@ApiModelProperty(value="账户")
	private String accountId;
	@ApiModelProperty(value="账户余额")
	private Double balance;

}
