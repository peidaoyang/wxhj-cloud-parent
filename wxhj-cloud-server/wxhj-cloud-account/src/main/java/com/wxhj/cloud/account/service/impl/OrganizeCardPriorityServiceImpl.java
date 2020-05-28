package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.OrganizeCardPriorityDO;
import com.wxhj.cloud.account.mapper.OrganizeCardPriorityMapper;
import com.wxhj.cloud.account.service.OrganizeCardPriorityService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/21 4:14 下午
 */
@Service
public class OrganizeCardPriorityServiceImpl implements OrganizeCardPriorityService {

    @Resource
    OrganizeCardPriorityMapper organizeCardPriorityMapper;

    @Override
    public void reorder(String organizeId, List<OrganizeCardPriorityDO> list) {
        Example example = new Example(OrganizeCardPriorityDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId);
        organizeCardPriorityMapper.deleteByExample(example);
        // 循环插入
        list.forEach(this::insert);
    }

    @Override
    public void insert(OrganizeCardPriorityDO organizeCardPriority) {
        organizeCardPriorityMapper.insert(organizeCardPriority);
    }

    @Override
    public List<OrganizeCardPriorityDO> listByOrganizeId(String organizeId) {
        Example example = new Example(OrganizeCardPriorityDO.class);
        example.createCriteria().andEqualTo("organizeId", organizeId);
        List<OrganizeCardPriorityDO> organizeCardPriorities = organizeCardPriorityMapper.selectByExample(example);
        if (organizeCardPriorities == null || organizeCardPriorities.size() == 0) {
            // 每个组织下都默认有两种卡
            organizeCardPriorities = new ArrayList<>(2);
            organizeCardPriorities.add(OrganizeCardPriorityDO.builder().cardType(0).cardName("现金钱包").organizeId(organizeId).priority(0).build());
            organizeCardPriorities.add(OrganizeCardPriorityDO.builder().cardType(1).cardName("补贴钱包").organizeId(organizeId).priority(1).build());
        }
        return organizeCardPriorities;
    }
}
