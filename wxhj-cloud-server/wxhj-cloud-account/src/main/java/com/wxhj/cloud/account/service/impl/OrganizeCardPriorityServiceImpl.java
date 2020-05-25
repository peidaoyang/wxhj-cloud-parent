package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.OrganizeCardPriorityDO;
import com.wxhj.cloud.account.mapper.OrganizeCardPriorityMapper;
import com.wxhj.cloud.account.service.OrganizeCardPriorityService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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
        return organizeCardPriorityMapper.selectByExample(example);
    }
}
