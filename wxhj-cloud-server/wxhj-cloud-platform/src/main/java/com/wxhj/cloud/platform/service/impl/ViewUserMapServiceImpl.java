/** 
 * @fileName: ViewUserMapServerImpl.java  
 * @author: pjf
 * @date: 2019年10月11日 上午9:48:13 
 */

package com.wxhj.cloud.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.wxhj.cloud.platform.domain.view.ViewUserMapDO;
import com.wxhj.cloud.platform.mapper.view.ViewUserMapMapper;
import com.wxhj.cloud.platform.service.ViewUserMapService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className ViewUserMapServerImpl.java
 * @author pjf
 * @date 2019年10月11日 上午9:48:13
 */

@Service
public class ViewUserMapServiceImpl implements ViewUserMapService {
	@Resource
	ViewUserMapMapper viewUserMapMapper;

	@Override
	public List<ViewUserMapDO> selectByAccount(String account,int loginType) {
		Example example = new Example(ViewUserMapDO.class);
		Criteria andEqualTo = example.createCriteria();
		andEqualTo.andEqualTo("1=1");
		if(!Strings.isNullOrEmpty(account))
		{
			if(loginType == 0) {
				andEqualTo.andEqualTo("account", account).andEqualTo("isDeleteMark", 0);
				return viewUserMapMapper.selectByExample(example);
			}else if(loginType == 1){
				andEqualTo.andEqualTo("mobilePhone", account).andEqualTo("isDeleteMark", 0);
				return viewUserMapMapper.selectByExample(example);
			}
		}
	
		return null;
	}
	

	@Override
	public ViewUserMapDO selectById(String id) {
		
		return viewUserMapMapper.selectByPrimaryKey(id);
	}

}
