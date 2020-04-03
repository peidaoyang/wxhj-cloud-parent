/** 
 * @fileName: ViewConsumeSummaryAccountService.java  
 * @author: pjf
 * @date: 2020年2月5日 下午1:49:57 
 */

package com.wxhj.cloud.account.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.view.ViewConsumeSummaryAccountDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className ViewConsumeSummaryAccountService.java
 * @author pjf
 * @date 2020年2月5日 下午1:49:57
 */

public interface ViewConsumeSummaryAccountService {

	PageInfo<ViewConsumeSummaryAccountDO> listPage(IPageRequestModel iPageRequestModel, String organizeId, String name,
			Date beginTime, Date endTime);

	PageInfo<ViewConsumeSummaryAccountDO> listPage(IPageRequestModel iPageRequestModel, String organizeId,
			String accountId, String name, Date beginTime, Date endTime);

	List<ViewConsumeSummaryAccountDO> list(String organizeId, String name, Date beginTime, Date endTime);
}
