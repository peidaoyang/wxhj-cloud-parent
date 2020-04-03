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
 * @ClassName: SysRolebyOrganzieResponseDTO.java
 * @author: cya
 * @Date: 2019年11月8日 下午4:34:04 
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel( description = "组织下用户信息返回对象")
public class SysRolebyOrganzieResponseDTO {
	@ApiModelProperty(value="用户主键")
	private String id;
	@ApiModelProperty(value="用户名称")
	private String fullName;
}
