package com.wxhj.cloud.feignClient.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("按组织编号获取时间段")
@NoArgsConstructor
@AllArgsConstructor
public class EntranceDayAllVO {
	@ApiModelProperty(value = "时间段编号")
	private String id;
	@ApiModelProperty(value = "时间段名称")
	private String fullName;
	@ApiModelProperty(value = "时间段描述")
	private String timeDescribe;
}
