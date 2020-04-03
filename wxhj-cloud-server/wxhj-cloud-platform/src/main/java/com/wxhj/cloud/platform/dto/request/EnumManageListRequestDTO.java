///**
// * 
// */
//package com.wxhj.cloud.platform.dto.request;
//
//
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @ClassName: EnumManageListRequestDTO.java
// * @author: cya
// * @Date: 2020年1月8日 下午1:55:55 
// */
//@Data
//@Api(value="枚举查询请求对象")
//public class EnumManageListRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value="枚举名称")
//	@NotNull
//	private String fullName;
//	@ApiModelProperty(value = "单页行数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段", example = "enum_code")
//	private String orderBy;
//}
