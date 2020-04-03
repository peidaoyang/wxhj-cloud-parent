//package com.wxhj.cloud.platform.dto.request;
//
//import javax.validation.constraints.Min;
//
//import com.esotericsoftware.kryo.NotNull;
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.ToString;
//
///**
// * 
// * @className SysUserPageRequestDTO.java
// * @author pjf
// * @date 2019年11月6日 上午8:46:56
// */
//@Data
//@ToString
//@ApiModel(description = "用户信息分页查询对象")
//public class SysUserPageRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value = "关键字")
//	@NotNull
//	private String nameValue;
//	@ApiModelProperty(value = "条数")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段")
//	private String orderBy;
//	@ApiModelProperty(value = "组织ID")
//	private String organizeId;
//}