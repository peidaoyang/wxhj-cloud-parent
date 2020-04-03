package com.wxhj.cloud.business.service.shuttleBus;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewRideSummaryDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;


/**
 * @ClassName: ViewRideTotalService.java
 * @author: cya
 * @Date: 2020年2月6日 下午2:42:42 
 */
public interface ViewRideSummaryService {
	PageInfo<ViewRideSummaryDO> select(IPageRequestModel pageRequestModel,String organizeId,
			String nameValue,String startTime,String endTime);
}
