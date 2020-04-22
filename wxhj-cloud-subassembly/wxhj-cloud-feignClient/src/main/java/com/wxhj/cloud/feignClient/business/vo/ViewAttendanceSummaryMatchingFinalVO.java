package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author daxiong
 * @date 2020/4/21 2:09 下午
 */
@Data
@ApiModel(value = "考勤记录VO")
public class ViewAttendanceSummaryMatchingFinalVO implements IOrganizeModel {

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datetime;

    private String authorityGroupId;

    private String accountId;

    private Integer attendanceType;

    private String accountName;

    private String authorityGroupName;

    private String organizeId;

    /**
     * 组织名称
     */
    private String organizeName;

    private String childOrganizeId;

    private Integer sequence1;

    private Integer upTime1;

    private Integer downTime1;

    private Integer timeState1;

    private Integer leaveTime1;

    private Integer travelTime1;

    private Integer workTime1;

    private Integer realUpTime1;

    private Integer upLateTime1;

    private Integer realDownTime1;

    private Integer downEarlyTime1;

    private Integer workStatus1;

    private Integer sequence2;

    private Integer upTime2;

    private Integer downTime2;

    private Integer timeState2;

    private Integer leaveTime2;

    private Integer travelTime2;

    private Integer workTime2;

    private Integer realUpTime2;

    private Integer upLateTime2;

    private Integer realDownTime2;

    private Integer downEarlyTime2;

    private Integer workStatus2;

    private Integer sequence3;

    private Integer upTime3;

    private Integer downTime3;

    private Integer timeState3;

    private Integer leaveTime3;

    private Integer travelTime3;

    private Integer workTime3;

    private Integer realUpTime3;

    private Integer upLateTime3;

    private Integer realDownTime3;

    private Integer downEarlyTime3;

    private Integer workStatus3;

    private Integer dayStatus;
}
