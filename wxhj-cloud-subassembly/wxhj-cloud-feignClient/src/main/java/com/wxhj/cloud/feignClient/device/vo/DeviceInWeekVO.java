package com.wxhj.cloud.feignClient.device.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.device.vo.ViewDeviceRecordVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "功能使用记录（一周）")
public class DeviceInWeekVO {
    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate receivedDate;
    @ApiModelProperty(value = "使用记录统计")
    private List<ViewDeviceRecordVO> deceiveList;
}
