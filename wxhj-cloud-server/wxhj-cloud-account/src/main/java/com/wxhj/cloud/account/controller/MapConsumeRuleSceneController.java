package com.wxhj.cloud.account.controller;

import com.wxhj.cloud.account.domain.MapConsumeRuleSceneDO;
import com.wxhj.cloud.account.service.MapConsumeRuleSceneService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MapConsumeRuleScene)表控制层
 *
 * @author makejava
 * @since 2020-05-28 10:48:02
 */
@RestController
@RequestMapping("mapConsumeRuleScene")
public class MapConsumeRuleSceneController {
    /**
     * 服务对象
     */
    @Resource
    private MapConsumeRuleSceneService mapConsumeRuleSceneService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MapConsumeRuleSceneDO selectOne(String id) {
        return null;
    }

}