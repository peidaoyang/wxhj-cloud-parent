package com.wxhj.cloud.feignClient.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@ApiModel(value = "通用仅包含id的请求")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonIdRequestDTO {
	@ApiModelProperty(value = "id")
	@NotBlank
	private String id;
}
