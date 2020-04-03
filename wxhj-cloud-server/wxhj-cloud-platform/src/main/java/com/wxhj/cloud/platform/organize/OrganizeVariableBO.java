package com.wxhj.cloud.platform.organize;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.wxhj.cloud.platform.dto.request.SysOrgUserSubmitRequestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrganizeVariableBO {

	@ApiModelProperty(value = "组织id、修改必填", example = "guid")
	private String id;
	@ApiModelProperty(value = "组织父级id", example = "guid")
	@NotBlank(message = "不能为空")
	private String parentId;
	@ApiModelProperty(value = "组织名称", example = "xxx组织")
	@NotBlank(message = "不能为空")
	private String fullName;

	@ApiModelProperty(value = "描述", example = "用于xxx的作用")
	private String description;
	@ApiModelProperty(value = "登录用户id")
	@NotNull
	private String userId;

	@ApiModelProperty(value = "账户请求对象")
	private SysOrgUserSubmitRequestDTO sysOrgUserSubmitRequest;

	@ApiModelProperty(value = "菜单列表")
	@NotEmpty
	private List<String> sysModuleIdList;
	//
	private String roleId;
	private String accountId;
}
