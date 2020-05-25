package com.wxhj.cloud.platform.service.impl;

import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeTypeDO;
import com.wxhj.cloud.platform.mapper.SysOrganizeAuthorizeTypeMapper;
import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeTypeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cya
 * @Date 2020/5/12
 * @Version V1.0
 **/
@Service
public class SysOrganizeAuthorizeTypeServiceImpl implements SysOrganizeAuthorizeTypeService {
    @Resource
    SysOrganizeAuthorizeTypeMapper sysOrganizeAuthorizeTypeMapper;


    @Override
    public List<String> list(Integer type) {
        Example example = new Example(SysOrganizeAuthorizeTypeDO.class);
        example.createCriteria().andEqualTo("type",type);
        return sysOrganizeAuthorizeTypeMapper.selectByExample(example).stream().map(q -> q.getModuleId()).collect(Collectors.toList());
    }

    @Override
    public void insert(SysOrganizeAuthorizeTypeDO sysOrganizeAuthorizeType) {
        sysOrganizeAuthorizeTypeMapper.insert(sysOrganizeAuthorizeType);
    }

    @Override
    public void delete(String moduleId) {
        Example example = new Example(SysOrganizeAuthorizeTypeDO.class);
        example.createCriteria().andEqualTo("moduleId",moduleId);
        sysOrganizeAuthorizeTypeMapper.deleteByExample(example);
    }
}
