/** 
 * @fileName: OrganizeDO.java  
 * @author: pjf
 * @Date: 2019年10月9日 下午3:32:22 
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
import com.wxhj.cloud.core.interfaces.ITreeElement;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.IModificationAudited;

import lombok.Data;

/**
 * @className OrganizeDO.java
 * @author pjf
 * @Date 2019年10月9日 下午3:32:22
 */

@Data
@Table(name = "sys_organize")
public class SysOrganizeDO extends AbstractEntity<SysOrganizeDO>
		implements ICreationAudited, IModificationAudited, IDeleteAudited, ITreeElement, IModelInitialization {
	@Id
	private String id;
	private String parentId;
	private Integer layers;
	private String encode;
	private String fullName;
	private String shortName;
	private String categoryId;
	private String managerId;
	private String telephone;
	private String mobilePhone;
	private String wechat;
	private String fax;
	private String email;
	private String areaid;
	private String address;
	private Integer isAllowEdit;
	private Integer isAllowDelete;
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
	private Long no;
	private String noList;
	private Integer type;

	@Override
	public void initialization() {
		isAllowEdit = 0;
		isAllowDelete = 0;
		sortCode = 0;
		isDeleteMark = 0;
		isEnabledMark = 0;
		encode = "0";
	}

	public void setType(Integer type) {
		this.type = type == null ? 0:type;
	}
}
