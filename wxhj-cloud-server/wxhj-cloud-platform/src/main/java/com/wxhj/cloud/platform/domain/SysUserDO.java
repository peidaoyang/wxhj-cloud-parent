/** 
 * @fileName: UserDO.java  
 * @author: pjf
 * @date: 2019年10月9日 下午3:00:18 
 */

package com.wxhj.cloud.platform.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;
import com.wxhj.cloud.driud.infrastructure.IDeleteAudited;
import com.wxhj.cloud.core.interfaces.IModelInitialization;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.IModificationAudited;

import lombok.Data;
import lombok.ToString;

/**
 * @className UserDO.java
 * @author pjf
 * @date 2019年10月9日 下午3:00:18
 */

@Data
@Table(name = "sys_user")
@ToString
public class SysUserDO extends AbstractEntity<SysUserDO>
		implements ICreationAudited, IModificationAudited, IDeleteAudited, IModelInitialization {
	@Id
	private String id;
	private String account;
	private String realName;
	private String headIcon;
	private Date birthDay;
	private String mobilePhone;
	private String email;
	private String wechat;
	private Integer isAdmin;
	private Integer sortCode;
	private Integer isDeleteMark;
	private Integer isEnabledMark;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	private String creatorUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	private String lastModifyUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String deleteUserId;

	private String organizeId;
	private String userPassword;
	private String userSecretKey;


	@Override
	public void initialization() {
		isAdmin = 0;
		isDeleteMark = 0;
		isEnabledMark = 1;
	}

}
