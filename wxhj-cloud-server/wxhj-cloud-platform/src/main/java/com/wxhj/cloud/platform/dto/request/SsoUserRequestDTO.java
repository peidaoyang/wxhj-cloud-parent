/** 
 * @fileName: SsoUserBO.java  
 * @author: pjf
 * @date: 2019年10月9日 下午1:57:07 
 */

package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SsoUserBO.java
 * @author pjf
 * @date 2019年10月9日 下午1:57:07
 */
@ToString
@Data
@ApiModel(description = "登录请求对象")
public class SsoUserRequestDTO {
	@ApiModelProperty(value = "用户名", example = "admin")
	@NotNull
	private String userName;

	@ApiModelProperty(value = "密码", example = "0000")
	@NotBlank(message = "password can not be empty")
	private String password;
	@ApiModelProperty(value = "该用户对应组织的映射id")
	@NotBlank(message = "mapId can not be empty")
	private String mapId;
	@ApiModelProperty(value = "登陆方式")
	@Min(0)
	@Max(1)
	private int loginType;
	
	@ApiModelProperty(value = "验证码")
	@NotBlank
	private String verificaCode;
	
	@ApiModelProperty(value="验证码接口返回的key",example="123agfatagawr")
	@NotBlank
	private String verificaKey;
}
