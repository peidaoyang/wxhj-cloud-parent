package com.wxhj.cloud.platform.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.wxhj.cloud.platform.bo.SysUserSubmitListBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: AccountImportRequestDTO.java
 * @author: cya
 * @Date: 2020年3月13日 下午2:17:29 
 */
@Data
@ApiModel(value="账号分配")
public class AccountImportRequestDTO {
	@ApiModelProperty(value = "人员id", example = "0000000008")
	@NotBlank
	private String id;
	
	@ApiModelProperty(value = "组织角色idlist")
	@NotEmpty
	private List<SysUserSubmitListBO> sysUserSubmitRequestList;
	
	@ApiModelProperty(value = "当前登录用户的id",example="aaaea5be-8273-4bdd-bd6f-4f66eaadd555")
	@NotBlank
	private String userId;
}
