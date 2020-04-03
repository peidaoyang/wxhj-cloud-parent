package com.wxhj.cloud.feignClient.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@ApiModel(value = "通用紧包含一个组织id的请求")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonOrganizeIdListRequestDTO {
	@ApiModelProperty(value = "组织id列表")
	private List<String> organizeIdList;
}
