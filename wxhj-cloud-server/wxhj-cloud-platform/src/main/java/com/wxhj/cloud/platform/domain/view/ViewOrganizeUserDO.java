/** 
 * @fileName: viewOrganizeUserDO.java  
 * @author: pjf
 * @date: 2019年11月12日 上午11:58:02 
 */

package com.wxhj.cloud.platform.domain.view;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @className viewOrganizeUserDO.java
 * @author pjf
 * @date 2019年11月12日 上午11:58:02
 */

@Data
@ToString
@Table(name = "view_organize_user")
public class ViewOrganizeUserDO {
	@Id
	private String id;
	private String organizeId;
	private String userId;
	private String roleId;
	private String organizeName;
	private String roleName;
	
}
