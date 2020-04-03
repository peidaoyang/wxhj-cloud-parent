package com.wxhj.cloud.feignClient.account.vo;


import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiOperation(value="权限组 返回对象")
public class AuthorityGroupVO {
	private String id;
	
	private String organizeId;
	
	private String fullName;
	
	private Integer type;
}
