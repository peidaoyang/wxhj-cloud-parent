package com.wxhj.cloud.feignClient.account.vo;

import java.util.List;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListAuthorityGroupPageVO implements IOrganizeModel{
	@ApiModelProperty(value="权限组id")
	private String id;
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	@ApiModelProperty(value="权限组名称")
	private String fullName;
	@ApiModelProperty(value="权限组类型")
	private Integer type;
	
	@ApiModelProperty(value="场景列表（不能排序）")
	private List<String> scencIdList;
	@ApiModelProperty(value="账户id列表（不能排序）")
	private List<String> accountIdList;
	
	@ApiModelProperty(value="自动同步类型：0，不同步，1，同步")
	private Integer autoSynchro;
}
