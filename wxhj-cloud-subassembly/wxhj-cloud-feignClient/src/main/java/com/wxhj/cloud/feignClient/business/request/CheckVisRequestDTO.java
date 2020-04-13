/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: CheckVisRequestDTO.java
 * @author: cya
 * @Date: 2020年2月11日 下午4:27:38 
 */
@Data
@ApiModel(value="审核访客 请求对象")
public class CheckVisRequestDTO {
	@ApiModelProperty(value="访客id")
	@NotBlank
	private String id;
	@ApiModelProperty(value="审核结果,1:通过，2:拒绝")
	@NotNull
	private Integer isCheck;
	@ApiModelProperty(value="场景id,单个场景填场景主键，全部填*",example = "289d238b-a486-4d21-a0cc-af4731499379")
	@NotBlank
	private String sceneId;
}
