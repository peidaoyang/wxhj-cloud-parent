package com.wxhj.cloud.feignClient.platform.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author daxiong
 * @date 2020-04-04 16:53
 */
@Data
@Api(value = "文件打包下载请求实体")
public class FileDownloadRequestDTO {
    @ApiModelProperty("组织id")
    @NotBlank
    private String organizeId;
    private String sceneId;

    private String taskExplain;
}
