package com.wxhj.cloud.platform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author daxiong
 * @date 2020-04-02 10:57
 */
@Table(name = "file_download")
@Data
public class FileDownloadDO {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 组织id
     */
    private String organizeId;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 任务说明
     */
    private String taskExplain;

    /**
     * 任务类型
     */
    private Integer type;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 返回的zip文件名称
     */
    private String fileName;

    /**
     * 标志唯一任务id
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 下载地址
     */
    private String downloadUrl;

}
