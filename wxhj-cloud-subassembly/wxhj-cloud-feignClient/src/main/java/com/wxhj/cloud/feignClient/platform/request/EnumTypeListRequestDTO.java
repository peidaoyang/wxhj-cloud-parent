package com.wxhj.cloud.feignClient.platform.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: EnumTypeListRequestDTO.java
 * @author: cya
 * @Date: 2020年1月9日 下午3:31:27 
 */
@Data
public class EnumTypeListRequestDTO {
	@ApiModelProperty(value="枚举编号",example="1")
	@Min(0)
	@Max(99)
	Integer enumCode;
}
