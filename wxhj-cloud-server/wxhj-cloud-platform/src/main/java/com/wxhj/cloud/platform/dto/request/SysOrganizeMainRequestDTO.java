/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: SysOrganizeMainRequestDTO.java
 * @author: cya
 * @Date: 2019年12月4日 下午1:31:20
 */
@ToString
@Data
@ApiModel(description = "获取组织树级显示列表对象")
public class SysOrganizeMainRequestDTO {
	@ApiModelProperty(value = "当前登录用户是否为系统用户")
	@NotNull
	private Boolean isSystem;
	@ApiModelProperty(value = "当前登录用户所属组织")
	@NotNull
	private String organizeId;
}
