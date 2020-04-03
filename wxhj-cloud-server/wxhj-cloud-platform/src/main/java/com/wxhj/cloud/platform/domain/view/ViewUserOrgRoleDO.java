/**
 * 
 */
package com.wxhj.cloud.platform.domain.view;

import javax.persistence.Table;

import lombok.Data;

/**
 * @ClassName: ViewUserOrgRole.java
 * @author: cya
 * @Date: 2020年2月28日 下午5:32:18 
 */
@Data
@Table(name = "view_user_org_role")
public class ViewUserOrgRoleDO {
	private String organizeId;
	private String roleId;
	private String roleName;
	private String userId;
}
