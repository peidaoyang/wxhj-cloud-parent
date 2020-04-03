package com.wxhj.cloud.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.account.domain.AutoSynchroAuthorityDO;
import com.wxhj.cloud.account.mapper.AutoSynchroAuthorityMapper;
import com.wxhj.cloud.account.service.AutoSynchroAuthorityService;

@Service
public class AutoSynchroAuthorityServiceImpl implements AutoSynchroAuthorityService{
	@Resource
	AutoSynchroAuthorityMapper autoSynchroAuthorityMapper;

	@Override
	public void insert(AutoSynchroAuthorityDO autoSynchroAuthority) {
		autoSynchroAuthorityMapper.insert(autoSynchroAuthority);
	}

	@Override
	public void delete(String autoSynchroAuthorityId) {
		autoSynchroAuthorityMapper.deleteByPrimaryKey(autoSynchroAuthorityId);
	}
	
}
