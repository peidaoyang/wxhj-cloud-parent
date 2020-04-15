package com.wxhj.cloud.platform.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
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
     * 批量删除
     * @param idList
     */
    void delete(List<String> idList);

    /**
     * 根据主键id删除
     * @param id
     */
    void delete(String id);

    /**
     * 获取下载记录列表
     * @param organizeId
     * @param taskId
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<FileDownloadDO> listPageByOrganizeIdAndTaskIdAndTime(IPageRequestModel iPageRequestModel,
                                                                  String organizeId, String taskId, Date startTime, Date endTime);

    /**
     * 根据主键id获取记录列表
     * @author daxiong
     * @date 2020/4/15 8:49 上午
     * @param idList
     * @return java.util.List<com.wxhj.cloud.platform.domain.FileDownloadDO>
     */
    List<FileDownloadDO> listFileDownload(List<String> idList);
}
