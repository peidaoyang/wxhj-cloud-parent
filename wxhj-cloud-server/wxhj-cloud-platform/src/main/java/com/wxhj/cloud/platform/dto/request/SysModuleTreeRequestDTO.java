/** 
 * @fileName: ModuleDO.java  
 * @author: pjf
 * @Date: 2019年10月9日 下午3:29:53 
 */
package com.wxhj.cloud.platform.dto.request;


import com.esotericsoftware.kryo.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @className SysModuleSelectRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 上午8:45:30
 */
@ToString
@Data
public class SysModuleTreeRequestDTO{
	@ApiModelProperty(value = "用户名", example = "菜单1")
	@NotNull
	String fullName;
}
