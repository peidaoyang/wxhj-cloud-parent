///**
// * @className ListDeviceGlobalParameterRequestDTO.java
// * @admin jwl
// * @date 2019年12月10日 下午12:53:59
// */
//package com.wxhj.cloud.feignClient.device.request;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.ToString;
//
///**
// * @className ListDeviceGlobalParameterRequestDTO.java
// * @admin jwl
// * @date 2019年12月10日 下午12:53:59
// */
//@Data
//@ToString
//public class ListDeviceGlobalParameterRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value = "条件参数", example = "")
//	private String nameValue;
//	@ApiModelProperty(value = "组织id")
//	@NotBlank
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
