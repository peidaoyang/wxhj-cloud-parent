/** 
 * @fileName: SsoLoginBO.java  
 * @author: pjf
 * @date: 2019年12月10日 下午1:07:35 
 */

package com.wxhj.cloud.sso.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @className SsoLoginBO.java
 * @author pjf
 * @date 2019年12月10日 下午1:07:35   
*/
/**
 * @className SsoLoginBO.java
 * @author pjf
 * @date 2019年12月10日 下午1:07:35
 */
@Data
@ToString
public class SsoLoginBO {
	private String userName;
	private String password;
	private String mapId;
	private Integer loginType;
}
