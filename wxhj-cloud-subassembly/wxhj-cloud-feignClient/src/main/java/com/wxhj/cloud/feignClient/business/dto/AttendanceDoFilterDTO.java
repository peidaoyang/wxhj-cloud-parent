package com.wxhj.cloud.feignClient.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.enums.DayWorkTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * @author daxiong
 * @date 2020-04-07 14:55
 */
@Data
@AllArgsConstructor
public class AttendanceDoFilterDTO {
    /**
     * 请假或出差id
     */
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private DayWorkTypeEnum dayWorkTypeEnum;
}
