package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.AccountTypeDO;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDO> listByOrgType(Integer orgType);
}
