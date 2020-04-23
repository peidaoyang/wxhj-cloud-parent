/** 
 * @fileName: VisitorInfoPosRequestDTO.java  
 * @author: pjf
 * @date: 2020年2月16日 下午1:38:35 
 */

package com.wxhj.common.device.dto.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className VisitorInfoPosRequestDTO.java
 * @author pjf
 * @date 2020年2月16日 下午1:38:35   
*/
/**
 * @className VisitorInfoPosRequestDTO.java
 * @author pjf
 * @date 2020年2月16日 下午1:38:35
 */
@ApiModel(value = "pos访客查询对象")
@Data
public class VisitorInfoPosRequestDTO {
	@ApiModelProperty(value = "身份证号")
	@NotNull
	private String idNumber;
	@ApiModelProperty(value = "场景编号")
	@NotNull
	private String sceneId;
	@ApiModelProperty(value = "当前时间",example="2020-03-03 09:35:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateTime;
}
