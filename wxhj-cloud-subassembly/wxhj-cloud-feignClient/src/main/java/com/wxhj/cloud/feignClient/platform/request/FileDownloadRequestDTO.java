package com.wxhj.cloud.feignClient.platform.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author daxiong
 * @date 2020-04-03 15:18
 */
@Data
@Api(value = "文件下载请求实体")
public class FileDownloadRequestDTO {
    @ApiModelProperty("组织id")
    @NotBlank
    private String organizeId;
    private String sceneId;

    @ApiModelProperty("搜索条件，创建时间-起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("搜索条件，创建时间-结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
