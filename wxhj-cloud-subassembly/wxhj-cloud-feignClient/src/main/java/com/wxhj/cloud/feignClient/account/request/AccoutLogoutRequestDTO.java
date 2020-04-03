package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("账户登出请求对象")
@Data
public class AccoutLogoutRequestDTO {
	@ApiModelProperty(value = "登录session",example = "123")
	@NotBlank
	private String sessionId;
}
