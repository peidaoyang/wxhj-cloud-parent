package com.wxhj.cloud.business.service;

import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;

import java.util.List;
import java.util.Map;

/**
 * @author daxiong
 * @date 2020/5/12 5:33 下午
 */
public interface OrganizeYearScheduleRecService {

    /**
     * 根据关联id获取列表
     *
     * @param refId
     * @return java.util.List<com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO>
     * @author daxiong
     * @date 2020/5/13 4:17 下午
     */
    List<OrganizeYearScheduleRecDO> listByRefId(String refId);

    /**
     * 根据关联id获取列表，并根据工作日状态进行分组
     *
     * @param refId
     * @return java.util.Map<java.lang.Integer, java.util.List < com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO>>
     * @author daxiong
     * @date 2020/5/18 11:30 上午
     */
    Map<Integer, List<OrganizeYearScheduleRecDO>> listGroupByDayStatus(String refId);

    /**
     * 插入
     *
     * @param organizeYearScheduleRec
     * @return void
     * @author daxiong
     * @date 2020/5/13 4:17 下午
     */
    void insert(OrganizeYearScheduleRecDO organizeYearScheduleRec);

    /**
     * 插入
     *
     * @param organizeYearScheduleRecList
     * @return void
     * @author daxiong
     * @date 2020/5/13 4:17 下午
     */
    void insertBatch(List<OrganizeYearScheduleRecDO> organizeYearScheduleRecList);

    /**
     * 删除
     *
     * @param refId
     * @return void
     * @author daxiong
     * @date 2020/5/13 4:17 下午
     */
    void delete(String refId);

    /**
     * 获取某个节假日规则的所有日期类型
     *
     * @param organizeYearScheduleId
     * @return java.util.List<java.lang.Integer>
     * @author daxiong
     * @date 2020/5/20 4:30 下午
     */
    List<Integer> getTotalDayType(String organizeYearScheduleId);
}
