package com.wxhj.cloud.platform.service.impl;

import javax.annotation.Resource;

import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.platform.domain.EnumManageDO;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.AnnouncementDO;
import com.wxhj.cloud.platform.mapper.AnnouncementMapper;
import com.wxhj.cloud.platform.service.AnnouncementServcie;

import tk.mybatis.mapper.entity.Example;

@Service
public class AnnouncementServcieImpl implements AnnouncementServcie{
	@Resource
	AnnouncementMapper announcementMapper;

	@Override
	public PageInfo<AnnouncementDO> select(IPageRequestModel pageRequestModel, String organizeId) {
		Example example = new Example(AnnouncementDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId);
		PageInfo<AnnouncementDO> announcementList = PageUtil.selectPageList(pageRequestModel, 
				()->announcementMapper.selectByExample(example));
		return announcementList;
	}

	@Override
	public PageInfo<AnnouncementDO> listPage(IPageRequestModel pageRequestModel, String organizeId,String nameValue) {
		Example example = new Example(AnnouncementDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andLike("title", "%"+nameValue+"%");
		PageInfo<AnnouncementDO> announcementList = PageUtil.selectPageList(pageRequestModel,
				()->announcementMapper.selectByExample(example));
		return announcementList;
	}

	@Override
	public AnnouncementDO select(String id) {
		return announcementMapper.selectByPrimaryKey(id);
	}

//	@Override
//	public AnnouncementDO select(String organizeId) {
//		Example example = new Example(AnnouncementDO.class);
//		example.setOrderByClause("creator_time desc LIMIT 1");
//		example.createCriteria().andEqualTo("organizeId",organizeId);
//		return announcementMapper.selectOneByExample(example);
//	}
	
	@Override
	public String insert(AnnouncementDO announcement,String userid) {
		announcement.create(userid);
		announcementMapper.insert(announcement);
		return announcement.getId();
	}

	@Override
	public void update(AnnouncementDO announcement) {
		announcementMapper.updateByPrimaryKeySelective(announcement);
	}

	@Override
	public void delete(String id) {
		announcementMapper.deleteByPrimaryKey(id);
	}
	
}
