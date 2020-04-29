/** 
 * @fileName: ViewEntranceDataServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月6日 下午4:11:33 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.EntranceDataDO;
import com.wxhj.cloud.business.domain.view.ViewEntranceDataDO;
import com.wxhj.cloud.business.mapper.view.ViewEntranceDataMapper;
import com.wxhj.cloud.business.service.ViewEntranceDataService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className ViewEntranceDataServiceImpl.java
 * @author pjf
 * @date 2020年2月6日 下午4:11:33   
*/
/**
 * @className ViewEntranceDataServiceImpl.java
 * @author pjf
 * @date 2020年2月6日 下午4:11:33
 */
@Service
public class ViewEntranceDataServiceImpl implements ViewEntranceDataService {
	@Resource
	ViewEntranceDataMapper viewEntranceDataMapper;

	@Override
	public PageInfo<ViewEntranceDataDO> listPage(IPageRequestModel pageRequestModel,String organizeId,
			 Date beginTime, Date endTime,String accountName) {
		Example example = new Example(EntranceDataDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId", organizeId).andBetween("recordDatetime", beginTime, endTime);
		if (!Strings.isNullOrEmpty(accountName)) {
			criteria.andLike("accountName", "%"+accountName+"%");
		}
		return PageUtil.selectPageList(pageRequestModel, () -> viewEntranceDataMapper.selectByExample(example));
	}

	@Override
	public List<ViewEntranceDataDO> list(String accountName, String organizeId, Date beginTime, Date endTime) {
		Example example = new Example(EntranceDataDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId", organizeId).andBetween("recordDatetime", beginTime, endTime);
		if (!Strings.isNullOrEmpty(accountName)) {
			criteria.andLike("accountName", "%"+accountName+"%");
		}
		return viewEntranceDataMapper.selectByExample(example);
	}


}
