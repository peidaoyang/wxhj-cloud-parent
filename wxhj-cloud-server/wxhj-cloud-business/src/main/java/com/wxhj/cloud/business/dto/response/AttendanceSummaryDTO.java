package com.wxhj.cloud.business.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020/4/16 10:09 上午
 */
@Data
@Builder
public class AttendanceSummaryDTO {
    /**
     * 第一时间段上班时间
     */
    private Integer upTime;
    /**
     * 第一时间段下班时间
     */
    private Integer downTime;
    /**
     * 第一时间段班次状态，-1：无该班次；0：正常；1：休息；2：请假；3：出差；4：其他
     */
    private Integer timeState;
    /**
     * 请假时长
     */
    private Integer leaveTime;
    /**
     * 出差时长
     */
    private Integer travelTime;
    /**
     * 上班时长
     */
    private Integer workTime;
    /**
     * 是否需要重新初始化数组
     */
    private Boolean needInit;
}
