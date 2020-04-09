package com.wxhj.cloud.platform.service;

import com.wxhj.cloud.platform.domain.OrganizePayInfoDO;

public interface OrganizePayInfoService {
    OrganizePayInfoDO select(String id);

    void insert(OrganizePayInfoDO organizePayInfo);

    void update(OrganizePayInfoDO organizePayInfo);
}
