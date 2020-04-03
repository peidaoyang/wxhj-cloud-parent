/** 
 * @fileName: confirmAsyncMapListenListRequestBO.java  
 * @author: pjf
 * @date: 2019年10月31日 下午2:24:52 
 */

package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className confirmAsyncMapListenListRequestBO.java
 * @author pjf
 * @date 2019年10月31日 下午2:24:52
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "AsyncMapListenList确认请求对象")
public class ConfirmAsyncMapListenListRequestDTO {
	@ApiModelProperty(value = "id列表")
	@NotNull
	private List<Long> idList;
}
