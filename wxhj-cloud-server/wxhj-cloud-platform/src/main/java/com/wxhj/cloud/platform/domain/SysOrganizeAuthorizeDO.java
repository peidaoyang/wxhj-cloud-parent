/** 
 * @fileName: RoleAuthorizeDO.java  
 * @author: pjf
 * @date: 2019年10月9日 下午3:35:28 
 */

package com.wxhj.cloud.platform.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className RoleAuthorizeDO.java
 * @author pjf
 * @date 2019年10月9日 下午3:35:28
 */
@Table(name = "sys_organize_authorize")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysOrganizeAuthorizeDO extends AbstractEntity<SysOrganizeAuthorizeDO> implements ICreationAudited {
	@Id
	private String id;
	private String moduleId;
	private String organizeId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	private String creatorUserId;
	
	/**
	 * @param moduleId
	 * @param organizeId
	 */
	public SysOrganizeAuthorizeDO(String moduleId, String organizeId) {
		super();
		this.moduleId = moduleId;
		this.organizeId = organizeId;
	}
}
