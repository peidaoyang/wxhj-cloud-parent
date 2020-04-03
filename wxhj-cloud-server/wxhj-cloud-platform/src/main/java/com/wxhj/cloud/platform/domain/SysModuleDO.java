/** 
 * @fileName: ModuleDO.java  
 * @author: pjf
 * @Date: 2019年10月9日 下午3:29:53 
 */

package com.wxhj.cloud.platform.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.interfaces.IModelInitialization;
import com.wxhj.cloud.core.interfaces.ITreeElement;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;
import com.wxhj.cloud.driud.infrastructure.IDeleteAudited;
import com.wxhj.cloud.driud.infrastructure.IModificationAudited;

import lombok.Data;

/**
 * @className ModuleDO.java
 * @author pjf
 * @Date 2019年10月9日 下午3:29:53
 */
@Data
@Table(name = "sys_module")
public class SysModuleDO extends AbstractEntity<SysModuleDO>
		implements ICreationAudited, IModificationAudited, IDeleteAudited, ITreeElement, IModelInitialization {
	@Id
	private String id;

	private String parentId;

	private Integer layers;
	private String encode;

	private String fullName;
	private String icon;

	private String urlAddress;
	private String target;

	private Integer isMenu;

	private Integer sortCode;
	private Integer isDeleteMark;
	private Integer isEnabledMark;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	private String creatorUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	private String lastModifyUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String deleteUserId;
	private Integer moduleType;

	@Override
	public void initialization() {
		isDeleteMark = 0;
		isEnabledMark = 1;
		target = "iframe";
	}
}
