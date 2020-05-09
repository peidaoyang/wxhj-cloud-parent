package com.wxhj.cloud.feignClient.business.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.AppCommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "打卡记录查询请求对象")
@Data
public class MatchAttendanceDataByAccountRequestDTO extends AppCommonPageRequestDTO {
    @ApiModelProperty(value = "开始时间", example = "2019-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @ApiModelProperty(value = "结束时间", example = "2019-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
