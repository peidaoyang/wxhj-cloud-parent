/**
 * 
 */
package com.wxhj.cloud.platform.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserOrgRoleBO.java
 * @author: cya
 * @Date: 2020年2月28日 下午4:43:06 
 */
@Data
public class SysUserOrgRoleVO {
	@ApiModelProperty(value="组织编号")
	private String organizeId;
	@ApiModelProperty(value="角色编号")
	private String roleId;
	@ApiModelProperty(value="角色名称")
	private String roleName;
}
