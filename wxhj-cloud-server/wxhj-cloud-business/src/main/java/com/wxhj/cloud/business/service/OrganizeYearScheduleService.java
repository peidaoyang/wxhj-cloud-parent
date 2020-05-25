package com.wxhj.cloud.business.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleDO;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleRecVO;

import java.util.List;
import java.util.Map;

/**
 * @author daxiong
 * @date 2020/5/12 5:33 下午
 */
public interface OrganizeYearScheduleService {

    /**
     * 根据组织id和name分页显示
     *
     * @param iPageRequestModel
     * @param nameValue
     * @param organizeId
     * @return com.github.pagehelper.PageInfo<com.wxhj.cloud.business.domain.OrganizeYearScheduleDO>
     * @author daxiong
     * @date 2020/5/12 5:36 下午
     */
    PageInfo<OrganizeYearScheduleDO> listByOrganizeIdAndName(IPageRequestModel iPageRequestModel, String organizeId,
                                                             String nameValue);

    /**
     * 获取组织下的所有年度安排列表
     *
     * @param organizeId
     * @return java.util.List<com.wxhj.cloud.business.domain.OrganizeYearScheduleDO>
     * @author daxiong
     * @date 2020/5/18 2:02 下午
     */
    List<OrganizeYearScheduleDO> listAllByOrganizeId(String organizeId);

    /**
     * 插入或更新
     *
     * @param organizeYearSchedule
     * @param list                 关联规则
     * @return void
     * @author daxiong
     * @date 2020/5/14 8:59 上午
     */
    void submitOrganizeYearScheduleCascade(OrganizeYearScheduleDO organizeYearSchedule, List<OrganizeYearScheduleRecVO> list);

    /**
     * 删除
     *
     * @param id
     * @return void
     * @author daxiong
     * @date 2020/5/14 8:59 上午
     */
    void deleteOrganizeYearSchedule(String id);

    /**
     * 根据id获取记录
     *
     * @param id
     * @return com.wxhj.cloud.business.domain.OrganizeYearScheduleDO
     * @author daxiong
     * @date 2020/5/15 3:25 下午
     */
    OrganizeYearScheduleDO selectByPrimaryKey(String id);

    /**
     * 根据id和date获取当天的考勤规则序号并缓存
     *
     * @param id
     * @param map
     * @param dateStr
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/5/18 5:59 下午
     */
    Integer selectCacheByIdAndDate(String id, Map<String, OrganizeYearScheduleRecDO> map, String dateStr);
}
