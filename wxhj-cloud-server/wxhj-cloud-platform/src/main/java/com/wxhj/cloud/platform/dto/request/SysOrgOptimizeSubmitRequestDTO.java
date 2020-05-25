package com.wxhj.cloud.platform.dto.request;

import java.util.List;

import javax.validation.constraints.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: SysOrgOptimizeSubmitRequestDTO.java
 * @author: cya
 * @Date: 2020年3月10日 上午9:17:23 
 */
@Data
@ToString
@ApiModel(description = "修改/添加组织请求对象")
public class SysOrgOptimizeSubmitRequestDTO {
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

	@ApiModelProperty(value = "组织类型,默认为0，校园版为1", example = "0")
	@Min(0)
	@Max(1000)
	private Integer type;

	@ApiModelProperty(value="账户请求对象")
	private SysOrgUserSubmitRequestDTO sysOrgUserSubmitRequest;

	
	@ApiModelProperty(value = "菜单列表")
	@NotEmpty
	List<String> sysModuleIdList;
}
