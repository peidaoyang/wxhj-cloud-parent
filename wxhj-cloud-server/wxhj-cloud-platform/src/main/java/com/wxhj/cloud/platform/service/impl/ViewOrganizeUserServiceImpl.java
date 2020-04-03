/** 
 * @fileName: ViewOrganizeUserServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月12日 下午1:18:39 
 */

package com.wxhj.cloud.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.platform.domain.view.ViewOrganizeUserDO;
import com.wxhj.cloud.platform.mapper.view.ViewOrganizeUserMapper;
import com.wxhj.cloud.platform.service.ViewOrganizeUserService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className ViewOrganizeUserServiceImpl.java
 * @author pjf
 * @date 2019年11月12日 下午1:18:39   
*/
/**
 * @className ViewOrganizeUserServiceImpl.java
 * @author pjf
 * @date 2019年11月12日 下午1:18:39
 */
@Service
public class ViewOrganizeUserServiceImpl implements ViewOrganizeUserService {
	@Resource
	ViewOrganizeUserMapper viewOrganizeUserMapper;

	@Override
	public List<ViewOrganizeUserDO> listByUserId(String userId) {
		Example example = new Example(ViewOrganizeUserDO.class);
		example.createCriteria().andEqualTo("userId", userId);
		return viewOrganizeUserMapper.selectByExample(example);
	}

	@Override
	public List<ViewOrganizeUserDO> listByUserIdAndOrgId(String userId, String organizeId) {
		Example example = new Example(ViewOrganizeUserDO.class);
		example.createCriteria().andEqualTo("userId", userId).andEqualTo("organizeId", organizeId);

		return viewOrganizeUserMapper.selectByExample(example);
		
	}

	@Override
	public List<ViewOrganizeUserDO> listByUserIdByOrganizeName(String userId, String organizeName) {
		Example example = new Example(ViewOrganizeUserDO.class);
		example.createCriteria().andEqualTo("userId", userId).andLike("organizeName", "%"+organizeName+"%");
		return viewOrganizeUserMapper.selectByExample(example);
		
	}

}
