package com.wxhj.cloud.feignClient.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KeyValueVO {
	@ApiModelProperty("主键")
	private String id;
	@ApiModelProperty("名称")
	private String value;
}
