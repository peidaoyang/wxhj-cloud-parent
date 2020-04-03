/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;

import java.util.Map;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: UserOrgRoleListRequestDTO.java
 * @author: cya
 * @Date: 2020年3月3日 下午4:39:21 
 */
@Data
@ToString
@ApiModel(description = "用户下所有的的角色和组织")
public class UserOrgRoleListRequestDTO {
	@ApiModelProperty(value="组织id")
	@NotNull
	private Map<String, String> orgMap;
}
