package com.wxhj.cloud.account.controller;

import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.account.domain.ConsumeRuleDO;
import com.wxhj.cloud.account.service.ConsumeRuleService;
import com.wxhj.cloud.feignClient.account.request.consume.rule.SubmitConsumeRuleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ConsumeRule)表控制层
 *
 * @author makejava
 * @since 2020-05-28 08:42:39
 */
@RestController
@RequestMapping("/consumeRule")
@Api(tags = "消费规则管理")
public class ConsumeRuleController {

    @Resource
    ConsumeRuleService consumeRuleService;
    @Resource
    Mapper beanMapper;

    @PostMapping("/listPageConsumeRule")
    @ApiOperation("分页查询消费规则")
    public void listPageConsumeRule(@RequestBody @Validated SubmitConsumeRuleDTO submitConsumeRule) {
        ConsumeRuleDO consumeRuleDO = beanMapper.map(submitConsumeRule, ConsumeRuleDO.class);
        consumeRuleService.insertCascade(consumeRuleDO, submitConsumeRule.getSceneIdList());
    }

}