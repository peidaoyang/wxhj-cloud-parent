/**
 * @className ListDayAttendanceDataRequestDTO.java
 * @author jwl
 * @date 2019年12月24日 上午9:13:07
 */
package com.wxhj.cloud.feignClient.business.request;



import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @className ListDayAttendanceDataRequestDTO.java
 * @author jwl
 * @date 2019年12月24日 上午9:13:07
 */
@Data
@ApiModel(value = "报表信息请求对象")
public class ListDayAttendanceDataRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value = "开始时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate beginTime;
	@ApiModelProperty(value = "结束时间", example = "2020-01-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate endTime;
}
