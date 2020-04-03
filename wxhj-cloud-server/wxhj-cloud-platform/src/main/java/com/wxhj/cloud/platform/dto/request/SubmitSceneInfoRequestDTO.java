/** 
 * @fileName: SubmitSceneInfoRequestDTO.java  
 * @author: pjf
 * @date: 2019年11月13日 下午1:12:22 
 */

package com.wxhj.cloud.platform.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className SubmitSceneInfoRequestDTO.java
 * @author pjf
 * @date 2019年11月13日 下午1:12:22
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel( description = "修改编辑场景请求对象")
public class SubmitSceneInfoRequestDTO {
	@ApiModelProperty(value = "场景id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotNull
	private String id;
	@ApiModelProperty(value = "场景名字", example = "场景1")
	@NotBlank(message = "不能为空")
	private String sceneName;
	@ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotBlank(message="不能为空")
	private String organizeId;
	@ApiModelProperty(value = "权限组idList", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	private List<String> authGroupIdList;

}