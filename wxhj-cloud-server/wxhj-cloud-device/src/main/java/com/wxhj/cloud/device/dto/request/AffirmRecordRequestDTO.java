/** 
 * @fileName: AffirmRecordRequestDTO.java  
 * @author: pjf
 * @date: 2020年2月11日 下午3:35:24 
 */

package com.wxhj.cloud.device.dto.request;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className AffirmRecordRequestDTO.java
 * @author pjf
 * @date 2020年2月11日 下午3:35:24
 */

@Data
@ApiModel("确认记录请求对象")
public class AffirmRecordRequestDTO {
	@ApiModelProperty("确认编号")
	private List<Long> id;
}
