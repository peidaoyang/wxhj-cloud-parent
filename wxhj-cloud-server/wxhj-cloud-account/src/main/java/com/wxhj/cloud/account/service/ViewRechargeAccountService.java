package com.wxhj.cloud.account.service;

import java.util.Date;
import java.util.List;

import com.wxhj.cloud.account.domain.view.ViewRechargeAccountDO;

public interface ViewRechargeAccountService {
	List<ViewRechargeAccountDO> select(
			String nameValue, Integer type, Integer payType, 
			Date startTime, Date endTime,
			String organizeId);
}
