/** 
 * @fileName: AppAuthenticationBO.java  
 * @author: pjf
 * @date: 2019年12月10日 下午4:31:14 
 */

package com.wxhj.cloud.sso.bo;

import java.util.UUID;

import com.google.common.base.Strings;
import com.wxhj.cloud.sso.IAuthenticationModel;

import lombok.Data;

/**
 * @className AppAuthenticationBO.java
 * @author pjf
 * @date 2019年12月10日 下午4:31:14   
*/

@Data
public class AppAuthenticationBO implements IAuthenticationModel {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户名主键
	 */
	private String userId;

	private String userTocken;
	/**
	 * 登录类型
	 */
	private Integer loginType;
	/**
	 * 组织主键
	 */
	private String organizeId;

	private Integer expireMinite;

	private Long expireFreshTime;

	private Double accountBalance;

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
