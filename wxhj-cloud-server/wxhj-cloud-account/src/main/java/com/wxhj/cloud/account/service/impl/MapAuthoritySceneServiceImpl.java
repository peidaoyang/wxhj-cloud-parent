/** 
 * @fileName: MapAuthoritySceneServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月1日 上午11:01:19 
 */

package com.wxhj.cloud.account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.wxhj.cloud.account.domain.MapAuthoritySceneDO;
import com.wxhj.cloud.account.mapper.MapAuthoritySceneMapper;
import com.wxhj.cloud.account.service.MapAuthoritySceneService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className MapAuthoritySceneServiceImpl.java
 * @author pjf
 * @date 2019年11月1日 上午11:01:19
 */
@Service
public class MapAuthoritySceneServiceImpl implements MapAuthoritySceneService {
	@Resource
	MapAuthoritySceneMapper mapAuthoritySceneMapper;

	@Override
	@Transactional
	public String insertCascade(MapAuthoritySceneDO mapAuthoritySceneDO) {
		String id = UUID.randomUUID().toString();
		mapAuthoritySceneDO.setId(id);
		mapAuthoritySceneMapper.insert(mapAuthoritySceneDO);
		return id;
	}

	@Override
	@Transactional
	public int deleteCascade(String authorityGroupId, String sceneId) {
		Example example = new Example(MapAuthoritySceneDO.class);
		Criteria criteria = example.createCriteria();
		if (Strings.isNullOrEmpty(authorityGroupId) && Strings.isNullOrEmpty(sceneId)) {
			return 0;
		}
		if (!Strings.isNullOrEmpty(authorityGroupId)) {
			criteria.andEqualTo("authorityGroupId", authorityGroupId);
		}
		if (!Strings.isNullOrEmpty(sceneId)) {
			criteria.andEqualTo("sceneId", sceneId);
		}

		example.and(criteria);
		return mapAuthoritySceneMapper.deleteByExample(example);
	}

	@Override
	public List<MapAuthoritySceneDO> list(MapAuthoritySceneDO mapAuthoritySceneDO) {
		return mapAuthoritySceneMapper.select(mapAuthoritySceneDO);
	}

	@Override
	public List<MapAuthoritySceneDO> listByAuthorityId(String authorityId) {
		Example example = new Example(MapAuthoritySceneDO.class);
		example.createCriteria().andEqualTo("authorityGroupId", authorityId);
		return mapAuthoritySceneMapper.selectByExample(example);

	}

	@Override
	public void update(Integer submitType, List<String> mainId, String assistId) {
		List<MapAuthoritySceneDO> addMapAuthoritySceneList = new ArrayList<>();
		List<MapAuthoritySceneDO> deleteMapAuthoritySceneList = new ArrayList<>();

		List<MapAuthoritySceneDO> newMapAuthoritySceneList = new ArrayList<>();
		List<MapAuthoritySceneDO> oldMapAuthoritySceneList = new ArrayList<>();
		//
		MapAuthoritySceneDO mapAuthorityScene = new MapAuthoritySceneDO();
		if (submitType.equals(0)) {
			mapAuthorityScene.setSceneId(assistId);
			newMapAuthoritySceneList = mainId.stream().map(q -> new MapAuthoritySceneDO(null, q, assistId))
					.collect(Collectors.toList());
		} else {
			mapAuthorityScene.setAuthorityGroupId(assistId);
			newMapAuthoritySceneList = mainId.stream()
					.map(q -> new MapAuthoritySceneDO(null, assistId, q))
					.collect(Collectors.toList());
		}

		//
		oldMapAuthoritySceneList = this.list(mapAuthorityScene);
		SetView<MapAuthoritySceneDO> differenceSetTemp = Sets.difference(Sets.newHashSet(newMapAuthoritySceneList),
				Sets.newHashSet(oldMapAuthoritySceneList));
		addMapAuthoritySceneList = Lists.newArrayList(differenceSetTemp);
		differenceSetTemp = Sets.difference(Sets.newHashSet(oldMapAuthoritySceneList),
				Sets.newHashSet(newMapAuthoritySceneList));
		deleteMapAuthoritySceneList = Lists.newArrayList(differenceSetTemp);
		addMapAuthoritySceneList.forEach(q -> {
			this.insertCascade(q);
		});
		deleteMapAuthoritySceneList.forEach(q -> {
			this.deleteCascade(q.getAuthorityGroupId(), q.getSceneId());
		});

	}
}
