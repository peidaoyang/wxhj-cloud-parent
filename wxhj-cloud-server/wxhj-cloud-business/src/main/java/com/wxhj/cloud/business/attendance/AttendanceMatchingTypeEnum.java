package com.wxhj.cloud.business.attendance;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttendanceMatchingTypeEnum {

	MATCHING_TYPE1(0), MATCHING_TYPE2(1), MATCHING_TYPE3(2);

	private Integer matchingType;

}
