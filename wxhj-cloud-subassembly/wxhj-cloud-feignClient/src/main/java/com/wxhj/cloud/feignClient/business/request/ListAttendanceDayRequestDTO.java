///**
// * @className ListAttendanceDayRequestDTO.java
// * @admin jwl
// * @date 2019年12月13日 下午2:11:34
// */
//package com.wxhj.cloud.feignClient.business.request;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @className ListAttendanceDayRequestDTO.java
// * @admin jwl
// * @date 2019年12月13日 下午2:11:34
// */
//@Data
//@ApiModel(value="获取班次请求对象")
//public class ListAttendanceDayRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value = "模糊查询", example = "测试班次")
//	@NotNull
//	private String nameValue;
//	@ApiModelProperty(value = "组织id", example = "guid")
//	@NotBlank
//	private String organizeId;
//	@ApiModelProperty(value="每页条数")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value="页数")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value="排序字段", example="xxx asc/desc")
//	private String orderBy;
//}
