///**
// * 
// */
//package com.wxhj.cloud.platform.dto.request;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
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
// * @ClassName: SysRoleListByOrgIdRequestDTO.java
// * @author: cya
// * @Date: 2020年3月16日 上午11:00:10 
// */
//@ToString
//@Data
//@ApiModel(description = "角色信息分页查询对象")
//public class SysRoleListByOrgIdRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value = "关键字")
//	@NotNull
//	private String nameValue;
//	
//	@ApiModelProperty(value = "条数",example="5")
//	@Min(1)
//	private Integer rows;
//	
//	@ApiModelProperty(value = "当前页",example="1")
//	@Min(1)
//	private Integer page;
//	
//	@ApiModelProperty(value = "排序字段",example="creator_time desc")
//	@NotBlank
//	private String orderBy;
//	
//	@ApiModelProperty(value = "排序字段",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
//	private String organizeId;
//}
//预留
