package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.util.Date;

/**
 * (ViewAttendanceSummaryMatchingFinal)表服务接口
 * @author daxiong
 * @date 2020/4/21 1:47 下午
 */
public interface ViewAttendanceSummaryMatchingFinalService {

    /**
     * 根据账户id和起止时间获取考勤记录
     * @author daxiong
     * @date 2020/4/21 1:58 下午
     * @param pageRequestModel
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param accountId 账户id
     * @return com.github.pagehelper.PageInfo<com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO>
     */
    PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByAccountPage(IPageRequestModel pageRequestModel, Date beginTime,
                                                                     Date endTime, String accountId);

    /**
     * 根据根组织id和起止时间获取考勤记录
     * @author daxiong
     * @date 2020/4/21 1:58 下午
     * @param pageRequestModel
     * @param beginTime     开始时间
     * @param endTime       结束时间
     * @param organizeId    组织id
     * @return com.github.pagehelper.PageInfo<com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO>
     */
    PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByOrganizePage(IPageRequestModel pageRequestModel, Date beginTime,
                                                                     Date endTime, String organizeId);

}