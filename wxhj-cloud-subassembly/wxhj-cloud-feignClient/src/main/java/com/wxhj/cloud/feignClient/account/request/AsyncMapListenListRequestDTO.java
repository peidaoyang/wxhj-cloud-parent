package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("异步同步人脸数量")
public class AsyncMapListenListRequestDTO {
	@ApiModelProperty("异步同步数量")
	@Min(1)
	private Integer asyncCount;
}
