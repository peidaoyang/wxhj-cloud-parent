/**
 * 
 */
package com.wxhj.cloud.platform.vo;

import java.util.List;

import com.wxhj.cloud.platform.bo.RoleInfoBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserOrgRoleVO.java
 * @author: cya
 * @Date: 2020年3月3日 下午4:45:34 
 */
@Data
@ApiModel(description="用户下所有的的角色和组织")
public class UserOrgRoleVO {
	@ApiModelProperty(value="组织id")
	private String organizeId;
	@ApiModelProperty(value="组织名称")
	private String organizeIdName;
	@ApiModelProperty(value="角色信息列表")
	private List<RoleInfoBO> roleList;
	
	/**
	 * @param orgId
	 * @param orgName
	 */
	public UserOrgRoleVO(String orgId, String orgName) {
		super();
		this.organizeId = orgId;
		this.organizeIdName = orgName;
	}
	
}
