package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.MapConsumeRuleSceneDO;
import com.wxhj.cloud.account.mapper.MapConsumeRuleSceneMapper;
import com.wxhj.cloud.account.service.MapConsumeRuleSceneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MapConsumeRuleScene)表服务实现类
 *
 * @author makejava
 * @since 2020-05-28 10:48:01
 */
@Service("mapConsumeRuleSceneService")
public class MapConsumeRuleSceneServiceImpl implements MapConsumeRuleSceneService {
    @Resource
    private MapConsumeRuleSceneMapper mapConsumeRuleSceneMapper;

    @Override
    public void insertList(List<MapConsumeRuleSceneDO> list) {
        list.forEach(this::insert);
    }

    @Override
    public void insert(MapConsumeRuleSceneDO mapConsumeRuleScene) {
        mapConsumeRuleSceneMapper.insert(mapConsumeRuleScene);
    }
}