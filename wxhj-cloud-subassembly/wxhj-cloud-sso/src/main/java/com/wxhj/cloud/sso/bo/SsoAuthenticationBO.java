/** 
 * @fileName: SsoAuthenticationModel.java  
 * @author: pjf
 * @date: 2019年12月10日 下午1:36:10 
 */

package com.wxhj.cloud.sso.bo;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.google.common.base.Strings;
import com.wxhj.cloud.sso.IAuthenticationModel;

import lombok.Data;

/**
 * @className SsoAuthenticationModel.java
 * @author pjf
 * @date 2019年12月10日 下午1:36:10
 */
@Data
public class SsoAuthenticationBO implements IAuthenticationModel {
	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 根据用户名密码生产的tocken
	 */
	private String userTocken;

	/**
	 * 用户名主键
	 */

	private String userId;
	/**
	 * 组织主键
	 */

	private String organizeId;

	private String mapId;

	private String currentOrganizeId;
	/**
	 * 子商户
	 */
	private List<String> organizeChildList;

	/**
	 * 权限列表
	 */
	private List<UserRoleBO> userRoleList;

	private Integer expireMinite;

	private Long expireFreshTime;

	private Boolean isSystem;

	/**
	 * 登录时间
	 */
	private LocalDateTime loginTime;

	/*
	 * 登陆方式
	 */
	private Integer loginType;

	/**
	 * 组织类型
	 */
	private Integer orgType;
	
	
	@Override
	public void generateTocken() {
		userTocken = UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Override
	public String getSessionId() {
		if (Strings.isNullOrEmpty(userName)) {
			return "";
		}
		if (Strings.isNullOrEmpty(userTocken)) {
			generateTocken();
		}
		return userName + "_" + userTocken;
	}

}
