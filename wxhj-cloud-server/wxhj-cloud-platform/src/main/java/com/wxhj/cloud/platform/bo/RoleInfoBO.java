/**
 * 
 */
package com.wxhj.cloud.platform.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: RoleInfoBO.java
 * @author: cya
 * @Date: 2020年3月3日 下午4:49:05 
 */
@Data
@ApiModel(description="用户下所有的的角色信息")
@AllArgsConstructor
public class RoleInfoBO {
	@ApiModelProperty(value="角色id")
	private String roleId;
	@ApiModelProperty(value="角色名字")
	private String roleName;
}
