/**
 * @className ListSceneInfoByIdRequestDTO.java
 * @admin admin
 * @date 2019年12月26日  下午5:39:27
 */
package com.wxhj.cloud.feignClient.platform.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className ListSceneInfoByIdRequestDTO.java
 * @admin admin
 * @date 2019年12月26日  下午5:39:27
 */
@Data
@ApiModel(value = "获取场景请求对象")
@NoArgsConstructor
@AllArgsConstructor
public class ListSceneInfoByIdRequestDTO {
	@ApiModelProperty(value = "场景编号")
	private List<String> id;
}
