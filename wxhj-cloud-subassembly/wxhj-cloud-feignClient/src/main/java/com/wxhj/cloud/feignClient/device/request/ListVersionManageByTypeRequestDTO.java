/**
 * @className ListVersionManageByTypeRequestDTO.java
 * @admin jwl
 * @date 2020年1月7日 下午3:31:03
 */
package com.wxhj.cloud.feignClient.device.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className ListVersionManageByTypeRequestDTO.java
 * @admin jwl
 * @date 2020年1月7日 下午3:31:03
 */
@Data
@ApiModel(value = "通过条件获取版本管理信息列表请求对象")
public class ListVersionManageByTypeRequestDTO {
	@ApiModelProperty(value = "组织编号")
	private String organizeId;
	@ApiModelProperty(value = "设备类型")
	private Integer deviceType;
	@ApiModelProperty(value = "资源类型")
	private Integer resourceType;
}
