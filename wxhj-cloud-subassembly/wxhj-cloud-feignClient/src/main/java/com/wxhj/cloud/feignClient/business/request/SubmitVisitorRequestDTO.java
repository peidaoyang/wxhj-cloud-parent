/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SubmitVisitorRequestDTO.java
 * @author: cya
 * @Date: 2020年2月11日 下午4:17:14 
 */
@ApiModel(value="新增/修改 访客信息请求对象")
@Data
public class SubmitVisitorRequestDTO {
	@ApiModelProperty(value="访客id",example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	private String id;
	@ApiModelProperty(value="身份证",example="321183199212021111")
	@NotBlank
	private String idNumber;
	@ApiModelProperty(value="手机",example="13911111111")
	@NotBlank
	private String phone;
	@ApiModelProperty(value="姓名",example="来访1")
	@NotNull
	private String name;
	@ApiModelProperty(value="账户id",example = "0000000028")
	@NotBlank
	private String accountId;
	@ApiModelProperty(value="账户姓名",example="测试1")
	@NotBlank
	private String accountName;
	@ApiModelProperty(value="公司名称",example="公交公司")
	private String company;
	@ApiModelProperty(value="职位",example="员工")
	private String position;
	@ApiModelProperty(value="开始时间 yyyy-MM-dd HH:mm:ss",example="2020-01-02 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	@ApiModelProperty(value="结束时间 yyyy-MM-dd HH:mm:ss",example="2020-03-19 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	@ApiModelProperty(value="来访原因",example="商户洽谈")
	private String reason;
	@ApiModelProperty(value="组织id",example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotBlank
	private String organizeId;
/*	@ApiModelProperty(value="场景id,单个场景填场景主键，全部填*",example = "289d238b-a486-4d21-a0cc-af4731499379")
	@NotBlank
	private String sceneId;*/
}
