package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.feignClient.business.vo.ViewAccountAttendanceMatchingFinalVO;

import java.util.Date;
import java.util.List;

/**
 * (ViewAttendanceSummaryMatchingFinal)表服务接口
 *
 * @author daxiong
 * @date 2020/4/21 1:47 下午
 */
public interface ViewAttendanceSummaryMatchingFinalService {

    /**
     * 根据账户id和起止时间获取考勤记录
     *
     * @param pageRequestModel
     * @param beginTime        开始时间
     * @param endTime          结束时间
     * @param accountId        账户id
     * @return com.github.pagehelper.PageInfo<com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO>
     * @author daxiong
     * @date 2020/4/21 1:58 下午
     */
    PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByAccountPage(IPageRequestModel pageRequestModel, Date beginTime,
                                                                     Date endTime, String accountId);

    /**
     * 根据根组织id和起止时间获取考勤记录
     *
     * @param pageRequestModel
     * @param beginTime        开始时间
     * @param endTime          结束时间
     * @param organizeId       组织id
     * @param nameValue        模糊查询条件
     * @return com.github.pagehelper.PageInfo<com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO>
     * @author daxiong
     * @date 2020/4/21 1:58 下午
     */
    PageInfo<ViewAttendanceSummaryMatchingFinalDO> listByOrganizePage(IPageRequestModel pageRequestModel, Date beginTime,
                                                                      Date endTime, String organizeId, String nameValue);

    /**
     * 根据根组织id和起止时间获取考勤记录，不分页，获取全部
     *
     * @param beginTime
     * @param endTime
     * @param organizeId
     * @param nameValue
     * @return java.util.List<com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO>
     * @author daxiong
     * @date 2020/5/12 10:08 上午
     */
    List<ViewAttendanceSummaryMatchingFinalDO> listByOrganizePageNoPage(Date beginTime, Date endTime,
                                                                  String organizeId, String nameValue);

    /**
     * 汇总人员考勤信息
     *
     * @param viewAttendanceSummaryList
     * @return List<ViewAccountAttendanceMatchingFinalVO>
     * @author daxiong
     * @date 2020/5/11 4:07 下午
     */
    List<ViewAccountAttendanceMatchingFinalVO> gatherAccountAttendanceInfo(List<ViewAttendanceSummaryMatchingFinalDO> viewAttendanceSummaryList);

}