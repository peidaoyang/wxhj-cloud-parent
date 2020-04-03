package com.wxhj.cloud.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.account.domain.view.ViewAuthorityAccountDO;
import com.wxhj.cloud.account.mapper.view.ViewAuthorityAccountMapper;
import com.wxhj.cloud.account.service.ViewAuthorityAccountService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ViewAuthorityAccountServiceImpl implements ViewAuthorityAccountService{
	@Resource
	ViewAuthorityAccountMapper viewAuthorityAccountMapper;

	@Override
	public List<ViewAuthorityAccountDO> list(String accountId) {
		Example example = new Example(ViewAuthorityAccountDO.class);
		example.createCriteria().andEqualTo("accountId",accountId);
		return viewAuthorityAccountMapper.selectByExample(example);
	}
	
}
