package com.wxhj.cloud.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.mapper.AccountConsumeMapper;
import com.wxhj.cloud.account.service.AccountConsumeService;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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

	@Override
	public List<AccountConsumeDO> list(String organizeId, Date time) {
		Example example = new Example(AccountConsumeDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andGreaterThanOrEqualTo("consumeDate",time);
		return acccountConsumeMapper.selectByExample(example);
	}

}
