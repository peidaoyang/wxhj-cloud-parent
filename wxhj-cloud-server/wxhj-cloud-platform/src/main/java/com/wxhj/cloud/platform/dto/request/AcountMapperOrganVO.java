/** 
 * @fileName: AcountMapperOrgan.java  
 * @author: pjf
 * @date: 2019年10月11日 下午1:08:21 
 */

package com.wxhj.cloud.platform.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className AcountMapperOrgan.java
 * @author pjf
 * @date 2019年10月11日 下午1:08:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AcountMapperOrganVO {
	@ApiModelProperty(value="名称")
	private String fullName;
	@ApiModelProperty(value="用户关联组织的权限")
	private String mapId;
}
