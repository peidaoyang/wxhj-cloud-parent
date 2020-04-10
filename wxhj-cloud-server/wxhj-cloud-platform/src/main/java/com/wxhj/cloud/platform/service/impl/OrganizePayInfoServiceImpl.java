package com.wxhj.cloud.platform.service.impl;

import com.wxhj.cloud.platform.domain.OrganizePayInfoDO;
import com.wxhj.cloud.platform.mapper.OrganizePayInfoMapper;
import com.wxhj.cloud.platform.service.OrganizePayInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrganizePayInfoServiceImpl implements OrganizePayInfoService {
    @Resource
    OrganizePayInfoMapper organizePayInfoMapper;

    @Override
    public OrganizePayInfoDO select(String id) {
        return organizePayInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(OrganizePayInfoDO organizePayInfo) {
        organizePayInfoMapper.insert(organizePayInfo);
    }

    @Override
    public void update(OrganizePayInfoDO organizePayInfo) {
        organizePayInfoMapper.updateByPrimaryKeySelective(organizePayInfo);
    }
}
