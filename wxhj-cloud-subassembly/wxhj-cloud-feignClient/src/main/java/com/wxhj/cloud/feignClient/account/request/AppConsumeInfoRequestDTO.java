/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.core.statics.CaseFormatStaticClass;
import com.wxhj.cloud.feignClient.dto.AppCommonPageRequestDTO;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @ClassName: AppConsumeInfoRequestDTO.java
 * @author: cya
 * @Date: 2020年2月2日 下午4:11:57 
 */
@Data
@ToString
@ApiModel(description = "app查询消费信息 请求对象")
public class AppConsumeInfoRequestDTO extends AppCommonPageRequestDTO {
	@ApiModelProperty(value = "账户id",example = "0000000052")
	@NotBlank
	private String accountId;
	@ApiModelProperty(value = "开始时间", example = "2019-06-11")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startTime;
	@ApiModelProperty(value = "结束时间", example = "2020-06-11")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endTime;
	@ApiModelProperty("卡类型")
	private Integer cardType;

}
