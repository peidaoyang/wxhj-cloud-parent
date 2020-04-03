/**
 * 
 */
package com.wxhj.cloud.business.service.shuttleBus;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.FlightInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @ClassName: FlightInfoService.java
 * @author: cya
 * @Date: 2020年2月5日 下午5:16:21 
 */
public interface FlightInfoService {
	PageInfo<FlightInfoDO> selectById(IPageRequestModel pageRequestModel,String organizeId,String nameValue);
	
	PageInfo<FlightInfoDO> selectByRoute(IPageRequestModel pageRequestModel,String organizeId,String nameValue);
	
	List<FlightInfoDO> selectByRoute(String routeNumber);
	
	FlightInfoDO select(String id);
	
	String insert(FlightInfoDO flightInfoDO);
	
	void update(FlightInfoDO flightInfoDO);
	
	void delete(List<String> id);
	
	
}
