/** 
 * @fileName: AccountLoginEnum.java  
 * @author: pjf
 * @date: 2020年3月6日 上午10:50:05 
 */

package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className AccountLoginEnum.java
 * @author pjf
 * @date 2020年3月6日 上午10:50:05   
*/
@Getter
@AllArgsConstructor
public enum AccountLoginTypeEnum {
	ACCOUNT_LOGIN(0),
	PHONE_LOGIN(1);
	private Integer  loginType;
}
