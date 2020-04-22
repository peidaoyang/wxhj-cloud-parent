package com.wxhj.cloud.business.domain.view;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * (ViewAttendanceSummaryMatchingFinal)实体类
 *
 * @author makejava
 * @since 2020-04-21 13:46:22
 */
@Data
@Table(name = "view_attendance_summary_matching_final")
public class ViewAttendanceSummaryMatchingFinalDO implements Serializable {
    private static final long serialVersionUID = -74996984284102306L;

    private Date datetime;

    private String authorityGroupId;

    private String accountId;

    private Integer attendanceType;

    private String accountName;

    private String authorityGroupName;

    private String organizeId;

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