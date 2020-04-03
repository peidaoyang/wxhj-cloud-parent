/** 
 * @fileName: FaceChangeRecServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月21日 下午1:22:01 
 */

package com.wxhj.cloud.faceServer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.faceServer.domian.FaceChangeRecDO;
import com.wxhj.cloud.faceServer.mapper.FaceChangeRecMapper;
import com.wxhj.cloud.faceServer.service.FaceChangeRecService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className FaceChangeRecServiceImpl.java
 * @author pjf
 * @date 2019年11月21日 下午1:22:01
 */

@Service
public class FaceChangeRecServiceImpl implements FaceChangeRecService {

	@Resource
	FaceChangeRecMapper faceChangeRecMapper;

	@Override
	@Transactional
	public void insertListCascade(List<FaceChangeRecDO> faceChangeRecList) {
		for (FaceChangeRecDO faceChangeRecTemp : faceChangeRecList) {
			faceChangeRecMapper.insert(faceChangeRecTemp);
		}
	}

	@Override
	public Boolean existByMasterId(Long masterId) {
		Example example = new Example(FaceChangeRecDO.class);
		example.createCriteria().andEqualTo("masterId", masterId);
		int faceChangeRecCount = faceChangeRecMapper.selectCountByExample(example);
		return faceChangeRecCount > 0;
	}

	@Override
	public List<FaceChangeRecDO> listGreaterThanIndexAndId(String id, Long currentIndex) {
		Example example = new Example(FaceChangeRecDO.class);
		example.createCriteria().andEqualTo("id", id).andGreaterThan("currentIndex", currentIndex);
		example.setOrderByClause("current_index");
		return faceChangeRecMapper.selectByExample(example);
	}
}
