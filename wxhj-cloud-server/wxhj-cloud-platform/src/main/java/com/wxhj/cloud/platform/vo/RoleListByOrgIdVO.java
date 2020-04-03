/**
 * 
 */
package com.wxhj.cloud.platform.vo;

import java.util.List;

import lombok.Data;

/**
 * @ClassName: RoleListByOrgIdVO.java
 * @author: cya
 * @Date: 2020年3月16日 下午2:19:34 
 */
@Data
public class RoleListByOrgIdVO {
	private String id;
	private Integer layers;
	private String encode;
	private String fullName;
	private List<ViewRoleOrganizeVO> roleList;
}
