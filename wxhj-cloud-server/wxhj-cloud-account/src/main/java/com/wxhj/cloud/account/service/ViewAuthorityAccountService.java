package com.wxhj.cloud.account.service;

import java.util.List;

import com.wxhj.cloud.account.domain.view.ViewAuthorityAccountDO;

public interface ViewAuthorityAccountService {
	List<ViewAuthorityAccountDO> list(String accountId);

	/**
	 * 同一个组织下一个人员只能有一个考勤规则
	 * @param accountId
	 * @param organizeId
	 * @return
	 */
	int oneAttendanceByAccountAndOrg(String accountId,String organizeId);

}
