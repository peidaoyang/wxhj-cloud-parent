/** 
 * @fileName: SubmitAuthorityGroupInfoRequestDTO.java  
 * @author: pjf
 * @date: 2019年11月18日 下午4:45:55 
 */

package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SubmitAuthorityGroupInfoRequestDTO.java
 * @author pjf
 * @date 2019年11月18日 下午4:45:55   
*/

@Data
@ToString
@ApiModel( description = "修改/编辑权限组请求对象")
public class SubmitAuthorityGroupInfoRequestDTO {
	@ApiModelProperty(value = "权限组id", example = "guid")
	private String id;
	@ApiModelProperty(value = "组织id", example = "guid")
	@NotBlank(message="组织id不能为空")
	private String organizeId;
	@ApiModelProperty(value = "权限组名字", example = "权限组1")
	@NotBlank(message="不能为空")
	private String fullName;
	@ApiModelProperty(value = "权限组类型", example = "0")
	private Integer type;
	@ApiModelProperty(value = "场景idList")
	private List<String> sceneIdList;
	@ApiModelProperty(value = "用户idList")
	private List<String> accountIdList;
	
	@ApiModelProperty(value="是否自动同步人员权限,0：不自动，1：自动")
//	@Min(0)
	private Integer autoSynchro;

	public void setAutoSynchro(Integer autoSynchro) {
		if(autoSynchro == null) {
			autoSynchro = 0;
		}else {
			this.autoSynchro = autoSynchro;
		}
	}
}
