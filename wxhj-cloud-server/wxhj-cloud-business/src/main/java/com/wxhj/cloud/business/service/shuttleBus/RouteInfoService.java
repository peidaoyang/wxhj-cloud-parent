/**
 * 
 */
package com.wxhj.cloud.business.service.shuttleBus;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.RouteInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @ClassName: RouteInfoService.java
 * @author: cya
 * @Date: 2020年2月4日 下午3:30:16 
 */
public interface RouteInfoService {
	PageInfo<RouteInfoDO>  select(IPageRequestModel pageRequestModel,String organizeId,String nameValue,String type);
	
	List<RouteInfoDO> listByOrg(String organizeId);
	
	String insert(RouteInfoDO routeInfo);
	
	void update(RouteInfoDO routeInfoDO);
	
	void delete(List<String> routIdList);
}
