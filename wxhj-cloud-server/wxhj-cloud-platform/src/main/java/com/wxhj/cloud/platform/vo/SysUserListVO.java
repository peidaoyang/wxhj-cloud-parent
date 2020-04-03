package com.wxhj.cloud.platform.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserListVO implements IOrganizeModel{
	@Id
	@ApiModelProperty(value="账户id")
	private String id;
	@ApiModelProperty(value="账号")
	private String account;
	@ApiModelProperty(value="姓名")
	private String realName;
	@ApiModelProperty(value="头像")
	private String headIcon;
	@ApiModelProperty(value="生日")
	private Date birthDay;
	@ApiModelProperty(value="手机")
	private String mobilePhone;
	@ApiModelProperty(value="邮箱")
	private String email;
	@ApiModelProperty(value="微信")
	private String wechat;
	@ApiModelProperty(value="0：非管理员，1：管理员")
	private Integer isAdmin;
	@ApiModelProperty(value="排序")
	private Integer sortCode;
	@ApiModelProperty(value="删除标志：0，未删除；1，已删除")
	private Integer isDeleteMark;
	@ApiModelProperty(value="是否允许删除：0，未删除；1，已删除")
	private Integer isEnabledMark;
	@ApiModelProperty(value="描述")
	private String description;
	@ApiModelProperty(value="创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	@ApiModelProperty(value="创建人员编号")
	private String creatorUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="修改时间")
	private Date lastModifyTime;
	@ApiModelProperty(value="修改人员编号")
	private String lastModifyUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="删除时间")
	private Date deleteTime;
	@ApiModelProperty(value="删除人员编号")
	private String deleteUserId;
	@ApiModelProperty(value="组织编号")
	private String organizeId;
	@ApiModelProperty(value="账号密码")
	private String userPassword;
	@ApiModelProperty(value="账号密码key值")
	private String userSecretKey;
	@ApiModelProperty(value="组织名称")
	private String organizeName;
	
	@ApiModelProperty(value="角色名称（不能排序）")
	private String roleListStr;
	
	public void setRoleListStr(List<String> roleIdList) {
		this.roleListStr = StringUtils.join(roleIdList, ",");
	}
	
}
