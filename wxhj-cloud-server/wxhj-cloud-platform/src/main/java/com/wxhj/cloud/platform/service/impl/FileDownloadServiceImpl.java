package com.wxhj.cloud.platform.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.enums.FileDownloadStatusEnum;
import com.wxhj.cloud.core.enums.FileDownloadTypeEnum;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.FileDownloadDO;
import com.wxhj.cloud.platform.mapper.FileDownloadMapper;
import com.wxhj.cloud.platform.service.FileDownloadService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author daxiong
 * @date 2020-04-02 09:34
 */
@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    @Resource
    FileDownloadMapper fileDownloadMapper;

    @Override
    public String insert(FileDownloadDO accountDownload) {
        String id = UUID.randomUUID().toString();
        accountDownload.setId(id);
        // 插入的状态都是下载中
        accountDownload.setStatus(FileDownloadStatusEnum.RUNNING.getCode());
        accountDownload.setType(FileDownloadTypeEnum.ACCOUNT_DOWNLOAD.getCode());
        accountDownload.setCreateTime(new Date());
        fileDownloadMapper.insert(accountDownload);
        return id;
    }

    @Override
    public PageInfo<FileDownloadDO> listPageByOrganizeIdAndTaskIdAndTime(IPageRequestModel iPageRequestModel,
                                                                         String organizeId, String taskId, Date startTime, Date endTime) {
        Example example = new Example(FileDownloadDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizeId", organizeId);
        if (!Strings.isNullOrEmpty(taskId)) {
            criteria.andEqualTo("taskId", taskId);
        }
        if (startTime != null && endTime != null) {
            criteria.andBetween("createTime", startTime, endTime);
        } else if (startTime != null) {
            criteria.andGreaterThanOrEqualTo("createTime", startTime);
        } else if (endTime != null) {
            criteria.andLessThanOrEqualTo("createTime", endTime);
        }
        return PageUtil.selectPageList(iPageRequestModel, () -> fileDownloadMapper.selectByExample(example));
    }

    @Override
    public void update(FileDownloadDO accountDownload) {
        fileDownloadMapper.updateByPrimaryKeySelective(accountDownload);
    }

}
