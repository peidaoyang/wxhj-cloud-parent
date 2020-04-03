/**
 * @className AccountResetPasswordRequestDTO.java
 * @admin jwl
 * @date 2020年1月15日 上午11:33:30
 */
package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className AccountResetPasswordRequestDTO.java
 * @admin jwl
 * @date 2020年1月15日 上午11:33:30
 */
@ApiModel(value = "用户重置密码请求对象")
@Data
public class AccountResetPasswordRequestDTO {
	@ApiModelProperty(value = "原密码",example = "0000")
	@NotBlank
	private String oldPassword;
	@ApiModelProperty(value = "密码", example = "test")
	@NotBlank
	private String newPassword;
	@ApiModelProperty(value = "账户id", example = "0000000028")
	@Size(min=10,max=10)
	private String accountId;
}
