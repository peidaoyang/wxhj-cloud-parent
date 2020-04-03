/**
 * @className MapEntrAuthAllResponseDTO.java
 * @author jwl
 * @date 2020年1月13日 下午2:08:01
 */
package com.wxhj.cloud.business.dto.response;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className MapEntrAuthAllResponseDTO.java
 * @author jwl
 * @date 2020年1月13日 下午2:08:01
 */
@Data
@ApiModel(value = "通行权限组全部返回对象")
public class MapEntrAuthAllResponseDTO implements IOrganizeModel{
	@ApiModelProperty(value = "通行编号")
	private String entranceId;
	@ApiModelProperty(value = "通行权限编号")
	private String authorityId;
	@ApiModelProperty(value = "权限名称")
	private String fullName;
	@ApiModelProperty(value = "组织编号")
	private String organizeId;
	private String organizeName;
	@ApiModelProperty(value = "通行名称")
	private String entranceName;
}
