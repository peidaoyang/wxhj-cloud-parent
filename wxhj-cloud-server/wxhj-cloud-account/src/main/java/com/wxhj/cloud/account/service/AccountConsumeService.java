package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.AccountConsumeDO;

public interface AccountConsumeService {
	void insertCascade(AccountConsumeDO accountConsume);

	void update(AccountConsumeDO accountConsume);

	void delete(String id);

	// List<AccountConsumeDO> listScmmary(String accountId, String organizeId,
	// String deviceId, Date beginTime, Date endTime);
	// List<AccountConsumeDO> select(String accountId, Date beginTime, Date
	// endTime);
}
