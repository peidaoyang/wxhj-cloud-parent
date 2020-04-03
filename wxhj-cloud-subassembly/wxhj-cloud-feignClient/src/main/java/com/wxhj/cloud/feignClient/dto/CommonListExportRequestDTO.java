package com.wxhj.cloud.feignClient.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonListExportRequestDTO {
	@ApiModelProperty(value = "条件参数", example = "")
	protected String nameValue;
	@ApiModelProperty(value = "组织编号", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	protected String organizeId;
	@ApiModelProperty(value = "导出的语音标准", example = "zh_CN")
	@NotBlank
	protected String language;
}
