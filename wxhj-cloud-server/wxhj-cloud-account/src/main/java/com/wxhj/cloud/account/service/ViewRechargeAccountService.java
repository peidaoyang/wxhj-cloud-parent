package com.wxhj.cloud.account.service;


import java.time.LocalDateTime;
import java.util.List;

import com.wxhj.cloud.account.domain.view.ViewRechargeAccountDO;

public interface ViewRechargeAccountService {
	List<ViewRechargeAccountDO> select(
			String nameValue, Integer type, Integer payType,
			LocalDateTime startTime, LocalDateTime endTime,
			String organizeId);
}
