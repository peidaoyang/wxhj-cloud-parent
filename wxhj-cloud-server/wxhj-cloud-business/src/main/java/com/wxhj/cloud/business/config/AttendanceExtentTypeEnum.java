/** 
 * @fileName: AttendanceExtentTypeEnum.java  
 * @author: pjf
 * @date: 2019年12月23日 上午10:47:31 
 */

package com.wxhj.cloud.business.config;

/**
 * @className AttendanceExtentTypeEnum.java
 * @author pjf
 * @date 2019年12月23日 上午10:47:31   
*/
/**
 * @className AttendanceExtentTypeEnum.java
 * @author pjf
 * @date 2019年12月23日 上午10:47:31
 */

public enum AttendanceExtentTypeEnum {

	NO_MATCH(0),

	MATCH_PART1(1), MATCH_PART2(2), MATCH_PART3(3), MATCH_PART4(4), MATCH_PART5(5), MATCH_PART6(6);

	private int extentType;

	AttendanceExtentTypeEnum(int extentType) {
		this.extentType = extentType;
	}

	public int getExtentType() {
		return extentType;
	}
}
