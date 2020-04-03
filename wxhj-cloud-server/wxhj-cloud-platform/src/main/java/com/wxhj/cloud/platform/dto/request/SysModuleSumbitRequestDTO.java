package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysModuleSumbitRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 上午8:45:23
 */
@Data
@ToString
@ApiModel(description = "菜单修改/新增请求对象")
public class SysModuleSumbitRequestDTO {
	@ApiModelProperty(value = "菜单id", example = "06324be3-e581-4bf8-9c0c-ef152d8efb22")
	private String id;
	@ApiModelProperty(value = "父级id、必填，最上级为0", example = "06324be3-e581-4bf8-9c0c-ef152d8efb33")
	@NotBlank(message = "不能为空")
	private String parentId;
	@ApiModelProperty(name = "layers", value = "层级",example="")
	@Min(0)
	private Integer layers;
	@ApiModelProperty(value = "菜单名称", example = "菜单1")
	@NotBlank
	private String fullName;
	@ApiModelProperty(value = "链接地址", example = "/sysModule/test")
	private String urlAddress;
	@ApiModelProperty(value = "目标")
	private String target;
	@ApiModelProperty(name = "isMenu", value = "是否菜单,不填后台默认为菜单")
	private Integer isMenu;
	@ApiModelProperty(value = "排序", example = "1")
	@Min(0)
	private Integer sortCode;
	@ApiModelProperty(value = "描述", example = "用于XXX展示")
	private String description;
	@ApiModelProperty(value = "登录用户id")
	@NotNull
	private String userId;

}
