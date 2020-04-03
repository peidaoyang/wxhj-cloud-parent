package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceGlobalParameterEnum {

	// 门禁参数
	EntranceParameter(1);

	private Integer parameterType;
}
