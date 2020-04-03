/** 
 * @fileName: DeviceRecordStateEnum.java  
 * @author: pjf
 * @date: 2020年2月28日 下午1:27:58 
 */

package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className DeviceRecordStateEnum.java
 * @author pjf
 * @date 2020年2月28日 下午1:27:58   
*/
/**
 * @className DeviceRecordStateEnum.java
 * @author pjf
 * @date 2020年2月28日 下午1:27:58
 */
@Getter
@AllArgsConstructor
public enum DeviceRecordStateEnum {
	//数据未分配
	NOT_DISTRIBUTE(0), 
	//数据已分配
	IS_DISTRIBUTE(1), 
	//数据分配异常
	ERR_DISTRIBUTE(2);
	private Integer state;
	
	public boolean stateEquals(Integer stateTemp)
	{
		return this.state==stateTemp;
	}
}
