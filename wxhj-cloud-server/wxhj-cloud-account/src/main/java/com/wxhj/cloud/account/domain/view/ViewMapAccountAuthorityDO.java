/** 
 * @fileName: ViewMapAccountAuthorityDO.java  
 * @author: pjf
 * @date: 2020年2月7日 下午4:42:41 
 */

package com.wxhj.cloud.account.domain.view;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @className ViewMapAccountAuthorityDO.java
 * @author pjf
 * @date 2020年2月7日 下午4:42:41   
*/

@Data
@Table(name = "view_map_account_authority")
public class ViewMapAccountAuthorityDO {
	@Id
	private String id;
	private String authorityGroupId;
	private String accountId;
	private String name;
}
