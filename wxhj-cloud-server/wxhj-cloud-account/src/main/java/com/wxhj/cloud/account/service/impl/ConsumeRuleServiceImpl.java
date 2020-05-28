package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.ConsumeRuleDO;
import com.wxhj.cloud.account.mapper.ConsumeRuleMapper;
import com.wxhj.cloud.account.service.ConsumeRuleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * (ConsumeRule)表服务实现类
 *
 * @author makejava
 * @since 2020-05-28 08:42:38
 */
@Service
public class ConsumeRuleServiceImpl implements ConsumeRuleService {
    @Resource
    private ConsumeRuleMapper consumeRuleMapper;

    @Override
    public ConsumeRuleDO selectById(String id) {
        return consumeRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ConsumeRuleDO> listPageByOrganizeId(String organizeId) {
        Example example = new Example(ConsumeRuleDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId);
        return consumeRuleMapper.selectByExample(example);
    }

    @Override
    public void insertCascade(ConsumeRuleDO consumeRuleDO, List<String> sceneIdList) {
        consumeRuleDO.setId(UUID.randomUUID().toString());
        consumeRuleDO.setCreateTime(LocalDateTime.now());
        consumeRuleMapper.insert(consumeRuleDO);
    }

    @Override
    public void update(ConsumeRuleDO consumeRuleDO) {
        consumeRuleMapper.updateByPrimaryKeySelective(consumeRuleDO);
    }

    @Override
    public void deleteById(ConsumeRuleDO consumeRuleDO) {
        consumeRuleMapper.deleteByPrimaryKey(consumeRuleDO);
    }
}