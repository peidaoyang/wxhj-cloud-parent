/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;



import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.feignClient.dto.AppCommonPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName: AppRechargeInfoRequestDTO.java
 * @author: cya
 * @Date: 2020年2月2日 下午1:54:00 
 */
@Data
@ToString
@ApiModel(description = "app查询充值信息 请求对象")
public class AppRechargeInfoRequestDTO extends AppCommonPageRequestDTO {
	@ApiModelProperty(value = "账户id",example = "0000000028")
	@NotBlank
	private String accountId;
	@ApiModelProperty(value = "开始时间", example = "2019-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;
	@ApiModelProperty(value = "结束时间", example = "2020-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

	private Integer cardType;
}
