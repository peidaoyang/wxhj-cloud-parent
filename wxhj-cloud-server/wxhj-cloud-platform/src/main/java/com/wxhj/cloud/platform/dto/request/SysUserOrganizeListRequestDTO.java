/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysUserOrganizeListDTO.java
 * @author: cya
 * @Date: 2019年11月10日 下午12:02:25 
 */
@Data
@ApiModel( description = "用户下组织信息请求对象")
public class SysUserOrganizeListRequestDTO {
	@ApiModelProperty(value = "查询名字",example = "组织1")
	private String fullName;
	@NotNull
	@ApiModelProperty(value = "组织主键",example = "guid")
	private String id;
}
