/**
 * 
 */
package com.wxhj.cloud.platform.dto.request;

import java.util.List;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: SysOrganizeTreeRequestDTO.java
 * @author: cya
 * @Date: 2019年12月4日 下午1:38:42
 */
@ApiModel(description = "获取组织树型显示列表通过组织名(用于组织管理主页显示)对象")
@Data
@ToString
public class SysOrganizeTreeRequestDTO {
	@ApiModelProperty(value = "组织名")
	@NotNull
	private String fullName;
	@ApiModelProperty(value = "当前登录账户的子商户列表")
	private List<String> organizeChildList;
	@ApiModelProperty(value = "当前登录账户的商户id")
	@NotNull
	private String currentOrganizeId;
}
