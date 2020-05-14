/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;




import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName: RideInfoListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月6日 下午12:59:31 
 */
@Data
@ApiModel("乘车记录分页查询请求对象")
public class RideInfoListRequestDTO  extends CommonListPageRequestDTO {
	@ApiModelProperty(value="用户id:accountId,班次编号:flightId",example = "flightId")
	@NotBlank
	private String type;
	
	@ApiModelProperty(value="开始时间",example="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginTime;
	@ApiModelProperty(value="结束时间",example="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

}
