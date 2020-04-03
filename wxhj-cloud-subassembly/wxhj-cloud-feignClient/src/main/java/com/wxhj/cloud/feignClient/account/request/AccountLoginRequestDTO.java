/**
 * @className AccountLoginRequestDTO.java
 * @admin jwl
 * @date 2019年12月9日 下午3:57:23
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
 * @className AccountLoginRequestDTO.java
 * @admin jwl
 * @date 2019年12月9日 下午3:57:23
 */
@ToString
@Data
@ApiModel(description = "用户登录请求对象")
public class AccountLoginRequestDTO {
	@ApiModelProperty(value = "账号", example = "0000000028")
	@NotBlank
	private String userName;
	@ApiModelProperty(value = "密码", example = "0000")
	@NotBlank(message = "password can not be empty")
	private String password;
	@ApiModelProperty(value = "mapId",example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	private String mapId;
	@ApiModelProperty(value = "登陆方式",example = "0")
	@Min(0)
	@Max(1)
	private int loginType;
}
