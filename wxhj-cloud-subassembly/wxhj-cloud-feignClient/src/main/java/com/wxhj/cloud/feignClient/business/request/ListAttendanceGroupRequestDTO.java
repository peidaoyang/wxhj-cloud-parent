///**
// * @className ListAttendanceGroupRequestDTO.java
// * @admin jwl
// * @date 2019年12月13日 下午3:05:45
// */
//package com.wxhj.cloud.feignClient.business.request;
//
//import javax.validation.constraints.Min;
//
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @className ListAttendanceGroupRequestDTO.java
// * @admin jwl
// * @date 2019年12月13日 下午3:05:45
// */
//@Data
//@ApiModel(value="获取考勤组请求对象")
//public class ListAttendanceGroupRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value = "条件参数", example = "")
//	private String nameValue;
//	@ApiModelProperty(value = "组织编号", example = "")
//	private String organizeId;
//	@ApiModelProperty(value = "单页行数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段", example = "id")
//	private String orderBy;
//}
