/**
 * @className RealNameAuthenticServiceImpl.java
 * @admin jwl
 * @date 2019年12月12日 上午11:37:13
 */
package com.wxhj.cloud.account.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.account.domain.RealNameAuthenticDO;
import com.wxhj.cloud.account.mapper.RealNameAuthenticMapper;
import com.wxhj.cloud.account.service.RealNameAuthenticService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className RealNameAuthenticServiceImpl.java
 * @admin jwl
 * @date 2019年12月12日 上午11:37:13
 */
@Service
public class RealNameAuthenticServiceImpl implements RealNameAuthenticService {
	
	@Resource
	RealNameAuthenticMapper realNameAuthenticMapper;
	
	@Override
	public void insert(RealNameAuthenticDO realNameAuthentic) {
		realNameAuthenticMapper.insert(realNameAuthentic);
	}

	@Override
	public void update(RealNameAuthenticDO realNameAuthentic) {
		realNameAuthentic.setApproveTime(new Date());
		realNameAuthenticMapper.updateByPrimaryKeySelective(realNameAuthentic);
	}

	@Override
	public RealNameAuthenticDO selectByAccountId(String accountId) {
		return realNameAuthenticMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public IPageResponseModel listRealNameAuthentic(IPageRequestModel pageRequestModel, String accountName) {
		Example example = new Example(RealNameAuthenticDO.class);
		if(!Strings.isNullOrEmpty(accountName)) {
			example.createCriteria().andLike("accountName", "%"+accountName+"%");
		}
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		PageInfo<RealNameAuthenticDO> page = PageUtil.selectPageList(pageRequestModel,
				() -> realNameAuthenticMapper.selectByExample(example));
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(page,
				pageDefResponseModel, RealNameAuthenticDO.class);
		return pageDefResponseModel;
	}

}
