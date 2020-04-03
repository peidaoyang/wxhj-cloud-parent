package com.wxhj.cloud.platform.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;
import com.wxhj.cloud.platform.mapper.MapOrganizeUserMapper;
import com.wxhj.cloud.platform.service.MapOrganizeUserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class MapOrganizeServiceImpl implements MapOrganizeUserService {
	@Resource
	MapOrganizeUserMapper mapOrganizeUserMapper;

	@Override
	public void insert(MapOrganizeUserDO mapOrganizeUser) {
		String id = UUID.randomUUID().toString();
		mapOrganizeUser.setId(id);
		mapOrganizeUserMapper.insert(mapOrganizeUser);
	}

	@Transactional
	@Override
	public void insert(List<MapOrganizeUserDO> mapOrganizeList) {
		for (MapOrganizeUserDO mapOrganizeUser : mapOrganizeList) {
			this.insert(mapOrganizeUser);
		}
	}

	@Override
	public void deleteById(List<String> idList) {
		Example example = new Example(MapOrganizeUserDO.class);
		example.createCriteria().andIn("id", idList);
		mapOrganizeUserMapper.deleteByExample(example);
	}
	@Override
	public void deleteByUserId(String userId) {
		Example example = new Example(MapOrganizeUserDO.class);
		example.createCriteria().andEqualTo("userId", userId);
		mapOrganizeUserMapper.deleteByExample(example);
	}


	@Override
	public void deleteByOrganizeId(String organizeId) {
		Example example = new Example(MapOrganizeUserDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		mapOrganizeUserMapper.deleteByExample(example);
	}
}
