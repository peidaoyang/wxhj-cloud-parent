package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.AccountRevokeDO;
import com.wxhj.cloud.account.mapper.AccountRevokeMapper;
import com.wxhj.cloud.account.service.AccountRevokeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Service
public class AccountRevokeServiceImpl implements AccountRevokeService {
    @Resource
    AccountRevokeMapper accountRevokeMapper;

    @Override
    public void insert(AccountRevokeDO accountRevoke) {
        accountRevoke.setCreatorTime(LocalDateTime.now());
        accountRevokeMapper.insert(accountRevoke);
    }
}
