/**
 * @className AuthorityType.java
 * @admin jwl
 * @date 2019年12月23日 下午10:08:12
 */
package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className AuthorityType.java
 * @admin jwl
 * @date 2019年12月23日 下午10:08:12
 */
@Getter
@AllArgsConstructor
public enum AuthorityType {
	// 权限组类型
	ACCOUNT(0, "默认入口"),
	ATTENDANCE(1, "考勤入口"),
	ENTRANCE(2,"门禁入口"),
	CONSUME(3,"消费入口"),
	SHUTTLEBUS(4,"班车入口"),
	STUDENT_ATTENDANCE(5,"学生考勤入口"),
	SCHOOL(6,"楼栋入口")
	;
	private Integer code;
	private String msg;
}
