/**
 * 
 */
package com.wxhj.cloud.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: UserOrganizeListVO.java
 * @author: cya
 * @Date: 2020年3月5日 下午2:18:41 
 */
@Data
@ToString
@ApiModel( description = "用户下组织信息返回对象")
public class UserOrganizeListVO {
	@ApiModelProperty(value="组织id")
	private String id;
	@ApiModelProperty(value="组织名称")
	private String fullName;
	@ApiModelProperty(value="true表示组织下有角色，false无角色")
	private boolean disabled;

	public UserOrganizeListVO(String id, String fullName) {
		super();
		this.id = id;
		this.fullName = fullName;
	}
}
