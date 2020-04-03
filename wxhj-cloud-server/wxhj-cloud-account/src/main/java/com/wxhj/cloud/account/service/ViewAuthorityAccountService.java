package com.wxhj.cloud.account.service;

import java.util.List;

import com.wxhj.cloud.account.domain.view.ViewAuthorityAccountDO;

public interface ViewAuthorityAccountService {
	List<ViewAuthorityAccountDO> list(String accountId);
}
