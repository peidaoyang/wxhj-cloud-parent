/** 
 * @fileName: DeviceAuthorizeTypeEnum.java  
 * @author: pjf
 * @date: 2020年3月2日 下午2:06:07 
 */

package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className DeviceAuthorizeTypeEnum.java
 * @author pjf
 * @date 2020年3月2日 下午2:06:07   
*/
/**
 * @className DeviceAuthorizeTypeEnum.java
 * @author pjf
 * @date 2020年3月2日 下午2:06:07
 */
@Getter
@AllArgsConstructor
public enum DeviceAuthorizeTypeEnum {
	// 百度人脸认证
	BAIDU_FACE(0);
	private Integer authorizeType;
}
