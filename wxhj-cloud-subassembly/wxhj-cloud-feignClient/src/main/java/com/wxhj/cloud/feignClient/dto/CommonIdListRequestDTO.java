package com.wxhj.cloud.feignClient.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@ApiModel(value = "通用仅包含idList的请求")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonIdListRequestDTO {
	@ApiModelProperty(value = "id")
	@NotNull
	private List<String> idList;
}
