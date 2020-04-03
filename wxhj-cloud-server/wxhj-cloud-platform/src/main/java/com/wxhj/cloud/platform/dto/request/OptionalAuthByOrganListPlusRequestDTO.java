package com.wxhj.cloud.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(description = "获取可选权限组信息")
public class OptionalAuthByOrganListPlusRequestDTO {

	@ApiModelProperty(value = "当前登录组织id")
	private String currentOrganizeId;
	@ApiModelProperty(value = "选择人员的子组织id")
	private String childOrganizeId;
}
