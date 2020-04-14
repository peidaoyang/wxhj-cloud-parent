package com.wxhj.cloud.feignClient.business.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    public Set<Integer> generateSection() {
        Set<Integer> retSet = new HashSet<>(downTime - upTime);
        for (Integer i = upTime; i <= downTime; i++) {
            retSet.add(i);
        }
        return retSet;
    }
}
