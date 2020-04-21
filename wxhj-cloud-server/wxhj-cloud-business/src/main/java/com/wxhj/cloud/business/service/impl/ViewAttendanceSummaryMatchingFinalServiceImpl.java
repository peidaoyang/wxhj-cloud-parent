package com.wxhj.cloud.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO;
import com.wxhj.cloud.business.mapper.view.ViewAttendanceSummaryMatchingFinalMapper;
import com.wxhj.cloud.business.service.ViewAttendanceSummaryMatchingFinalService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author daxiong
 * @date 2020/4/21 1:49 下午
 */
@Service
public class ViewAttendanceSummaryMatchingFinalServiceImpl implements ViewAttendanceSummaryMatchingFinalService {
    @Resource
    ViewAttendanceSummaryMatchingFinalMapper viewAttendanceSummaryMatchingFinalMapper;

    @Override
    public PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByOrganizePage(IPageRequestModel pageRequestModel,
                                                                             Date beginTime, Date endTime, String organizeId) {
        Example example = new Example(ViewAttendanceSummaryMatchingFinalDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId).andBetween("datetime", beginTime, endTime);
        return PageUtil.selectPageList(pageRequestModel, () -> viewAttendanceSummaryMatchingFinalMapper.selectByExample(example));
    }

    @Override
    public PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByAccountPage(IPageRequestModel pageRequestModel, Date beginTime,
                                                                            Date endTime, String accountId) {
        Example example = new Example(ViewAttendanceSummaryMatchingFinalDO.class);
        example.createCriteria().andEqualTo("accountId", accountId).andBetween("datetime", beginTime, endTime);
        return PageUtil.selectPageList(pageRequestModel, () -> viewAttendanceSummaryMatchingFinalMapper.selectByExample(example));
    }

}
