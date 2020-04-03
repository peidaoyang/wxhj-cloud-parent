/** 
 * @fileName: PlatformEnum.java  
 * @author: pjf
 * @date: 2020年2月13日 下午1:11:00 
 */

package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className PlatformEnum.java
 * @author pjf
 * @date 2020年2月13日 下午1:11:00   
*/
/**
 * @className PlatformEnum.java
 * @author pjf
 * @date 2020年2月13日 下午1:11:00
 */
@Getter
@AllArgsConstructor
public enum PlatformEnum {
	DRIVER_TYPE(1, "设备类型"), RESOURCES_TYPE(2, "资源类型"), PARAMETER_TYPE(3, "参数类型"), RECHARGE_ORGAN_TYPE(4, "充值方类型"),
	RECHARGE_TYPE(5, "充值类型"), DATE_TYPE(6, "日期类型");
	private Integer code;
	private String name;
}
