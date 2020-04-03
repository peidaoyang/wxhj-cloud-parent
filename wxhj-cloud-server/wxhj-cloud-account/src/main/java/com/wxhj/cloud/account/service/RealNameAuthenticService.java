/**
 * @className RealNameAuthenticService.java
 * @admin jwl
 * @date 2019年12月12日 上午11:34:45
 */
package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.RealNameAuthenticDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @className RealNameAuthenticService.java
 * @admin jwl
 * @date 2019年12月12日 上午11:34:45
 */
public interface RealNameAuthenticService {

	void insert(RealNameAuthenticDO realNameAuthentic);
	
	void update(RealNameAuthenticDO realNameAuthentic);
	
	RealNameAuthenticDO selectByAccountId(String accountId);
	
	IPageResponseModel listRealNameAuthentic(
			IPageRequestModel pageRequestModel, String accountName);
}
