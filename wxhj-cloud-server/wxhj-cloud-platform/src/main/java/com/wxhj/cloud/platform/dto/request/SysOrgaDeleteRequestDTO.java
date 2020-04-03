package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysOrgaDeleteRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 上午8:45:10
 */
@ToString
@Data
@ApiModel(description = "根据组织id删除组织请求对象")
public class SysOrgaDeleteRequestDTO {
	@ApiModelProperty(value = "组织id", example = "2fca977e-3412-4fd1-b618-02b92c4d843o")
	String id;
	@ApiModelProperty(value = "登录用户id")
	@NotNull
	private String userId;
}
