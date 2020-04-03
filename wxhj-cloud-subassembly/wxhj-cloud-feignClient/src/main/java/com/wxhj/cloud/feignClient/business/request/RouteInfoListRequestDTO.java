/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;


import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: RouteInfoListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月4日 下午3:58:47 
 */
@Data
@ApiModel(value="获取信息信息分页查询 对象")
public class RouteInfoListRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="标志位,routeNumber:表示线路编号，site:表示站点名称",example="routeNumber")
	@NotBlank
	private String type;
}
