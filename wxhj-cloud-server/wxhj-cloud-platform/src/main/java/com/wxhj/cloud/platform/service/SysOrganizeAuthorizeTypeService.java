package com.wxhj.cloud.platform.service;

import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeTypeDO;

import java.util.List;

public interface SysOrganizeAuthorizeTypeService {
    List<String> list(Integer type);
    void insert(SysOrganizeAuthorizeTypeDO sysOrganizeAuthorizeType);
    void delete(String moduleId);
}
