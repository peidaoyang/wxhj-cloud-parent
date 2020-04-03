package com.wxhj.cloud.platform.dto.request;

import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysRoleSubmitRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 上午8:44:37
 */
@ToString
@Data
@ApiModel(description = "角色添加/修改请求对象")
public class SysRoleSubmitRequestDTO   {
	@ApiModelProperty(value = "角色主键", example = "guid")
	@Id
	private String id;
	@ApiModelProperty(value = "父级id")
	@NotBlank(message = "不能为空")
	private String organizeId;

	@ApiModelProperty(value = "角色名字", example = "角色xxx")
	@NotBlank(message = "不能为空")
	private String fullName;

	@ApiModelProperty(value = "排序字段", example = "角色xxx")
	@Min(0)
	private Integer sortCode;

	@ApiModelProperty(value = "角色描述")
	private String description;
	@ApiModelProperty(value = "菜单id列表")
	List<String> moduleIdList;

	@NotNull
	@ApiModelProperty(value = "当前登录用户的id")
	private String userId;
}
