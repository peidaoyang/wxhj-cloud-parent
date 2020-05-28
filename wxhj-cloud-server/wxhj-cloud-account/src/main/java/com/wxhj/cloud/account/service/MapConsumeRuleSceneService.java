package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.MapConsumeRuleSceneDO;

import java.util.List;

/**
 * (MapConsumeRuleScene)表服务接口
 *
 * @author makejava
 * @since 2020-05-28 10:48:00
 */
public interface MapConsumeRuleSceneService {

    /**
     * 批量插入
     *
     * @param list
     * @return void
     * @author daxiong
     * @date 2020/5/28 10:52 上午
     */
    void insertList(List<MapConsumeRuleSceneDO> list);

    /**
     * 插入单条数据
     *
     * @param mapConsumeRuleScene
     * @return void
     * @author daxiong
     * @date 2020/5/28 10:53 上午
     */
    void insert(MapConsumeRuleSceneDO mapConsumeRuleScene);
}