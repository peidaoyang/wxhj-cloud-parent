/**
 * @className EntranceDayResponseDTO.java
 * @author jwl
 * @date 2020年1月10日 下午2:23:40
 */
package com.wxhj.cloud.business.dto.response;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.business.domain.EntranceDayRecDO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className EntranceDayResponseDTO.java
 * @author jwl
 * @date 2020年1月10日 下午2:23:40
 */
@Data
@ApiModel(value = "通行时间段返回对象")
public class EntranceDayResponseDTO {
	@ApiModelProperty(value = "时间段编号")
	private String id;
	@ApiModelProperty(value = "时间段名称")
	@NotBlank(message = "不能为空")
	private String fullName;
	@ApiModelProperty(value = "组织编号")
	@NotBlank(message = "不能为空")
	private String organizeId;
	@ApiModelProperty(value = "是否匹配")
	private Integer matchType;
	@ApiModelProperty(value = "时间描述")
	private String timeDescribe;
	@ApiModelProperty(value = "时间段详细设计")
	private List<EntranceDayRecDO> listEntranceDayRec;
}
