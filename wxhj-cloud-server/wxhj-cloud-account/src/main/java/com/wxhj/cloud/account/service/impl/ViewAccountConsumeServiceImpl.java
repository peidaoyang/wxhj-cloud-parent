/** 
 * @fileName: ViewAccountConsumeServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月5日 上午11:06:52 
 */

package com.wxhj.cloud.account.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.account.domain.view.ViewAccountConsumeDO;
import com.wxhj.cloud.account.mapper.view.ViewAccountConsumeMapper;
import com.wxhj.cloud.account.service.ViewAccountConsumeService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className ViewAccountConsumeServiceImpl.java
 * @author pjf
 * @date 2020年2月5日 上午11:06:52
 */

@Service
public class ViewAccountConsumeServiceImpl implements ViewAccountConsumeService {

	@Resource
	ViewAccountConsumeMapper viewAccountConsumeMapper;

	@Override
	public PageInfo<ViewAccountConsumeDO> listPage(IPageRequestModel iPageRequestModel, String organizeId, String name,
												   LocalDateTime beginTime, LocalDateTime endTime, Integer cardType) {
		Example example = new Example(ViewAccountConsumeDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId", organizeId)
				.andLike("accountName", "%" + name + "%")
				.andBetween("consumeDate",beginTime, endTime);
		if (cardType != null) {
			criteria.andEqualTo("cardType", cardType);
		}

		return PageUtil.selectPageList(iPageRequestModel, () -> viewAccountConsumeMapper.selectByExample(example));

	}


	@Override
	public List<ViewAccountConsumeDO> list(String organizeId, String name, LocalDateTime beginTime, LocalDateTime endTime) {
		Example example = new Example(ViewAccountConsumeDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId", organizeId)
				.andLike("accountName", "%" + name + "%")
				.andBetween("consumeDate",beginTime, endTime);

		return viewAccountConsumeMapper.selectByExample(example);

	}
	@Override
	public PageInfo<ViewAccountConsumeDO> listByTimeAndAccountPage(IPageRequestModel iPageRequestModel,
																   String accountId, LocalDateTime beginTime, LocalDateTime endTime) {
		Example example = new Example(ViewAccountConsumeDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("accountId", accountId).andBetween("consumeDate",
				beginTime, endTime);
		if (!Strings.isNullOrEmpty(accountId)) {
			criteria.andEqualTo("accountId", accountId);
		}
		return PageUtil.selectPageList(iPageRequestModel, () -> viewAccountConsumeMapper.selectByExample(example));

	}

}
