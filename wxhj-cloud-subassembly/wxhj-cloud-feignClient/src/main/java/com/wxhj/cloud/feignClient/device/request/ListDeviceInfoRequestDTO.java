//package com.wxhj.cloud.feignClient.device.request;
//
//import javax.validation.constraints.Min;
//
//import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
//import com.wxhj.cloud.core.utils.HumpUtil;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.ToString;
//
//@Data
//@ToString
//@ApiModel(description = "设备请求对象")
//public class ListDeviceInfoRequestDTO implements IPageRequestModel {
//	@ApiModelProperty(value = "条件参数", example = "")
//	private String nameValue;
//
//	@ApiModelProperty(value = "单页行数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段", example = "device_id")
//	private String orderBy;
//	
//	@Override
//	public void setOrderBy(String orderBy) {
//		this.orderBy = HumpUtil.humpToLine(orderBy);
//	}
//}
