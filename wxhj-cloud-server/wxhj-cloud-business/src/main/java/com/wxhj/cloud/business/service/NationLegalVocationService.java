package com.wxhj.cloud.business.service;

import com.wxhj.cloud.business.domain.NationLegalVocationDO;

import java.time.LocalDate;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/8 5:45 下午
 */
public interface NationLegalVocationService {

    /**
     * 插入国家法定节假日（包括调休日）
     *
     * @param nationLegalVocation
     * @return void
     * @author daxiong
     * @date 2020/5/8 5:45 下午
     */
    void insert(NationLegalVocationDO nationLegalVocation);

    /**
     * 根据year获取国家法定节假日列表
     *
     * @param beginTime
     * @param endTime
     * @return java.util.List<com.wxhj.cloud.business.domain.NationLegalVocationDO>
     * @author daxiong
     * @date 2020/5/13 11:08 上午
     */
    List<NationLegalVocationDO> listNationLegalVocation(LocalDate beginTime, LocalDate endTime);


    /**
     * 生成对应年份的周末信息
     *
     * @param beginYear
     * @param endYear
     * @return java.util.List<com.wxhj.cloud.business.domain.NationLegalVocationDO>
     * @author daxiong
     * @date 2020/5/13 4:49 下午
     */
    List<NationLegalVocationDO> generateWeekends(Integer beginYear, Integer endYear);
}
