package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import com.wxhj.cloud.account.domain.AccountConsumeDO;


import java.time.LocalDateTime;
import java.util.List;

public interface AccountConsumeService {
	void insertCascade(AccountConsumeDO accountConsume, AccountCardInfoDO accountCardInfo);

	void update(AccountConsumeDO accountConsume);

	void delete(String id);

	List<AccountConsumeDO> list(String organizeId, LocalDateTime time);

	void revoke(String id,Integer isRevoke);
}
