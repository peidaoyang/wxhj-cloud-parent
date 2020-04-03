/**
 * @className SubmitEntranceDayRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 上午10:08:36
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SubmitEntranceDayRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 上午10:08:36
 */
@Data
@ApiModel(value = "编辑通行时间段请求对象")
public class SubmitEntranceDayRequestDTO {
	@ApiModelProperty(value = "时间段编号")
	private String id;
	@ApiModelProperty(value = "时间段名称")
	@NotBlank(message = "不能为空")
	private String fullName;
	@ApiModelProperty(value = "组织编号")
	@NotBlank(message = "不能为空")
	private String organizeId;
	@ApiModelProperty(value = "是否匹配")
	@Min(0)
	@Max(1)
	private Integer matchType;
	@ApiModelProperty(value = "时间描述")
	private String timeDescribe;
	@ApiModelProperty(value = "时间段详细设计")
	private List<SubmitEntranceDayRecRequestDTO> listEntranceDayRec;
}
