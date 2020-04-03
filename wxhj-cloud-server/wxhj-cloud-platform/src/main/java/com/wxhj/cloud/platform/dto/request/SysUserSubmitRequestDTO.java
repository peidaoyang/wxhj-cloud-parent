package com.wxhj.cloud.platform.dto.request;

import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.esotericsoftware.kryo.NotNull;
import com.wxhj.cloud.platform.bo.SysUserSubmitListBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysUserSubmitRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 上午8:47:24
 */
@ToString
@Data
@ApiModel(description = "修改/新增用户请求对象")
public class SysUserSubmitRequestDTO   {
	@Id
	private String id;
	@ApiModelProperty(value = "账户", example = "test")
	@NotBlank
	private String account;
	@ApiModelProperty(value = "姓名", example = "张三")
	@NotBlank
	private String realName;
	@ApiModelProperty(value = "手机", example = "13912345678")
	@NotBlank
	private String mobilePhone;
	@ApiModelProperty(value = "排序", example = "0")
	@Min(0)
	private Integer sortCode;
	@ApiModelProperty(value = "描述", example = "用于xxx")
	private String description;
	@ApiModelProperty(value = "组织id（后台返回）", example = "0000011")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value = "密码", example = "test")
	@NotBlank
	private String userPassword;
	@ApiModelProperty(value = "组织角色list")
	private List<SysUserSubmitListBO> sysUserSubmitRequestList;
	@NotNull
	@ApiModelProperty(value = "当前登录用户的id")
	private String userId;
}
