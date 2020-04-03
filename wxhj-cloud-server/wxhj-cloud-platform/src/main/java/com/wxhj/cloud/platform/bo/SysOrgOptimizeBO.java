/**
 * 
 */
package com.wxhj.cloud.platform.bo;

import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SysOrgOptimizeBO.java
 * @author: cya
 * @Date: 2020年3月10日 上午10:02:58 
 */
@Data
public class SysOrgOptimizeBO {
	@Id
	@ApiModelProperty(value = "组织id、修改必填", example = "guid")
	private String id;
	@ApiModelProperty(value = "组织父级id", example = "guid")
	@NotBlank(message = "不能为空")
	private String parentId;
	@ApiModelProperty(value = "组织名称", example = "xxx组织")
	@NotBlank(message = "不能为空")
	private String fullName;
	
	@ApiModelProperty(value = "层级", example = "1")
	@Min(1)
	private Integer layers;
	@ApiModelProperty(value = "描述", example = "用于xxx的作用")
	private String description;
	@ApiModelProperty(value = "登录用户id")
	@NotNull
	private String userId;
	
	@ApiModelProperty(value = "菜单列表")
	@NotEmpty
	List<String> sysModuleIdList;
}
