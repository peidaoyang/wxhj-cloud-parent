package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.business.dto.CurrentAttendanceDayRecDTO;
import com.wxhj.cloud.feignClient.business.dto.DurationDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020-04-08 17:42
 */
@Data
@ApiModel(value = "获取考勤规则记录VO")
public class GetAttendanceDaysVO {

    private String accountId;
    private String accountName;
    private String groupId;
    private String groupName;

    @ApiModelProperty(value = "日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    private Map<Integer, Integer> generateDurationSection() {
        durationList = durationList.stream().sorted().collect(Collectors.toList());
        Map<Integer, Integer> retMap = new TreeMap();
        durationList.forEach(
                q -> {
                    for (Integer i = q.getBeginTime(); i <= q.getEndTime(); i++) {
                        retMap.put(i, q.getType());
                    }
                }
        );
        return retMap;
    }
}
