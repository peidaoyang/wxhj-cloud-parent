package com.wxhj.cloud.business.service.impl.shuttleBus;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewRideSummaryDO;
import com.wxhj.cloud.business.mapper.view.ViewRideSummaryMapper;
import com.wxhj.cloud.business.service.shuttleBus.ViewRideSummaryService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ViewRideTotalServiceImpl.java
 * @author: cya
 * @Date: 2020年2月6日 下午2:43:45 
 */
@Service
public class ViewRideSummaryServiceImpl implements ViewRideSummaryService{
	@Resource
	ViewRideSummaryMapper viewRideSummaryMapper;


	@Override
	public PageInfo<ViewRideSummaryDO> select(IPageRequestModel pageRequestModel, String organizeId, String nameValue,
			String startTime, String endTime) {
		Example example = new Example(ViewRideSummaryDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andBetween("rideTime", startTime, endTime)
			.andLike("flightId", "%" + nameValue + "%");
		PageInfo<ViewRideSummaryDO> pageData = PageUtil.selectPageList(pageRequestModel,
				() -> viewRideSummaryMapper.selectByExample(example));
		return pageData;
	}
	
	
}
