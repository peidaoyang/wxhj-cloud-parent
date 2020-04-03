package com.wxhj.cloud.business.service.shuttleBus;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.DriverInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @ClassName: DriverInfoService.java
 * @author: cya
 * @Date: 2020年2月4日 下午5:34:43 
 */
public interface DriverInfoService {
	PageInfo<DriverInfoDO> select(IPageRequestModel pageRequestModel,String organizeId,String nameValue,String field);
//	PageInfo<DriverInfoDO> selectByAccountId(IPageRequestModel pageRequestModel,String organizeId,String nameValue);
//	PageInfo<DriverInfoDO> selectByName(IPageRequestModel pageRequestModel,String organizeId,String nameValue);
	
	DriverInfoDO select(String id);
	
	String insert(DriverInfoDO driverInfo,String userId);
	
	void update(DriverInfoDO driverInfo);
	
	void delete(List<String> jobIdList);
}
