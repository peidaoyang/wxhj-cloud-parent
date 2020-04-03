package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SubmitRoutInfoRequestDTO.java
 * @author: cya
 * @Date: 2020年2月4日 下午4:04:32 
 */
@Data
@ApiModel(value="新增/修改 线路信息 请求对象")
public class SubmitRoutInfoRequestDTO {
	@ApiModelProperty(value="线路id",example="f8b89131-de13-4dc2-b5bb-b117e12c1111")
	private String id;
	@ApiModelProperty(value="线路编号",example="0001")
	@NotBlank
	private String routeNumber;
	@ApiModelProperty(value = "线路名称",example="测试线路")
	@NotNull
	private String routeName;
	@ApiModelProperty(value = "起始站点",example="起点a")
	@NotNull
	private String startSite;
	@ApiModelProperty(value = "结束站点",example="结束d")
	@NotNull
	private String endSite;
	@ApiModelProperty(value = "途径站点，多个站点用逗号隔开",example="b站点，c站点")
	@NotNull
	private String channelSite;
	@ApiModelProperty(value = "组织id",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
}
