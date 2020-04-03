/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: SysWebUserRequestDTO.java
 * @author: cya
 * @Date: 2019年12月6日 下午5:31:51
 */
@ToString
@Data
@ApiModel(description = "获取当前账户的权限列表")
public class SysWebUserRequestDTO {
	@ApiModelProperty(value = "登录的sessionId")
	@NotNull
	private String sessionId;
}
