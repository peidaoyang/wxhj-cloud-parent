package com.wxhj.cloud.feignClient.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author daxiong
 * @date 2020-04-02 18:23
 */
@Data
@ApiModel(value = "文件下载")
public class FileDownloadDTO {

    private String id;

    @ApiModelProperty(value = "组织id", example = "7c0f9909-4f38-4a18-a6a6-31883db5fef1")
    private String organizeId;

    @ApiModelProperty(value = "任务来源id", example = "7c0f9909-4f38-4a18-a6a6-31883db5fef1")
    private String taskId;

    @ApiModelProperty(value = "任务类型，1001：账户信息下载", example = "1001")
    private Integer type;

    @ApiModelProperty(value = "状态，1：下载中；2：下载成功；3：下载失败；4：取消下载", example = "1")
    private Integer status;

    @ApiModelProperty(value = "文件名称", example = "a4e9db49-cbab-462b-b848-861323742a34.zip")
    private String fileName;

    @ApiModelProperty(value = "下载路径", example = "http://su.bcebos.com/v1/burcket-dev1/var/folders/y6/819k3f")
    private String downloadUrl;

    @ApiModelProperty(value = "创建时间戳(unix时间)", example = "2020-04-04 10:04:32")
    private Date createTime;
}
