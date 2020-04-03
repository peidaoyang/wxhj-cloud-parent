/**
 * @className MapAuthSceneResponseDTO.java
 * @admin jwl
 * @date 2020年1月16日 下午1:35:30
 */
package com.wxhj.cloud.feignClient.account.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className MapAuthSceneResponseDTO.java
 * @admin jwl
 * @date 2020年1月16日 下午1:35:30
 */
@Data
@ApiModel(value = "权限场景返回对象")
public class MapAuthSceneResponseDTO {
	private String id;
	private String authorityGroupId;
	private String sceneId;
}
