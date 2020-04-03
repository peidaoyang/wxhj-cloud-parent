package com.wxhj.cloud.feignClient.account.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthorityGroupInfoBO {
	@ApiModelProperty(value="权限组id")
	private String id;
	@ApiModelProperty(value="组织id")
	private String organizeId;
	@ApiModelProperty(value="权限组名称")
	private String fullName;
	@ApiModelProperty(value="权限组入口类型，0：默认值，1：考勤权限组，2：门禁入口，3：消费入口，4：班车入口")
	private Integer type;
}
