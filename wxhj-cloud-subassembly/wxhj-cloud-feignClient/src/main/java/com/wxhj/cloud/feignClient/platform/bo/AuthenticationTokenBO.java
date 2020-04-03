/** 
 * @fileName: AuthenticationTokenBO.java  
 * @author: pjf
 * @date: 2019年12月10日 下午2:28:51 
 */

package com.wxhj.cloud.feignClient.platform.bo;

import java.util.List;

import lombok.Data;

/**
 * @className AuthenticationTokenBO.java
 * @author pjf
 * @date 2019年12月10日 下午2:28:51
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
	 * 组织主键
	 */
	private String organizeId;
	private String mapId;
	private String currentOrganizeId;
	/**
	 * 子商户
	 */
	private List<String> organizeChildList;

	private Boolean isSystem;

	private String sessionId;
}
