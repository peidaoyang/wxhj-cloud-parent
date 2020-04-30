package com.wxhj.cloud.account.service;

import java.util.List;

import com.wxhj.cloud.account.domain.view.ViewAuthorityAccountDO;

public interface ViewAuthorityAccountService {
	List<ViewAuthorityAccountDO> list(String accountId);

	/**
	 * 一个人员只能有一个考勤规则
	 * @param authorityIdList
	 * @param accountId
	 * @param organizeId
	 * @return
	 */
	boolean oneAttendanceByAccountAndOrg(List<String> authorityIdList,String accountId,String organizeId);

}
