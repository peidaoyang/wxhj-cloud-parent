/**
 * 
 */
package com.wxhj.cloud.platform.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: SysUserOrgReponseDTO.java
 * @author: cya
 * @Date: 2019年11月8日 下午2:17:01 
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel( description = "用户下组织信息返回对象")
public class MapOrgUserReponseDTO {
	@ApiModelProperty(value="组织id",example = "guid")
	private String id;
	@ApiModelProperty(value="关键字")
	private String fullName;
}
