package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.ConsumeRuleDO;
import java.util.List;

/**
 * (ConsumeRule)表服务接口
 *
 * @author makejava
 * @since 2020-05-28 08:42:36
 */
public interface ConsumeRuleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ConsumeRuleDO selectById(String id);

    /**
     * 分页查询
     *
     * @param organizeId 根组织id
     * @return 对象列表
     */
    List<ConsumeRuleDO> listPageByOrganizeId(String organizeId);

    /**
     * 新增数据
     *
     * @param consumeRuleDO 实例对象
     * @return void
     */
    void insertCascade(ConsumeRuleDO consumeRuleDO, List<String> sceneIdList);

    /**
     * 修改数据
     *
     * @param consumeRuleDO 实例对象
     * @return void
     */
    void update(ConsumeRuleDO consumeRuleDO);

    /**
     * 通过主键删除数据
     *
     * @param consumeRuleDO 实例对象
     * @return void
     */
    void deleteById(ConsumeRuleDO consumeRuleDO);

}