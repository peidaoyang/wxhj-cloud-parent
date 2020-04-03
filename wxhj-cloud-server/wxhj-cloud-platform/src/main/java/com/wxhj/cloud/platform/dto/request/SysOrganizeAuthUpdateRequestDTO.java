package com.wxhj.cloud.platform.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysOrganizeAuthUpdateRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 上午8:45:05
 */
@Data
@ToString
public class SysOrganizeAuthUpdateRequestDTO  {
	@ApiModelProperty(value = "组织id")
	@NotBlank(message = "不能为空")
	String id;
	
	@ApiModelProperty(value = "组织名称")
	@NotBlank(message = "不能为空")
	String fullName;
	
	@ApiModelProperty(value = "菜单列表")
	@NotEmpty
	List<String> sysModuleIdList;
	@ApiModelProperty(value = "登录用户id")
	@NotNull
	private String userId;
}
