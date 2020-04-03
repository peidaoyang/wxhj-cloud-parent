/** 
 * @fileName: UserRoleBO.java  
 * @author: pjf
 * @date: 2019年12月10日 下午1:40:08 
 */

package com.wxhj.cloud.sso.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className UserRoleBO.java
 * @author pjf
 * @date 2019年12月10日 下午1:40:08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleBO {
	private String moduleId;
	private String url;
}
