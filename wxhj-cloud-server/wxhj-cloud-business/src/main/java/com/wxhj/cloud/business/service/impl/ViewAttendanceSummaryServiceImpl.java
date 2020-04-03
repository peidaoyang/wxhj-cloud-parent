/**
 * @className ViewAttendanceSummaryServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 上午8:48:12
 */
package com.wxhj.cloud.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryDO;
import com.wxhj.cloud.business.mapper.view.ViewAttendanceSummaryMapper;
import com.wxhj.cloud.business.service.ViewAttendanceSummaryService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className ViewAttendanceSummaryServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 上午8:48:12
 */
@Service
public class ViewAttendanceSummaryServiceImpl implements ViewAttendanceSummaryService {

	@Resource
	ViewAttendanceSummaryMapper viewAttendanceSummaryMapper;

	@Override
	public PageInfo<ViewAttendanceSummaryDO> listPage(IPageRequestModel pageRequestModel, Date beginTime, Date endTime,
			String name, String organizeId) {
		Example example = new Example(ViewAttendanceSummaryDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo( "organizeId",organizeId).andBetween("datetime", beginTime, endTime);
		if (Strings.isNullOrEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		return PageUtil.selectPageList(pageRequestModel, () -> viewAttendanceSummaryMapper.selectByExample(example));
	}

	@Override
	public PageInfo<ViewAttendanceSummaryDO> listByAccountPage(IPageRequestModel pageRequestModel, Date beginTime,
			Date endTime, String accountId) {
		Example example = new Example(ViewAttendanceSummaryDO.class);
		example.createCriteria().andEqualTo("accountId", accountId).andBetween("datetime", beginTime, endTime);
		return PageUtil.selectPageList(pageRequestModel, () -> viewAttendanceSummaryMapper.selectByExample(example));
	}

	@Override
	public List<ViewAttendanceSummaryDO> list(Date beginTime, Date endTime, String name, String organizeId) {
		Example example = new Example(ViewAttendanceSummaryDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId", organizeId).andBetween("datetime", beginTime, endTime);
		if (Strings.isNullOrEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		return  viewAttendanceSummaryMapper.selectByExample(example);
	}

}
