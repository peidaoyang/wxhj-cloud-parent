/**
 * 
 */
package com.wxhj.cloud.feignClient.device.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: VersionManageListRequestDTO.java
 * @author: cya
 * @Date: 2020年1月2日 下午4:54:47 
 */
@Data
@Api(value="版本管理信息分页查询对象")
public class VersionManageListRequestDTO extends CommonPageRequestDTO{
	@ApiModelProperty(value="版本类型")
	@Min(-1)
	@Max(10)
	private Integer deviceType;
}
