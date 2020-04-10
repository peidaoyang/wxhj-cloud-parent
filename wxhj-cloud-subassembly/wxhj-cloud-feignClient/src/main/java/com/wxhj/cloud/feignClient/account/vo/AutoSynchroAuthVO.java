package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiOperation(value="自动同步权限组信息返回对象")
public class AutoSynchroAuthVO {
	private String id;
	
	private String organizeId;
	
	private String fullName;
	
	private Integer type;

	private Integer autoSynchro;
}
