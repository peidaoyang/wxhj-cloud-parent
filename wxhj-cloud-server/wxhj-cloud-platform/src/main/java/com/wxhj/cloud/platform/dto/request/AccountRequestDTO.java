/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: AccountRequestDTO.java
 * @author: cya
 * @Date: 2019年11月7日 下午2:40:05 
 */
@Data
@ApiModel(description = "登录方式请求对象")
public class AccountRequestDTO {
	@ApiModelProperty(value = "用户名", example = "admin")
	@NotNull
	private String userName;

	@ApiModelProperty(value = "登陆方式",example = "0")
	@Min(0)
	@Max(1)
	private int loginType;
}
