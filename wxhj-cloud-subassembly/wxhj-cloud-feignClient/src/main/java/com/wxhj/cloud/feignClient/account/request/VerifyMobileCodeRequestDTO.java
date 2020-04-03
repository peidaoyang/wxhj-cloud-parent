package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "验证验证码请求对象")
public class VerifyMobileCodeRequestDTO {
	@ApiModelProperty(value = "手机号", example = "13922222222")
	@Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "mobilePhone error")
	private String mobilePhone;
	@ApiModelProperty(value = "验证码", example = "1234")
	@NotBlank
	private String code;
}
