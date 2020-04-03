/** 
 * @fileName: SceneInfoServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月13日 上午10:35:05 
 */

package com.wxhj.cloud.platform.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.platform.domain.SceneInfoDO;
import com.wxhj.cloud.platform.mapper.SceneInfoMapper;
import com.wxhj.cloud.platform.service.SceneInfoService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className SceneInfoServiceImpl.java
 * @author pjf
 * @date 2019年11月13日 上午10:35:05   
*/
/**
 * @className SceneInfoServiceImpl.java
 * @author pjf
 * @date 2019年11月13日 上午10:35:05
 */
@Service
public class SceneInfoServiceImpl implements SceneInfoService {
	@Resource
	SceneInfoMapper sceneInfoMapper;

	@Override
	public void delete(String id) {
		sceneInfoMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<SceneInfoDO> select() {
		return sceneInfoMapper.selectAll();
	}
	
	@Override
	public void update(SceneInfoDO sceneInfo) {
		sceneInfoMapper.updateByPrimaryKeySelective(sceneInfo);
	}

	@Override
	public String insert(SceneInfoDO sceneInfo) {
		sceneInfo.setId(UUID.randomUUID().toString());
		sceneInfoMapper.insert(sceneInfo);
		return sceneInfo.getId();
	}

	@Override
	public List<SceneInfoDO> listByOrganizeId(String organizeId) {
		Example example = new Example(SceneInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);

		return sceneInfoMapper.selectByExample(example);
	}

	@Override
	public SceneInfoDO selectById(String id) {

		return sceneInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SceneInfoDO> listByIdList(List<String> sceneIdList) {
		Example example = new Example(SceneInfoDO.class);
		example.createCriteria().andIn("id", sceneIdList);
		return sceneInfoMapper.selectByExample(example);
	}

}
