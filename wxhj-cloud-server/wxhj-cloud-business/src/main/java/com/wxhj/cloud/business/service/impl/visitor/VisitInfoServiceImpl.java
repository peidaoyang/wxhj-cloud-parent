package com.wxhj.cloud.business.service.impl.visitor;


import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.VisitInfoDO;
import com.wxhj.cloud.business.mapper.view.VisitInfoMapper;
import com.wxhj.cloud.business.service.visitor.VisitInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @ClassName: VisitInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:29:22
 */
@Service
public class VisitInfoServiceImpl implements VisitInfoService {
    @Resource
    VisitInfoMapper visitInfoMapper;

    @Override
    public PageInfo<VisitInfoDO> selectByName(IPageRequestModel pageRequestModel, String organizeId, LocalDateTime startTime,
                                              LocalDateTime endTime) {
        Example example = new Example(VisitInfoDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId).andBetween("beginTime", startTime, endTime);
        PageInfo<VisitInfoDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
                () -> visitInfoMapper.selectByExample(example));
        return pageAttendanceData;
    }

    @Override
    public void insert(VisitInfoDO visitInfo) {
        visitInfoMapper.insert(visitInfo);
    }

}
