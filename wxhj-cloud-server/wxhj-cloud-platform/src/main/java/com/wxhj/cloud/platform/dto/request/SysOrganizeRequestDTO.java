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
 * @ClassName: SysOrganizeRequestDTO.java
 * @author: cya
 * @Date: 2019年12月4日 下午1:11:46
 */
@ToString
@Data
@ApiModel(description = "获取主要组织列表(层级<=1)对象")
public class SysOrganizeRequestDTO {
	@ApiModelProperty(value = "当前登录账户的商户id")
	@NotNull
	private String currentOrganizeId;
}
