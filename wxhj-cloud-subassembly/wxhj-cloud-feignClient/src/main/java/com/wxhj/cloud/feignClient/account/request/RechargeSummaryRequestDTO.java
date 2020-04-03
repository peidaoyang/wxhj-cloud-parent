///**
// * 
// */
//package com.wxhj.cloud.feignClient.account.request;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @ClassName: RechargeSummaryRequestDTO.java
// * @author: cya
// * @Date: 2020年2月4日 下午1:36:44 
// */
//@Data
//@ApiModel(value="充值汇总报表请求对象")
//public class RechargeSummaryRequestDTO implements IPageRequestModel{
//	@NotBlank
//	@ApiModelProperty(value="开始时间",example="yyyy-MM-dd")
//	private String beginTime;
//	@NotBlank
//	@ApiModelProperty(value="结束时间",example="yyyy-MM-dd")
//	private String endTime;
//	@ApiModelProperty(value = "单页行数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段", example = "recharge_time")
//	private String orderBy;
//}
