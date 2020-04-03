/**
 * 
 */
package com.wxhj.cloud.feignClient.device.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ImportFileDeviceAuthorizeRequestDTO.java
 * @author: cya
 * @Date: 2019年12月11日 上午11:30:07 
 */
@Data
@ApiModel(description = "通过文件新增授权信息")
public class ImportFileDeviceAuthorizeRequestDTO {
	@ApiModelProperty(value="文件名",example="213.csv")
	private String fileName;
}
