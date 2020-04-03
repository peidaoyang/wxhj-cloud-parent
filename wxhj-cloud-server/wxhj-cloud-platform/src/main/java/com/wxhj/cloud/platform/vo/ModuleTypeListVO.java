/** 
 * @fileName: ModuleTypeListVO.java  
 * @author: pjf
 * @date: 2020年3月10日 上午10:36:45 
 */

package com.wxhj.cloud.platform.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className ModuleTypeListVO.java
 * @author pjf
 * @date 2020年3月10日 上午10:36:45   
*/
/**
 * @className ModuleTypeListVO.java
 * @author pjf
 * @date 2020年3月10日 上午10:36:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleTypeListVO {
	@ApiModelProperty(value="快选菜单类型")
	private Integer moduleType;
	@ApiModelProperty(value="快选菜单id")
	private String moduleId;
	@ApiModelProperty(value="快选菜单名称")
	private String moduleName;
}
