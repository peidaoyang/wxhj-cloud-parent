package com.wxhj.cloud.account.service;


import com.wxhj.cloud.account.domain.OrganizeCardPriorityDO;

import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/21 2:47 下午
 */
public interface OrganizeCardPriorityService {

    /**
     * 根据组织id获取该组织的卡付款优先级
     *
     * @param organizeId
     * @return java.util.List<com.wxhj.cloud.account.domain.OrganizeCardPriorityDO>
     * @author daxiong
     * @date 2020/5/21 4:13 下午
     */
    List<OrganizeCardPriorityDO> listByOrganizeId(String organizeId);

    /**
     * 对规则进行重排序
     *
     * @param organizeId
     * @param list
     * @return void
     * @author daxiong
     * @date 2020/5/21 5:19 下午
     */
    void reorder(String organizeId, List<OrganizeCardPriorityDO> list);

    /**
     * 插入数据
     *
     * @param organizeCardPriority
     * @return void
     * @author daxiong
     * @date 2020/5/21 5:22 下午
     */
    void insert(OrganizeCardPriorityDO organizeCardPriority);
}
