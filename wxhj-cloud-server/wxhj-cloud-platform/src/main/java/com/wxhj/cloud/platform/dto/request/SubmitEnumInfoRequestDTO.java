/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SubmitEnumInfoRequestDTO.java
 * @author: cya
 * @Date: 2020年1月8日 下午2:03:56 
 */
@Data
@ApiModel(value="新增/修改  枚举请求对象")
public class SubmitEnumInfoRequestDTO {
	@ApiModelProperty(value = "枚举id")
	private String id;
	@ApiModelProperty(value = "枚举编号",example="1")
	@Max(50)
	@Min(-10)
	private Integer enumCode;
	@ApiModelProperty(value = "枚举名称",example="test")
	@NotBlank
	private String enumName;
	@ApiModelProperty(value = "枚举类型编号",example="1")
	@Max(20)
	private Integer enumType;
	@ApiModelProperty(value = "枚举类型名称",example="test")
	@NotNull
	private String enumTypeName;
}
