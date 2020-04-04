package com.wxhj.cloud.platform.vo;


import java.util.List;

import com.wxhj.cloud.feignClient.account.vo.AuthorityBySceneIdVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListScenePageVO {
	@ApiModelProperty(value="场景id")
	private String id;
	@ApiModelProperty(value="场景名称")
	private String sceneName;
	@ApiModelProperty(value="组织id")
	private String organizeId;
	@ApiModelProperty(value="组织名称")
	private String organizeName;
	
	@ApiModelProperty(value="权限组列表（不能排序）")
	private List<AuthorityBySceneIdVO> authorityList;
}
