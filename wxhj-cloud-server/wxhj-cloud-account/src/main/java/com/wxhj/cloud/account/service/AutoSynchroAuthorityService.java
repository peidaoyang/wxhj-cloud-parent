package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.AutoSynchroAuthorityDO;


public interface AutoSynchroAuthorityService {
	void insert(AutoSynchroAuthorityDO autoSynchroAuthority);
	void update(AutoSynchroAuthorityDO autoSynchroAuthority);
	void delete(String autoSynchroAuthorityId);
}
