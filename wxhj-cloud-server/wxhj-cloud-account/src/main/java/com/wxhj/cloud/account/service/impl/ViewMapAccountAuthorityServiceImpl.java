/** 
 * @fileName: ViewMapAccountAuthorityServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月7日 下午4:45:40 
 */

package com.wxhj.cloud.account.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wxhj.cloud.account.domain.view.ViewMapAccountAuthorityDO;
import com.wxhj.cloud.account.mapper.view.ViewMapAccountAuthorityMapper;
import com.wxhj.cloud.account.service.ViewMapAccountAuthorityService;
import tk.mybatis.mapper.entity.Example;

/**
 * @className ViewMapAccountAuthorityServiceImpl.java
 * @author pjf
 * @date 2020年2月7日 下午4:45:40
 */
@Service
public class ViewMapAccountAuthorityServiceImpl implements ViewMapAccountAuthorityService {
	@Resource
	ViewMapAccountAuthorityMapper viewMapAccountAuthorityMapper;

	@Override
	public List<ViewMapAccountAuthorityDO> listByAuthorityId(String authorityId) {
		Example example = new Example(ViewMapAccountAuthorityDO.class);
		example.createCriteria().andEqualTo("authorityGroupId", authorityId);
		return viewMapAccountAuthorityMapper.selectByExample(example);
	}

}
