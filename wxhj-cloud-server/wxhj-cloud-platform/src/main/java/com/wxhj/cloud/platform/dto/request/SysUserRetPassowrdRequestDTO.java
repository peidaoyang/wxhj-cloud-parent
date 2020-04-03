package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.NotBlank;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysUserRetPassowrdRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 上午8:47:15
 */
@ToString
@Data
@ApiModel(description = "重置用户密码请求对象")
public class SysUserRetPassowrdRequestDTO   {
	@ApiModelProperty(value = "用户主键", example = "guid")
	@NotBlank
	String id;
	@ApiModelProperty(value = "密码", example = "test")
	@NotBlank
	String password;
	@NotNull
	@ApiModelProperty(value = "当前登录用户的id")
	private String userId;
}
