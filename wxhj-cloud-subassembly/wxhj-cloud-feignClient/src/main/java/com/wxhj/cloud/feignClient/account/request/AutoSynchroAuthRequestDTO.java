package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiOperation(value="自动同步权限组信息查询 请求对象")
public class AutoSynchroAuthRequestDTO {
	@ApiModelProperty(value="组织id")
	@NotBlank
	private String organizeId;
}
