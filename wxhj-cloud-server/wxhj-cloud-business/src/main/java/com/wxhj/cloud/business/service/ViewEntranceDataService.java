/** 
 * @fileName: ViewEntranceDataService.java  
 * @author: pjf
 * @date: 2020年2月6日 下午4:10:39 
 */

package com.wxhj.cloud.business.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewEntranceDataDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className ViewEntranceDataService.java
 * @author pjf
 * @date 2020年2月6日 下午4:10:39   
*/
/**
 * @className ViewEntranceDataService.java
 * @author pjf
 * @date 2020年2月6日 下午4:10:39
 */

public interface ViewEntranceDataService {
	PageInfo<ViewEntranceDataDO> listPage(IPageRequestModel pageRequestModel, String organizeId,
			Date beginTime, Date endTime,String accountName);
	
	List<ViewEntranceDataDO> list( String accountName, String organizeId,Date beginTime, Date endTime);
}
