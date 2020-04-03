package com.wxhj.cloud.platform.service;

import com.wxhj.cloud.platform.domain.FileDownloadDO;

import java.util.Date;
import java.util.List;

/**
 * @author daxiong
 * @date 2020-04-02 09:34
 */
public interface FileDownloadService {

    /**
     * 插入
     * @param accountDownload
     * @return
     */
    String insert(FileDownloadDO accountDownload);

    /**
     * 修改，实体中有字段值的才修改
     * @param accountDownload
     */
    void update(FileDownloadDO accountDownload);

    /**
     * 获取下载记录列表
     * @param organizeId
     * @param taskId
     * @param startTime
     * @param endTime
     * @return
     */
    List<FileDownloadDO> listByOrganizeIdAndTaskIdAndTime(String organizeId, String taskId, Date startTime, Date endTime);
}
