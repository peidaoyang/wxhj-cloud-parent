///**
// * @className ListMonthAttendanceDataRequestDTO.java
// * @admin jwl
// * @date 2020年1月19日 下午4:02:26
// */
//package com.wxhj.cloud.feignClient.business.request;
//
//import java.util.Date;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * @className ListMonthAttendanceDataRequestDTO.java
// * @admin jwl
// * @date 2020年1月19日 下午4:02:26
// */
//@Data
//@ApiModel(value = "汇总报表信息请求对象")
//public class ListMonthAttendanceDataRequestDTO extends CommonListPageRequestDTO{
//	@ApiModelProperty(value = "开始时间", example = "2019-12-22")
//	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date beginTime;
//	@ApiModelProperty(value = "结束时间", example = "2020-12-22")
//	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date endTime;
//
//}
