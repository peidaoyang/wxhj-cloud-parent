/**
 * @className SubmitEntranceGroupRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午5:00:16
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.List;

import com.wxhj.cloud.feignClient.business.bo.EntranceGroupRecBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SubmitEntranceGroupRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午5:00:16
 */
@Data
@ApiModel(value = "编辑通行规则请求对象")
public class SubmitEntranceGroupRequestDTO {
	@ApiModelProperty(value = "通行规则编号", example = "")
	private String id;
	@ApiModelProperty(value = "通行规则名称", example = "测试通行规则")
	private String fullName;
	@ApiModelProperty(value = "通行规则类型", example = "0")
	private Integer groupType;
	@ApiModelProperty(value = "组织编号", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	private String organizeId;
	@ApiModelProperty(value = "通行规则详细设计")
	private List<EntranceGroupRecBO> entranceGroupRecList;
//
	@ApiModelProperty(value = "场景idList")
	private List<String> sceneIdList;
	@ApiModelProperty(value = "用户编号")
	private List<String> accountIdList;

	@ApiModelProperty(value="是否自动同步人员权限,0：不自动，1：自动")
//	@Min(0)
	private Integer autoSynchro;
}
