/**
 * @className ListAttendanceDayRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 下午2:11:34
 */
package com.wxhj.cloud.feignClient.business.request;

import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author daxiong
 * @date 2020/5/14 4:43 下午
 */
@Data
@ApiModel(value="获取班次请求对象")
public class ListAllAttendanceDayRequestDTO extends CommonOrganizeRequestDTO {
	@ApiModelProperty(value = "是否是学生考勤，0：不是；1：是", example = "1")
	private Integer studentAttendance;
}
