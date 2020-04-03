package com.wxhj.cloud.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.account.domain.AccountInfoBakDO;
import com.wxhj.cloud.account.mapper.AccountInfoBakMapper;
import com.wxhj.cloud.account.service.AccountInfoBakService;
@Service
public class AccountInfoBakServiceImpl implements AccountInfoBakService {
	@Resource
	AccountInfoBakMapper accountInfoBakMapper;

	@Override
	public void insert(AccountInfoBakDO accountInfoBak) {
		accountInfoBakMapper.insert(accountInfoBak);
	}

}
