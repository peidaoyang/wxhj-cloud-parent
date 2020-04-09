/** 
 * @fileName: CurrentAccountAuthorityServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:41:26 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.mapper.CurrentAccountAuthorityMapper;
import com.wxhj.cloud.business.service.CurrentAccountAuthorityService;
import tk.mybatis.mapper.entity.Example;

/**
 * @className CurrentAccountAuthorityServiceImpl.java
 * @author pjf
 * @date 2019年12月19日 下午4:41:26
 */
@Service
public class CurrentAccountAuthorityServiceImpl implements CurrentAccountAuthorityService {
	@Resource
	CurrentAccountAuthorityMapper currentAccountAuthorityMapper;

	@Override
	public List<CurrentAccountAuthorityDO> selectByAuthorityList(List<String> authorityList) {
		Example example = new Example(CurrentAccountAuthorityDO.class);
		example.createCriteria().andIn("authorityGroupId", authorityList);
		return currentAccountAuthorityMapper.selectByExample(example);

	}

	@Override
	public CurrentAccountAuthorityDO selectByAccountId(String accountId) {
		Example example = new Example(CurrentAccountAuthorityDO.class);
		example.createCriteria().andEqualTo("accountId", accountId);
		return currentAccountAuthorityMapper.selectByExample(example).get(0);
	}

	@Override
	public String insert(CurrentAccountAuthorityDO currentAccountAuthorityDO) {
		String id = UUID.randomUUID().toString();
		currentAccountAuthorityDO.setId(id);
		currentAccountAuthorityMapper.insert(currentAccountAuthorityDO);
		return id;
	}
	
	@Transactional
	@Override
	public void insertList(List<CurrentAccountAuthorityDO> listAccount) {
		listAccount.forEach(q -> {
			this.insert(q);
		});
	}
	
	@Override
	public void update(CurrentAccountAuthorityDO currentAccountAuthorityDO) {
		currentAccountAuthorityMapper.updateByPrimaryKeySelective(currentAccountAuthorityDO);
	}

	@Transactional
	@Override
	public void delete(String id) {
		Example example = new Example(CurrentAccountAuthorityDO.class);
		example.createCriteria().andEqualTo("authorityGroupId",id);
		currentAccountAuthorityMapper.deleteByExample(example);
	}


}
