package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;

import java.util.List;

public interface MapAccountAuthorityPlusService {

    void update(String authorityId, List<String> accountIdList);

    List<MapAccountAuthorityDO> deleteByAccountId(String accountId);

}
