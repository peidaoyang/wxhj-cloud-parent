package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.AccountConsumeDO;

import java.util.Date;
import java.util.List;

public interface AccountConsumeService {
	void insertCascade(AccountConsumeDO accountConsume);

	void update(AccountConsumeDO accountConsume);

	void delete(String id);

	List<AccountConsumeDO> list(String organizeId, Date time);
}
