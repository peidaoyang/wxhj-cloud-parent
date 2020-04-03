package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: DriverListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月4日 下午6:16:00 
 */
@Data
@ApiModel(value="司机信息分页查询请求对象")
public class DriverListRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="标志为,jobNumber:工号，accountId：用户id，name：姓名",example = "1")
	@NotBlank
	private String type;
}
