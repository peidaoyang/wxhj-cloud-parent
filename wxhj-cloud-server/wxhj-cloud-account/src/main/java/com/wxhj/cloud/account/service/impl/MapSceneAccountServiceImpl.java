/** 
 * @fileName: MapSceneAccountServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月27日 下午1:41:10 
 */

package com.wxhj.cloud.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.account.domain.MapSceneAccountDO;
import com.wxhj.cloud.account.mapper.MapSceneAccountMapper;
import com.wxhj.cloud.account.service.MapSceneAccountService;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @className MapSceneAccountServiceImpl.java
 * @author pjf
 * @date 2019年11月27日 下午1:41:10
 */

@Service
public class MapSceneAccountServiceImpl implements MapSceneAccountService {

	@Resource
	MapSceneAccountMapper mapSceneAccountMapper;

	@Override
	public MapSceneAccountDO selectBySceneIdAndAccountId(String sceneId, String accountId) {
		Example example = new Example(MapSceneAccountDO.class);
		example.createCriteria().andEqualTo("sceneId", sceneId).andEqualTo("accountId", accountId);
		MapSceneAccountDO mapSceneAccount = mapSceneAccountMapper.selectOneByExample(example);
		if (mapSceneAccount == null) {
			mapSceneAccount = new MapSceneAccountDO();
			mapSceneAccount.initialization();
			mapSceneAccount.setSceneId(sceneId);
			mapSceneAccount.setAccountId(accountId);
			mapSceneAccountMapper.insert(mapSceneAccount);
		}
		return mapSceneAccount;
	}

	@Override
	public void addTotalCount(String id) {
		mapSceneAccountMapper.addTotalCount(id);

	}

	@Override
	public List<MapSceneAccountDO> listBySceneId(String sceneId) {
		Example example = new Example(MapSceneAccountDO.class);
		example.createCriteria().andEqualTo("sceneId", sceneId)
				.andGreaterThan("totalCount", 0);
		return mapSceneAccountMapper.selectByExample(example);
	}

	@Override
	public void subtractTotalCount(String id) {
		mapSceneAccountMapper.subtractTotalCount(id);
	}
}
