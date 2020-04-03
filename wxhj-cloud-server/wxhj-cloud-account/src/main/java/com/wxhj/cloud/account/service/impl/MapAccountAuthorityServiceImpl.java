/** 
 * @fileName: MapAccountAuthorityServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月1日 上午8:43:28 
 */

package com.wxhj.cloud.account.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.mapper.MapAccountAuthorityMapper;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className MapAccountAuthorityServiceImpl.java
 * @author pjf
 * @date 2019年11月1日 上午8:43:28   
*/
/**
 * @className MapAccountAuthorityServiceImpl.java
 * @author pjf
 * @date 2019年11月1日 上午8:43:28
 */
@Service
public class MapAccountAuthorityServiceImpl implements MapAccountAuthorityService {
	@Resource
	MapAccountAuthorityMapper mapAccountAuthorityMapper;

	@Override
	@Transactional
	public String insertCascade(MapAccountAuthorityDO mapAccountAuthorityDO) {
		String id = UUID.randomUUID().toString();
		mapAccountAuthorityDO.setId(id);

		mapAccountAuthorityMapper.insert(mapAccountAuthorityDO);
		return id;
	}

	@Override
	@Transactional
	public int deleteCascade(String authorityGroupId, String accountId) {

		Example example = new Example(MapAccountAuthorityDO.class);
		Criteria criteria = example.createCriteria();
		if (Strings.isNullOrEmpty(authorityGroupId) && Strings.isNullOrEmpty(accountId)) {
			return 0;
		}
		if (!Strings.isNullOrEmpty(authorityGroupId)) {
			criteria.andEqualTo("authorityGroupId", authorityGroupId);
		}
		if (!Strings.isNullOrEmpty(accountId)) {
			criteria.andEqualTo("accountId", accountId);
		}
		return mapAccountAuthorityMapper.deleteByExample(example);
	}

	@Override
	public List<MapAccountAuthorityDO> list(MapAccountAuthorityDO mapAccountAuthority) {

		return mapAccountAuthorityMapper.select(mapAccountAuthority);
	}

	@Override
	public List<MapAccountAuthorityDO> listByAuthorityId(String authorityId) {
		Example example = new Example(MapAccountAuthorityDO.class);
		example.createCriteria().andEqualTo("authorityGroupId", authorityId);
		return mapAccountAuthorityMapper.selectByExample(example);

	}

	@Override
	public List<MapAccountAuthorityDO> listByAuthorityIdList(List<String> authorityIdList) {
		Example example = new Example(MapAccountAuthorityDO.class);
		example.createCriteria().andIn("authorityGroupId", authorityIdList);
		return mapAccountAuthorityMapper.selectByExample(example);
	}

	@Override
	public List<MapAccountAuthorityDO> listByAccountId(String accountId) {
		Example example = new Example(MapAccountAuthorityDO.class);
		example.createCriteria().andEqualTo("accountId", accountId);
		return mapAccountAuthorityMapper.selectByExample(example);
	}

	@Override
	public void update(String authorityId, List<String> accountIdList) {
		List<MapAccountAuthorityDO> newlistMap = accountIdList.stream()
				.map(q -> new MapAccountAuthorityDO(null, authorityId, q)).collect(Collectors.toList());
		MapAccountAuthorityDO mapAccountAuthority = new MapAccountAuthorityDO();
		mapAccountAuthority.setAuthorityGroupId(authorityId);
		List<MapAccountAuthorityDO> oldlistMap = this.list(mapAccountAuthority);
		List<MapAccountAuthorityDO> addlistMap = newlistMap.stream().filter(q -> !oldlistMap.contains(q))
				.collect(Collectors.toList());
		List<MapAccountAuthorityDO> dellistMap = oldlistMap.stream().filter(q -> !newlistMap.contains(q))
				.collect(Collectors.toList());
		addlistMap.forEach(q -> {
			this.insertCascade(q);
		});
		dellistMap.forEach(q -> {
			this.deleteCascade(q.getAuthorityGroupId(), q.getAccountId());
		});

	}

	@Override
	public List<MapAccountAuthorityDO> deleteByAccountId(String accountId) {
		List<MapAccountAuthorityDO> listByAccountId = this
				.listByAccountId(accountId);
		for (MapAccountAuthorityDO mapAccountAuthorityTemp : listByAccountId) {
			this.deleteCascade(mapAccountAuthorityTemp.getAuthorityGroupId(),
					mapAccountAuthorityTemp.getAccountId());
		}
		return listByAccountId;
	}

}
