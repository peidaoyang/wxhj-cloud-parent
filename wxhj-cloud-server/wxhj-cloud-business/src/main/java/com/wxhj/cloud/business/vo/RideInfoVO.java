///**
// * 
// */
//package com.wxhj.cloud.business.vo;
//
//import java.util.Date;
//
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @ClassName: RideInfoListResponseDTO.java
// * @author: cya
// * @Date: 2020年2月24日 上午10:10:10
// */
//@Data
//public class RideInfoVO implements IOrganizeModel {
//	@ApiModelProperty(value="订单编号")
//	private String orderNumber;
//	@ApiModelProperty(value="扣款金额")
//	private Integer amount;
//	@ApiModelProperty(value="票价")
//	private Integer price;
//	@ApiModelProperty(value="用户id")
//	private String accountId;
//	@ApiModelProperty(value="用户姓名")
//	private String accountName;
//	@ApiModelProperty(value="班次id")
//	private String flightId;
//	@ApiModelProperty(value="线路编号")
//	private String routeNumber;
//	@ApiModelProperty(value="车号")
//	private String carNumber;
//	
//	@ApiModelProperty(value="设备id")
//	private String deviceId;
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@ApiModelProperty(value="乘车事件")
//	private Date rideTime;
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@ApiModelProperty(value="创建时间")
//	private Date creatorTime;
//	@ApiModelProperty(value="线路名称")
//	private String routeName;
//	@ApiModelProperty(value="开始站点")
//	private String startSite;
//	@ApiModelProperty(value="结束站点")
//	private String endSite;
//	@ApiModelProperty(value="途径站点")
//	private String channelSite;
//	
//	@ApiModelProperty(value="组织编号（不能排序）")
//	private String organizeId;
//	@ApiModelProperty(value="组织名称（不能排序）")
//	private String organizeName;
//}
