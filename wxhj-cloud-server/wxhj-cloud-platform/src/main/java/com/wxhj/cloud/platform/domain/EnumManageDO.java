/**
 * 
 */
package com.wxhj.cloud.platform.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: EnumManageDO.java
 * @author: cya
 * @Date: 2020年1月8日 上午11:18:08 
 */
@Data
@Table(name="enum_manage")
public class EnumManageDO {
	@Id
	@ApiModelProperty(value="枚举主键")
	private String id;
	@ApiModelProperty(value="枚举编号")
	private Integer enumCode;
	@ApiModelProperty(value="枚举名称")
	private String enumName;
	@ApiModelProperty(value="枚举类型编号")
	private Integer enumType;
	@ApiModelProperty(value="枚举类型名称")
	private String enumTypeName;
	@ApiModelProperty(value = "是否需要显示")
	private Integer isHidden;
}
