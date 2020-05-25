/**
 * 
 */
package com.wxhj.cloud.business.dto.response;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: RideInfoListByAccoutIdVO.java
 * @author: cya
 * @Date: 2020年2月7日 下午1:47:32 
 */
@Data
@ApiModel(value="app乘车记录返回对象")
public class RideInfoListByAccoutIdResponseDTO {
	private String flightId;
	private String routeNumber;
	private String carNumber;
	private Integer amount;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rideTime;
}
