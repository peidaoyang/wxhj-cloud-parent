//package com.wxhj.cloud.feignClient.device.request;
//
//import javax.validation.constraints.Min;
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.ToString;
//
//@Data
//@ToString
//public class ListDeviceStateRequestDTO implements IPageRequestModel{
//	@ApiModelProperty(value = "条件参数", example = "")
//	private String nameValue;
//	@ApiModelProperty(value = "组织编号", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
//	private String organizeId;
//	@ApiModelProperty(value = "单页行数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段", example = "device_id")
//	private String orderBy;
//}
