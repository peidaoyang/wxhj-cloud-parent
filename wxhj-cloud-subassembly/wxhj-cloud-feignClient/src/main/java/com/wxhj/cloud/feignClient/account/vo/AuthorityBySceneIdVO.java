package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import lombok.Data;

@Data
@ApiOperation(value="权限组场景信息返回对象")
public class AuthorityBySceneIdVO {
	@ApiModelProperty(value="场景id")
	private String sceneId;
	@ApiModelProperty(value="权限组id")
	private String authorityId;
	@ApiModelProperty(value="权限组名称")
	private String fullName;
}
