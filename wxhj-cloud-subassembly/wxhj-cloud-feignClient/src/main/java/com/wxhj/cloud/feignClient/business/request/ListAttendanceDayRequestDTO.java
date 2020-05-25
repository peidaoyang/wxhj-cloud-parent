/**
 * @className ListAttendanceDayRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 下午2:11:34
 */
package com.wxhj.cloud.feignClient.business.request;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className ListAttendanceDayRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 下午2:11:34
 */
@Data
@ApiModel(value="获取班次请求对象")
public class ListAttendanceDayRequestDTO extends CommonListPageRequestDTO {
	@ApiModelProperty(value = "是否是学生考勤，0：不是；1：是", example = "1")
	private Integer studentAttendance;
}
