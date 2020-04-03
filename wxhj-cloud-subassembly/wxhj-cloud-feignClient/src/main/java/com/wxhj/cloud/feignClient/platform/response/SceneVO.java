/**
 * @className SceneResponseDTO.java
 * @admin jwl
 * @date 2019年12月26日 下午5:04:43
 */
package com.wxhj.cloud.feignClient.platform.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SceneResponseDTO.java
 * @admin jwl
 * @date 2019年12月26日 下午5:04:43
 */
@Data
@ApiModel(value = "场景返回对象")
public class SceneVO {
	@ApiModelProperty(value = "场景编号")
	private String sceneId;
	@ApiModelProperty(value = "场景名称")
	private String sceneName;
}
