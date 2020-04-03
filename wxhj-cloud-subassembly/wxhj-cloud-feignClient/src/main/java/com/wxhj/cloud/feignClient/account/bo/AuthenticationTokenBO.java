/**
 * @className AuthenticationTokenBO.java
 * @admin jwl
 * @date 2020年1月15日 上午11:47:09
 */
package com.wxhj.cloud.feignClient.account.bo;

import lombok.Data;

/**
 * @className AuthenticationTokenBO.java
 * @admin jwl
 * @date 2020年1月15日 上午11:47:09
 */
@Data
public class AuthenticationTokenBO {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户名主键
	 */
	private String userId;

	/**
	 * 登录类型
	 */
	private Integer loginType;
	/**
	 * 组织主键
	 */

	private String organizeId;
	
	private String sessionId;
}
