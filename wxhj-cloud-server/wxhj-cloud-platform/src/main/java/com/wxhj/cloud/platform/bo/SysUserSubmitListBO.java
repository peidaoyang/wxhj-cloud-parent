/**
 * 
 */
package com.wxhj.cloud.platform.bo;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserSubmitRequestListDTO.java
 * @author: cya
 * @Date: 2019年11月8日 上午11:17:43 
 */
@Data
@ApiModel(description="新增/修改用户请求菜单和组织id列表对象")
public class SysUserSubmitListBO {
	@ApiModelProperty(value="组织id",example="guid")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value="角色id",example="guid")
	@NotBlank
	private String roleId;
}
