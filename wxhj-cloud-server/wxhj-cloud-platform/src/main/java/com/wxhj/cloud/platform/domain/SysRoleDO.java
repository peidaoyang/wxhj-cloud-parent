/** 
 * @fileName: RoleDO.java  
 * @author: pjf
 * @Date: 2019年10月9日 下午3:33:32 
 */

package com.wxhj.cloud.platform.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;
import com.wxhj.cloud.driud.infrastructure.IDeleteAudited;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.IModificationAudited;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className RoleDO.java
 * @author pjf
 * @Date 2019年10月9日 下午3:33:32
 */
@Table(name = "sys_role")
@NoArgsConstructor
@Data
public class SysRoleDO extends AbstractEntity<SysRoleDO> 
	implements ICreationAudited, IModificationAudited, IDeleteAudited {
	@Id
	private String id;
	private String organizeId;
	private Integer category=1;
	private String encode;
	private String fullName;
	private String type;
	private Integer isAllowEdit=0;
	private Integer isAllowDelete=0;
	private Integer sortCode;
	private Integer isDeleteMark;
	private Integer isEnabledMark=0;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	private String creatorUserId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	private String lastModifyUserId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String deleteUserId;
	
}
