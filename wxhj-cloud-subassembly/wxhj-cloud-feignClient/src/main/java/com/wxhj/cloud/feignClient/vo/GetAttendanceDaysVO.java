package com.wxhj.cloud.feignClient.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CurrentAttendanceDayRecDTO;
import com.wxhj.cloud.feignClient.dto.DurationDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author daxiong
 * @date 2020-04-08 17:42
 */
@Data
@ApiModel(value = "获取考勤规则记录VO")
public class GetAttendanceDaysVO {

    @ApiModelProperty(value = "日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dayInfo;
    @ApiModelProperty(value = "考勤规则类型", example = "0：正常班；1：休息；2：请假；3：出差")
    private Integer type;
    @ApiModelProperty(value = "考勤规则类型中文描述")
    private String typeName;

    @ApiModelProperty(value = "考勤规则list")
    /**
     * key为考勤班次的序号
     */
    private Map<Integer, CurrentAttendanceDayRecDTO> currentAttendanceDayRecMap;

    @ApiModelProperty(value = "考勤最早开始时间")
    private Integer earliestTime;
    @ApiModelProperty(value = "考勤最晚结束时间")
    private Integer latestTime;

    List<DurationDTO> durationList;

}
