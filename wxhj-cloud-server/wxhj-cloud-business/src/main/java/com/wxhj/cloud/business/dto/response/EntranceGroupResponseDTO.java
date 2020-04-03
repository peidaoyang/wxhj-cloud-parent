/**
 * @className EntranceGroupResponseDTO.java
 * @author jwl
 * @date 2020年1月13日 上午8:53:29
 */
package com.wxhj.cloud.business.dto.response;

import java.util.List;

import com.wxhj.cloud.business.vo.EntranceGroupRecVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className EntranceGroupResponseDTO.java
 * @author jwl
 * @date 2020年1月13日 上午8:53:29
 */
@Data
@ApiModel(value = "通行组返回对象")
public class EntranceGroupResponseDTO {
	@ApiModelProperty(value = "通行组编号")
	private String id;
	@ApiModelProperty(value = "通行组名称")
	private String fullName;
	@ApiModelProperty(value = "通行类型")
	private Integer groupType;
	@ApiModelProperty(value = "通行组详情列表")
	private List<EntranceGroupRecVO> entranceDayList;
	
	@ApiModelProperty(value = "组织编号")
	private String organizeId;
	@ApiModelProperty(value = "用户集合")
	private List<String> accountList;
	@ApiModelProperty(value = "场景集合")
	private List<String> sceneList;
}
