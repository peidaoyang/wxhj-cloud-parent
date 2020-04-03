/**
 * @className SubmitMapAttendanceAuthorizeRequestDTO.java
 * @admin jwl
 * @date 2019年12月18日 上午10:51:24
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SubmitMapAttendanceAuthorizeRequestDTO.java
 * @admin jwl
 * @date 2019年12月18日 上午10:51:24
 */
@Data
@ApiModel(value = "编辑考勤权限组请求对象")
public class SubmitMapAttendanceAuthorizeRequestDTO {
	@ApiModelProperty(value = "考勤组id", example = "guid")
	@NotBlank(message="组织id不能为空")
	private String attendanceId;
	@ApiModelProperty(value = "权限组")
	@NotBlank(message="权限组不能为空")
	private List<AuthorityGroupInfoRequestDTO> authorizeList;
	
}
