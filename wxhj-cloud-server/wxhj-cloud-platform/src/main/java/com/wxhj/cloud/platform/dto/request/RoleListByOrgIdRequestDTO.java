/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: RoleListByOrgIdRequestDTO.java
 * @author: cya
 * @Date: 2020年3月16日 下午2:12:33 
 */
@Data
@ApiModel(description = "根据组织查询角色对象")
public class RoleListByOrgIdRequestDTO {
	@ApiModelProperty(value="组织id",example="dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotBlank
	private String organizeId;
}
