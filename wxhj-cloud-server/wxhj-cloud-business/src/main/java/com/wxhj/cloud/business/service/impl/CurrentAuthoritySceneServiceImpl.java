/** 
 * @fileName: CurrentAuthoritySceneService.java  
 * @author: pjf
 * @date: 2019年12月20日 上午9:19:31 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.domain.CurrentAuthoritySceneDO;
import com.wxhj.cloud.business.mapper.CurrentAuthoritySceneMapper;
import com.wxhj.cloud.business.service.CurrentAuthoritySceneService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className CurrentAuthoritySceneService.java
 * @author pjf
 * @date 2019年12月20日 上午9:19:31   
*/
/**
 * @className CurrentAuthoritySceneService.java
 * @author pjf
 * @date 2019年12月20日 上午9:19:31
 */
@Service
public class CurrentAuthoritySceneServiceImpl implements CurrentAuthoritySceneService {
	@Resource
	private CurrentAuthoritySceneMapper currentAuthoritySceneMapper;

	@Override
	public List<CurrentAuthoritySceneDO> selectBySceneId(String sceneId) {
		Example example = new Example(CurrentAuthoritySceneDO.class);
		example.createCriteria().andEqualTo("sceneId", sceneId);
		return currentAuthoritySceneMapper.selectByExample(example);
	}

	@Override
	public String insert(CurrentAuthoritySceneDO currentAuthoritySceneDO) {
		String id = UUID.randomUUID().toString();
		currentAuthoritySceneDO.setId(id);
		currentAuthoritySceneMapper.insert(currentAuthoritySceneDO);
		return id;
	}

	@Transactional
	@Override
	public void insertListCascade(List<CurrentAuthoritySceneDO> currentAuthoritySceneDO,
			List<CurrentAccountAuthorityDO> listAccountAuthority, String attendanceId) {
		currentAuthoritySceneDO.forEach(q -> this.insert(q));
	}

	// String authorityId
	@Override
	public void update(CurrentAuthoritySceneDO currentAuthoritySceneDO) {
		currentAuthoritySceneMapper.updateByPrimaryKeySelective(currentAuthoritySceneDO);
	}

	@Transactional
	@Override
	public void deleteCascade(String id) {
		Example example = new Example(CurrentAuthoritySceneDO.class);
		example.createCriteria().andEqualTo("authorityGroupId", id);
		currentAuthoritySceneMapper.deleteByExample(example);
	}

}
