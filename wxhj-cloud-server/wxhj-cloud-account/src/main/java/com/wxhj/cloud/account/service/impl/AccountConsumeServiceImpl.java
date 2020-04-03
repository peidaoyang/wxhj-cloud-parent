package com.wxhj.cloud.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.mapper.AccountConsumeMapper;
import com.wxhj.cloud.account.service.AccountConsumeService;

@Service
public class AccountConsumeServiceImpl implements AccountConsumeService {

	@Resource
	AccountConsumeMapper acccountConsumeMapper;

	@Override
	@Transactional
	public void insertCascade(AccountConsumeDO accountConsume) {
		accountConsume.setConsumeId(accountConsume.getOrderNumber());
		acccountConsumeMapper.insert(accountConsume);
	}

	@Override
	public void update(AccountConsumeDO accountConsume) {
		acccountConsumeMapper.updateByPrimaryKeySelective(accountConsume);
	}

	@Override
	public void delete(String id) {
		acccountConsumeMapper.deleteByPrimaryKey(id);
	}

}
