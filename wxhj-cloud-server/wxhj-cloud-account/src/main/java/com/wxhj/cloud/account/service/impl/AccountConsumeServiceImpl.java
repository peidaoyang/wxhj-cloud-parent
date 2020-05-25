package com.wxhj.cloud.account.service.impl;

import javax.annotation.Resource;

import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.mapper.AccountConsumeMapper;
import com.wxhj.cloud.account.service.AccountConsumeService;
import tk.mybatis.mapper.entity.Example;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountConsumeServiceImpl implements AccountConsumeService {

	@Resource
	AccountConsumeMapper acccountConsumeMapper;

	@Override
	@Transactional
	public void insertCascade(AccountConsumeDO accountConsume, AccountCardInfoDO accountCardInfo) {
		accountConsume.setConsumeId(accountConsume.getOrderNumber());
		accountConsume.initialization();
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

	@Override
	public List<AccountConsumeDO> list(String organizeId, LocalDateTime time) {
		Example example = new Example(AccountConsumeDO.class);
		example.createCriteria()
				.andEqualTo("organizeId",organizeId)
				.andGreaterThanOrEqualTo("consumeDate",time);
		return acccountConsumeMapper.selectByExample(example);
	}

	@Override
	public void revoke(String id, Integer isRevoke) {
		AccountConsumeDO accountConsume = new AccountConsumeDO();
		accountConsume.setConsumeId(id);
		accountConsume.setIsRevoke(1);
		update(accountConsume);
	}

}
