/**
 * 
 */
package com.wxhj.cloud.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.view.ViewRechargeSummaryDO;
import com.wxhj.cloud.account.mapper.view.ViewRechargeSummaryMapper;
import com.wxhj.cloud.account.service.ViewRechargeSummaryService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ViewRechargeSummaryServiceImpl.java
 * @author: cya
 * @Date: 2020年2月4日 下午1:31:45
 */
@Service
public class ViewRechargeSummaryServiceImpl implements ViewRechargeSummaryService {
	@Resource
	ViewRechargeSummaryMapper viewRechargeSummaryMapper;

	@Override
	public PageInfo<ViewRechargeSummaryDO> listByDatePage(IPageRequestModel pageRequestModel, String beginTime,
			String endTime) {
		Example example = new Example(ViewRechargeSummaryDO.class);
		example.createCriteria().andBetween("rechargeTime", beginTime, endTime);
		PageInfo<ViewRechargeSummaryDO> list = PageUtil.selectPageList(pageRequestModel,
				() -> viewRechargeSummaryMapper.selectByExample(example));
		return list;
	}
}
