/**
 * 
 */
package com.wxhj.cloud.platform.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: MapOrgRoleResponseDTO.java
 * @author: cya
 * @Date: 2019年11月10日 下午2:31:11 
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="用户已选中角色信息请求对象")
public class MapOrgRoleResponseDTO {
	@ApiModelProperty(value="用户id",example="guid")
	private String id;
	@ApiModelProperty(value="用户名称",example="用户1")
	private String fullName;
}
