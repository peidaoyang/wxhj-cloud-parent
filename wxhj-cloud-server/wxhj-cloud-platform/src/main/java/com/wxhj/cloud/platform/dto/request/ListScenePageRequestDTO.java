///** 
// * @fileName: ListScenePageDTO.java  
// * @author: pjf
// * @date: 2019年11月13日 上午10:39:20 
// */
//
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
// * @className ListScenePageDTO.java
// * @author pjf
// * @date 2019年11月13日 上午10:39:20   
//*/
//
//@Data
//@ToString
//@ApiModel( description = "场景查询请求对象")
//public class ListScenePageRequestDTO implements  IPageRequestModel{
//	@ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
//	@NotBlank(message = "不能为空")
//	private String organizeId;
//	@ApiModelProperty(value = "场景名称", example = "")
//	@NotNull
//	private String nameValue;
//	@ApiModelProperty(value = "单页行数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段", example = "id")
//	@NotNull
//	private String orderBy;
//}

//预留
