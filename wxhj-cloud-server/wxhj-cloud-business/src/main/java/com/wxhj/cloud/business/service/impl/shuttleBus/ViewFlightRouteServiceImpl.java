/**
 * 
 */
package com.wxhj.cloud.business.service.impl.shuttleBus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewFlightRouteDO;
import com.wxhj.cloud.business.mapper.view.ViewFlightRouteMapper;
import com.wxhj.cloud.business.service.shuttleBus.ViewFlightRouteService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ViewFlightRouteServiceImpl.java
 * @author: cya
 * @Date: 2020年2月6日 下午6:13:33
 */
@Service
public class ViewFlightRouteServiceImpl implements ViewFlightRouteService {
	@Resource
	ViewFlightRouteMapper viewFlightRouteMapper;

	@Override
	public ViewFlightRouteDO select(String routeNumber, String carNumber, String organizeId, Integer minuteTime) {
		Example example = new Example(ViewFlightRouteDO.class);
		example.createCriteria().andEqualTo("routeNumber", routeNumber).andEqualTo("carNumber", carNumber)
				.andEqualTo("organizeId", organizeId).andLessThanOrEqualTo("startTime", minuteTime)
				.andGreaterThanOrEqualTo("endTime", minuteTime);
		List<ViewFlightRouteDO> selectByExample = viewFlightRouteMapper.selectByExample(example);
		return selectByExample.size() > 0 ? selectByExample.get(0) : null;
	}

	@Override
	public IPageResponseModel listBySiteNamePage(IPageRequestModel pageRequestModel, String nameValue) {
		Example example = new Example(ViewFlightRouteDO.class);
		example.createCriteria().andLike("startSite", "%" + nameValue + "%").andLike("endSite", "%" + nameValue + "%")
			.andLike("channelSite", "%" + nameValue + "%");
		PageInfo<ViewFlightRouteDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
				() -> viewFlightRouteMapper.selectByExample(example));
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();		
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageAttendanceData,
				 pageDefResponseModel, ViewFlightRouteDO.class);
		return pageDefResponseModel;
	}
	
	
	@Override
	public PageInfo<ViewFlightRouteDO> listPage(IPageRequestModel pageRequestModel, String organizeId,String nameValue,
			String searchField) {
		Example example = new Example(ViewFlightRouteDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andLike(searchField, "%" + nameValue + "%");
		PageInfo<ViewFlightRouteDO> pageData = PageUtil.selectPageList(pageRequestModel,
				() -> viewFlightRouteMapper.selectByExample(example));
		return pageData;
	}
}
