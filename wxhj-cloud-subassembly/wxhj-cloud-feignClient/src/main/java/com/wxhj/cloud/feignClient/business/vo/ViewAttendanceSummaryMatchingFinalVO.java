package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.utils.DateFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


/**
 * @author daxiong
 * @date 2020/4/21 2:09 下午
 */
@Data
@ApiModel(value = "考勤汇总的记录VO")
public class ViewAttendanceSummaryMatchingFinalVO implements IOrganizeModel {



    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="日期")
    private LocalDate datetime;
    @ApiModelProperty(value="权限组id(不显示)")
    private String authorityGroupId;
    @ApiModelProperty(value="权限组id")
    private String accountId;
    @ApiModelProperty(value="权限组类型(不显示)")
    private Integer attendanceType;
    @ApiModelProperty(value="姓名")
    private String accountName;
    @ApiModelProperty(value="考勤组名称")
    private String authorityGroupName;
    @ApiModelProperty(value="组织id(不显示)")
    private String organizeId;

    /**
     * 组织名称
     */
    @ApiModelProperty(value="组织名称")
    private String organizeName;
    @ApiModelProperty(value="子组织的id")
    private String childOrganizeId;

    @ApiModelProperty(value="第一个段")
    private Integer sequence1;
    @ApiModelProperty(value="正常上班时间")
    private Integer upTime1;
    private String upTime1Str;
    @ApiModelProperty(value="正常下班时间")
    private Integer downTime1;
    private String downTime1Str;
    @ApiModelProperty(value="状态()")
    private Integer timeState1;
    @ApiModelProperty(value="请假总时长")
    private Integer leaveTime1;
    @ApiModelProperty(value="出差总时长")
    private Integer travelTime1;
    @ApiModelProperty(value="工作总时长")
    private Integer workTime1;
    @ApiModelProperty(value="实际上班时间")
    private Integer realUpTime1;
    private String realUpTime1Str;
    @ApiModelProperty(value="迟到时间")
    private Integer upLateTime1;
    @ApiModelProperty(value="实际下班时间")
    private Integer realDownTime1;
    private String realDownTime1Str;
    @ApiModelProperty(value="早退时间")
    private Integer downEarlyTime1;
    @ApiModelProperty(value="实际工作时长")
    private Integer realWorkTime1;
    @ApiModelProperty(value="工作状态(0：正常，1：迟到，2：早退，3：迟到早退，4：上班缺卡，5：下班缺卡，6：旷工)")
    private Integer workStatus1;

    private Integer sequence2;

    private Integer upTime2;
    private String upTime2Str;

    private Integer downTime2;
    private String downTime2Str;

    private Integer timeState2;

    private Integer leaveTime2;

    private Integer travelTime2;

    private Integer workTime2;

    private Integer realUpTime2;
    private String realUpTime2Str;

    private Integer upLateTime2;

    private Integer realDownTime2;
    private String realDownTime2Str;

    private Integer downEarlyTime2;
    private Integer realWorkTime2;
    private Integer workStatus2;

    private Integer sequence3;

    private Integer upTime3;
    private String upTime3Str;

    private Integer downTime3;
    private String downTime3Str;

    private Integer timeState3;

    private Integer leaveTime3;

    private Integer travelTime3;

    private Integer workTime3;

    private Integer realUpTime3;
    private String realUpTime3Str;

    private Integer upLateTime3;

    private Integer realDownTime3;
    private String realDownTime3Str;

    private Integer downEarlyTime3;
    private Integer realWorkTime3;
    private Integer workStatus3;
    @ApiModelProperty(value="当天状态")
    private Integer dayStatus;

    @ApiModelProperty(value = "当天工作总时长")
    private Integer workTotal;
    private String workTotalStr;
    private Integer leaveTotal;
    private Integer travelTotal;
    private Integer lateTotal;
    private Integer earlyTotal;

    public void formatView() {
        setUpTime1Str(DateFormat.minute2HourMinute(upTime1));
        setDownTime1Str(DateFormat.minute2HourMinute(downTime1));
        setRealUpTime1Str(DateFormat.minute2HourMinute(realUpTime1));
        setRealDownTime1Str(DateFormat.minute2HourMinute(realDownTime1));
        
        setUpTime2Str(DateFormat.minute2HourMinute(upTime2));
        setDownTime2Str(DateFormat.minute2HourMinute(downTime2));
        setRealUpTime2Str(DateFormat.minute2HourMinute(realUpTime2));
        setRealDownTime2Str(DateFormat.minute2HourMinute(realDownTime2));
        
        setUpTime3Str(DateFormat.minute2HourMinute(upTime3));
        setDownTime3Str(DateFormat.minute2HourMinute(downTime3));
        setRealUpTime3Str(DateFormat.minute2HourMinute(realUpTime3));
        setRealDownTime3Str(DateFormat.minute2HourMinute(realDownTime3));

        // 统计当天工作、请假、出差、迟到、早退等时间
//        setWorkTotal(MathUtil.add(MathUtil.subIfNullZero(realDownTime1, realUpTime1),
//                MathUtil.subIfNullZero(realDownTime2, realUpTime2),
//                MathUtil.subIfNullZero(realDownTime3, realUpTime3)));
//        setWorkTotalStr(DateUtil.minute2Hour(getWorkTotal()));
//        setLeaveTotal(MathUtil.add(leaveTime1, leaveTime2, leaveTime3));
//        setTravelTotal(MathUtil.add(travelTime1, travelTime2, travelTime3));
//        setLateTotal(MathUtil.add(upLateTime1, upLateTime2, upLateTime3));
//        setEarlyTotal(MathUtil.add(downEarlyTime1, downEarlyTime2, downEarlyTime3));
    }
}
