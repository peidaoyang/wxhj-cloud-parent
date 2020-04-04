package com.wxhj.cloud.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.account.domain.view.ViewSceneAuthorityDO;
import com.wxhj.cloud.account.mapper.view.ViewSceneAuthorityMapper;
import com.wxhj.cloud.account.service.ViewSceneAuthorityService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ViewSceneAuthorityServiceImpl implements ViewSceneAuthorityService{
	@Resource
	ViewSceneAuthorityMapper viewSceneAuthorityMapper;

	@Override
	public List<ViewSceneAuthorityDO> list(List<String> sceneId) {
		Example example = new Example(ViewSceneAuthorityDO.class);
		example.createCriteria().andIn("sceneId",sceneId);
		return viewSceneAuthorityMapper.selectByExample(example);
	}
	
	
}
