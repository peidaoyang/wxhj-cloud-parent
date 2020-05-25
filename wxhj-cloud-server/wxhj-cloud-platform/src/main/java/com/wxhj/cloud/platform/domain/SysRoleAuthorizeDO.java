/** 
 * @fileName: RoleAuthorizeDO.java  
 * @author: pjf
 * @date: 2019年10月9日 下午3:35:28 
 */

package com.wxhj.cloud.platform.domain;



import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @className RoleAuthorizeDO.java
 * @author pjf
 * @date 2019年10月9日 下午3:35:28   
*/
@ToString
@Table(name="sys_role_authorize")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysRoleAuthorizeDO extends AbstractEntity<SysRoleAuthorizeDO> 
	implements ICreationAudited{
	@Id
	private  String  id;
	private  String  moduleId;
	private  String  roleId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creatorTime;
	private  String  creatorUserId;
	/**
	 * @param moduleId
	 * @param roleId
	 */
	public SysRoleAuthorizeDO(String moduleId, String roleId) {
		super();
		this.moduleId = moduleId;
		this.roleId = roleId;
	}
	
	
}
