package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.NotNull;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: FlightListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月5日 下午5:42:47 
 */
@Data
@ApiModel(value="班次分页查询请求对象")
public class FlightListRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="标志为,flightNumber:班次编号，routeNumber：线路编号",example = "1")
	@NotNull
	private String type;
}
