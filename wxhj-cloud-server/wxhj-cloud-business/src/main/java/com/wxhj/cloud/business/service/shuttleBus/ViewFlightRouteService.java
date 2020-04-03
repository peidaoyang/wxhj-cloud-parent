/**
 * 
 */
package com.wxhj.cloud.business.service.shuttleBus;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewFlightRouteDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @ClassName: ViewFlightRouteService.java
 * @author: cya
 * @Date: 2020年2月6日 下午6:12:41
 */
public interface ViewFlightRouteService {

	ViewFlightRouteDO select(String routeNumber, String carNumber, String organizeId, Integer minuteTime);

	IPageResponseModel listBySiteNamePage(IPageRequestModel pageRequestModel, String nameValue);
	
	PageInfo<ViewFlightRouteDO> listPage(IPageRequestModel pageRequestModel, String organizeId,String nameValue,
			String searchField) ;
}