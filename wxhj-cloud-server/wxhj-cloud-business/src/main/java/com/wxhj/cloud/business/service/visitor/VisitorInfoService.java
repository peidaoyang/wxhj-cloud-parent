package com.wxhj.cloud.business.service.visitor;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.VisitorInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @ClassName: visitorInfoService.java
 * @author: cya
 * @Date: 2020年2月11日 下午3:26:54
 */
public interface VisitorInfoService {
	PageInfo<VisitorInfoDO> listPage(IPageRequestModel pageRequestModel,String organizeId,
			String nameValue,Integer isCheck,String field);
	
//	PageInfo<VisitorInfoDO> selectByNameAndIscheck(IPageRequestModel pageRequestModel,String organizeId,
//			String nameValue,Integer isCheck);
//	
//	PageInfo<VisitorInfoDO> selectByAccoutIdAndIscheck(IPageRequestModel pageRequestModel,String organizeId,
//			String nameValue,Integer isCheck);
	
	IPageResponseModel select(IPageRequestModel pageRequestModel,String accountId,Date startTime,Date endTime);
	
	String insert(VisitorInfoDO visitorInfo);

	void update(VisitorInfoDO visitorInfo);

	void check(String id, Integer isCheck);
	
	List<VisitorInfoDO> selectByIdNumberAndSceneId(String isNumber,String sceneId,Date dateTime);
	
	void delete(List<String> idList);
}
