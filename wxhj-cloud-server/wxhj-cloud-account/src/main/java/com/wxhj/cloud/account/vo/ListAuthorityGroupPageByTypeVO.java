/**
 * 
 */
package com.wxhj.cloud.account.vo;

import java.util.List;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ListAuthorityGroupPageByTypeVO.java
 * @author: cya
 * @Date: 2020年3月13日 上午11:10:55
 */
@Data
@ApiModel(value = "权限组查询返回对象")
public class ListAuthorityGroupPageByTypeVO implements IOrganizeModel {
	@ApiModelProperty(value="编号")
	private String id;
	
	@ApiModelProperty(value="组织id（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	
	@ApiModelProperty(value="权限组名称")
	private String fullName;
	@ApiModelProperty(value="权限组类型")
	private Integer type;
	@ApiModelProperty(value="场景id列表（不能排序）")
	private List<String> scencIdList;
	@ApiModelProperty(value="用户id列表（不能排序）")
	private List<String> accountIdList;
	
	@ApiModelProperty(value="自动同步类型：0，不同步，1，同步")
	private Integer autoSynchro;
}
