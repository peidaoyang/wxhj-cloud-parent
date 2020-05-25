package com.wxhj.cloud.business.service.visitor;


import java.time.LocalDateTime;
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

	PageInfo<VisitorInfoDO> listPage(IPageRequestModel pageRequestModel,List<String> organizeId,
									 String nameValue,Integer isCheck,String field);

	IPageResponseModel listPage(IPageRequestModel pageRequestModel, String nameValue, String field, Integer isCheck,
								LocalDateTime startTime, LocalDateTime endTime);

	
	IPageResponseModel select(IPageRequestModel pageRequestModel,String accountId,LocalDateTime startTime,LocalDateTime endTime);
	
	String insert(VisitorInfoDO visitorInfo);

	void update(VisitorInfoDO visitorInfo);

	void check(String id, Integer isCheck,String sceneId);
	
	List<VisitorInfoDO> selectByIdNumberAndSceneId(String isNumber,String sceneId,LocalDateTime dateTime);
	
	void delete(List<String> idList);
}
