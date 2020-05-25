package com.wxhj.cloud.feignClient.business.vo;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListDayAttendanceDataVO implements IOrganizeModel{
	@ApiModelProperty(value="编号")
	private String id;
	@ApiModelProperty(value="考勤权限组编号")
	private String authorityGroupId;
	@ApiModelProperty(value="用户账号")
	private String accountId;
	@ApiModelProperty(value="用户名")
	private String name;
	
	@ApiModelProperty(value="组织id（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序）")
	
	private String organizeName;
	@ApiModelProperty(value="考勤规则名称")
	private String attendanceGroupName;	
	@ApiModelProperty(value="班次名称")
	private String attendanceDayName;	
	
	@ApiModelProperty(value="班次编号")
	private String attendanceDayId;
	@ApiModelProperty(value="班次类型")
	private String attendanceType;
	
	@ApiModelProperty(value="时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private LocalDateTime datetime;
	
	@ApiModelProperty(value="第一时间段")
	private String serialNumber1;
	@ApiModelProperty(value="第一时间段上班时间")
	private String upTime1;
	@ApiModelProperty(value="/第一时间段上班匹配时间")
	private String upMatchingTime1;
	@ApiModelProperty(value="第一时间段上班差")
	private Integer upMatchingTimeDisparity1;
	@ApiModelProperty(value="第一时间段上班状态")
	private String upTimeState1;
	@ApiModelProperty(value="第一时间段下班时间")
	private String downTime1;
	@ApiModelProperty(value="第一时间段下班匹配时间")
	private String downMatchingTime1;
	@ApiModelProperty(value="第一时间段下班差")
	private Integer downMatchingTimeDisparity1;
	@ApiModelProperty(value="第一时间段下班状态")
	private String downTimeState1;
	@ApiModelProperty(value="第二时间段")
	private String serialNumber2;
	@ApiModelProperty(value="第二时间段上班时间")
	private String upTime2;
	@ApiModelProperty(value="第二时间段上班匹配时间")
	private String upMatchingTime2;
	@ApiModelProperty(value="第二时间段上班差")
	private Integer upMatchingTimeDisparity2;
	@ApiModelProperty(value="第二时间段上班状态")
	private String upTimeState2;
	@ApiModelProperty(value="第二时间段下班时间")
	private String downTime2;
	@ApiModelProperty(value="第二时间段下班匹配时间")
	private String downMatchingTime2;
	@ApiModelProperty(value="第二时间段下班差")
	private Integer downMatchingTimeDisparity2;
	@ApiModelProperty(value="第二时间段下班状态")
	private String downTimeState2;
	@ApiModelProperty(value="第三时间段")
	private String serialNumber3;
	@ApiModelProperty(value="第三时间段上班时间")
	private String upTime3;
	@ApiModelProperty(value="第三时间段上班匹配时间")
	private String upMatchingTime3;
	@ApiModelProperty(value="第三时间段上班差")
	private Integer upMatchingTimeDisparity3;
	@ApiModelProperty(value="第三时间段上班状态")
	private String upTimeState3;
	@ApiModelProperty(value="第三时间段下班时间")
	private String downTime3;
	@ApiModelProperty(value="第三时间段下班匹配时间")
	private String downMatchingTime3;
	@ApiModelProperty(value="第三时间段下班差")
	private Integer downMatchingTimeDisparity3;
	@ApiModelProperty(value="第三时间段下班状态")
	private String downTimeState3;
}
