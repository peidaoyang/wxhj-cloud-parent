package com.wxhj.cloud.feignClient.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RouteByOrgVO {
	@ApiModelProperty(value="线路名称")
	private String routeName;
	@ApiModelProperty(value="线路编号")
	private String routeNumber;
}
