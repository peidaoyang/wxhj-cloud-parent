package com.wxhj.cloud.platform.service.impl;

import com.wxhj.cloud.platform.domain.OrganizePayInfoDO;
import com.wxhj.cloud.platform.mapper.OrganizePayInfoMapper;
import com.wxhj.cloud.platform.service.OrganizePayInfoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrganizePayInfoServiceImpl implements OrganizePayInfoService {
    @Resource
    OrganizePayInfoMapper organizePayInfoMapper;

    @Override
    @Cacheable(value = "organizePayInfo",key = "#id", unless = "#result eq null ")
    public OrganizePayInfoDO select(String id) {
        return organizePayInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    //@CacheEvict(value = "organizePayInfo",key = "#organizePayInfo.id")
    public void insert(OrganizePayInfoDO organizePayInfo) {
        organizePayInfoMapper.insert(organizePayInfo);
    }

    @Override
    @CacheEvict(value = "organizePayInfo",key = "#organizePayInfo.id")
    public void update(OrganizePayInfoDO organizePayInfo) {
        organizePayInfoMapper.updateByPrimaryKeySelective(organizePayInfo);
    }
}
