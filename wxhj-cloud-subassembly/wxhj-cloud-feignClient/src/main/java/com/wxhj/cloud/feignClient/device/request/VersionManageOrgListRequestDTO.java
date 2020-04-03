/**
 * 
 */
package com.wxhj.cloud.feignClient.device.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: VersionManageOrgListRequestDTO.java
 * @author: cya
 * @Date: 2020年1月9日 上午9:25:08 
 */
@Data
@Api(value="版本管理信息组织分页查询对象")
public class VersionManageOrgListRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="版本类型")
	@Min(-1)
	@Max(10)
	private Integer deviceType;

}
