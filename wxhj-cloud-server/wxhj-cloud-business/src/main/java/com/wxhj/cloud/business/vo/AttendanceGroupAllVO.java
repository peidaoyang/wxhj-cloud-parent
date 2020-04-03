/**
 * @className AttendanceGroupAllResponseDTO.java
 * @author jwl
 * @date 2019年12月27日 上午9:54:51
 */
package com.wxhj.cloud.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className AttendanceGroupAllResponseDTO.java
 * @author jwl
 * @date 2019年12月27日 上午9:54:51
 */
@Data
@ApiModel(value = "考勤组全部返回对象")
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceGroupAllVO {
	@ApiModelProperty(value = "考勤组编号")
	private String id;
	@ApiModelProperty(value = "考勤组名称")
	private String fullName;
}
