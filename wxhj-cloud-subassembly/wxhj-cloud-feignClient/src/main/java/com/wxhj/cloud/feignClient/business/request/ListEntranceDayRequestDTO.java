///**
// * @className ListEntranceDayRequestDTO.java
// * @admin jwl
// * @date 2020年1月10日 下午2:10:09
// */
//package com.wxhj.cloud.feignClient.business.request;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @className ListEntranceDayRequestDTO.java
// * @admin jwl
// * @date 2020年1月10日 下午2:10:09
// */
//@Data
//public class ListEntranceDayRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value = "模糊查询", example = "时间段")
//	@NotNull
//	private String nameValue;
//	@ApiModelProperty(value = "组织id", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
//	@NotBlank
//	private String organizeId;
//	@ApiModelProperty(value="每页条数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value="页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value="排序字段", example="id")
//	@NotNull
//	private String orderBy;
//}
