package com.wxhj.cloud.platform.vo;

import java.util.List;

import com.wxhj.cloud.feignClient.account.bo.AuthorityGroupInfoBO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OptionalAuthByOrganVO {
	@ApiModelProperty(value="组织id")
	private String id;
	@ApiModelProperty(value="父级组织id")
	private String parentId;
	@ApiModelProperty(value="层级")
	private Integer layers;
	@ApiModelProperty(value="组织编号")
	private String encode;
	@ApiModelProperty(value="组织名称")
	private String fullName;
	@ApiModelProperty(value="排序")
	private Integer sortCode;
	List<AuthorityGroupInfoBO> authGroupInfoList;

}
