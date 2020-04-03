package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "忘记密码请求对象")
public class ForgetPasswordRequestDTO {
	@ApiModelProperty(value = "密码", example = "test")
	@NotBlank
	private String newPassword;
	
	@ApiModelProperty(value = "手机号", example = "13922222222")
	@Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "mobilePhone error")
	private String mobilePhone;
	
	@ApiModelProperty(value = "验证码", example = "1122")
	@NotBlank
	private String code;
}
