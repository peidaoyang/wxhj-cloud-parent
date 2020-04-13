package com.wxhj.cloud.feignClient.dto;

import lombok.Data;

/**
 * @author daxiong
 * @date 2020-04-08 17:03
 */
@Data
public class CurrentAttendanceDayRecDTO {

    private String dayId;
    private Integer sequence;
    private String groupId;
    private Integer upTime;
    private Integer downTime;
    private Integer upExtent;
    private Integer downExtent;
}
