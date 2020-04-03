package com.wxhj.cloud.platform.dto.request;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysOrganizeSubmitBO.java
 * @author pjf
 * @date 2019年11月6日 上午8:44:16
 */
@Data
@ToString
@ApiModel(description = "修改/添加组织请求对象")
public class SysOrgaSubmitRequestDTO {
	@Id
	@ApiModelProperty(value = "组织id、修改必填", example = "guid")
	private String id;
	@ApiModelProperty(value = "组织父级id", example = "guid")
	@NotBlank(message = "不能为空")
	private String parentId;
	@ApiModelProperty(value = "层级", example = "1")
	@Min(1)
	private Integer layers;
	@ApiModelProperty(value = "商户编码", example = "000011")
	private String encode;
	@ApiModelProperty(value = "是否有效标志", example = "0/1")
	@Max(1)
	@Min(0)
	private Integer isEnabledMark;
	@ApiModelProperty(value = "组织名称", example = "xxx组织")
	@NotBlank(message = "不能为空")
	private String fullName;
	@ApiModelProperty(value = "简称")
	private String shortName;
	@ApiModelProperty(value = "分类id")
	private String categoryId;
	@ApiModelProperty(value = "负责人id")
	private String managerId;
	@ApiModelProperty(value = "电话")
	private String telephone;
	@ApiModelProperty(value = "手机")
	private String mobilePhone;
	@ApiModelProperty(value = "微信")
	private String wechat;
	@ApiModelProperty(value = "传真")
	private String fax;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "归属区域")
	private String areaid;
	@ApiModelProperty(value = "地址")
	private String address;
	@ApiModelProperty(value = "排序字段", example = "1")
	@Min(0)
	private Integer sortCode;
	@ApiModelProperty(value = "描述", example = "用于xxx的作用")
	private String description;
	@ApiModelProperty(value = "登录用户id")
	@NotNull
	private String userId;
}
