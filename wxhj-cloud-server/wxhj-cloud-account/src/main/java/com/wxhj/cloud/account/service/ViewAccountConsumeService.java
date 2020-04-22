/** 
 * @fileName: ViewAccountConsumeService.java  
 * @author: pjf
 * @date: 2020年2月5日 上午11:06:19 
 */

package com.wxhj.cloud.account.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.view.ViewAccountConsumeDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className ViewAccountConsumeService.java
 * @author pjf
 * @date 2020年2月5日 上午11:06:19
 */

public interface ViewAccountConsumeService {
	PageInfo<ViewAccountConsumeDO> listPage(IPageRequestModel iPageRequestModel, String organizeId, String name,
			Date beginTime, Date endTime);

//	PageInfo<ViewAccountConsumeDO> listPage(IPageRequestModel iPageRequestModel, String organizeId, String accountId,
//			String name, Date beginTime, Date endTime);
	
	
	PageInfo<ViewAccountConsumeDO> listByTimeAndAccountPage(IPageRequestModel iPageRequestModel, String accountId,
			 Date beginTime, Date endTime);


	List<ViewAccountConsumeDO> list(String organizeId, String name, Date beginTime, Date endTime);

}
