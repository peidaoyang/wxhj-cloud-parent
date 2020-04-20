/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: VisitorInfoListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月11日 下午4:09:44 
 */
@ApiModel(value="访客信息分页查询 请求对象")
@Data
public class VisitorInfoListRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="name：访客姓名，accountId：受访者id")
	@NotBlank
	private String type;
	@ApiModelProperty(value="审核状态,0:未审核，1：通过，2：拒绝, -1：全部",example = "1")
	@Min(-1)
	@Max(4)
	private Integer isCheck;
}
