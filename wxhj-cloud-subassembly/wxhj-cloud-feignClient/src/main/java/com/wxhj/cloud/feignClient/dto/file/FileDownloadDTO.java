package com.wxhj.cloud.feignClient.dto.file;

import lombok.Data;

import java.util.Date;

/**
 * @author daxiong
 * @date 2020-04-02 18:23
 */
@Data
public class FileDownloadDTO {

    private String id;
    private String organizeId;
    private String taskId;
    private Integer type;
    private Integer status;
    private String fileName;
    private String downloadUrl;
    private Date createTime;
}
