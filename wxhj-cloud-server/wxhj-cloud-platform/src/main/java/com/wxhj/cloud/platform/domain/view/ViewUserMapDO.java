/** 
 * @fileName: ViewUserMapDO.java  
 * @author: pjf
 * @date: 2019年10月11日 上午9:41:01 
 */

package com.wxhj.cloud.platform.domain.view;



import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className ViewUserMapDO.java
 * @author pjf
 * @date 2019年10月11日 上午9:41:01
 */
@Data
@Table(name = "view_user_map")
public class ViewUserMapDO {

	private String userId;
	private String account;
	private String realName;
	private String headIcon;
	private LocalDateTime birthDay;
	private String mobilePhone;
	private String email;
	private String wechat;
	private Integer isAdmin;
	private Integer sortCode;
	private Integer isDeleteMark;
	private Integer isEnabledMark;
	private String description;
	private LocalDateTime creatorTime;
	private String creatorUserId;
	private LocalDateTime lastModifyTime;
	private String lastModifyUserId;
	private LocalDateTime deleteTime;
	private String deleteUserId;

	private String organizeId;
	private String userPassword;
	private String userSecretKey;
	@Id
	private String mapId;

	private String currentOrganizeId;
	
	private String roleId;

	private String fullName;
}
