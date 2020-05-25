/**
 * 
 */
package com.wxhj.cloud.platform.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.EnumManageDO;
import com.wxhj.cloud.platform.mapper.EnumManageMapper;
import com.wxhj.cloud.platform.service.EnumManageService;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: EnumManageServiceImpl.java
 * @author: cya
 * @Date: 2020年1月8日 上午11:22:07 
 */
@Service
public class EnumManageServiceImpl implements EnumManageService{
	@Resource
	EnumManageMapper enumManageMapper;

	@Override
	public IPageResponseModel selectEnumList(IPageRequestModel pageRequestModel,String enumName) {
		Example example = new Example(EnumManageDO.class);
		example.createCriteria().andLike("enumName", "%"+enumName+"%").andEqualTo("isHidden",0);
		PageInfo<EnumManageDO> enumList = PageUtil.selectPageList(pageRequestModel, 
				()->enumManageMapper.selectByExample(example));
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(enumList, 
				pageDefResponseModel, EnumManageDO.class);
		return pageDefResponseModel;
	}
	@Override
	public String insert(EnumManageDO enumManage) {
		enumManage.setId(UUID.randomUUID().toString());
		enumManageMapper.insertSelective(enumManage);
		return enumManage.getId();
	}

	@Override
	public void delete(String id) {
		enumManageMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void update(EnumManageDO enumManageDO) {
		enumManageMapper.updateByPrimaryKeySelective(enumManageDO);
	}
	@Override
	public List<EnumManageDO> selectByEnumCode(Integer enumCode) {
		Example example = new Example(EnumManageDO.class);
		example.createCriteria().andEqualTo("enumCode",enumCode);
		return enumManageMapper.selectByExample(example);
	}

	@Override
	public List<EnumManageDO> listByEnumcodeAndEnumType(Integer enumCode, List<Integer> enumType) {
		Example example = new Example(EnumManageDO.class);
		example.createCriteria().andEqualTo("enumCode",enumCode).andIn("enumType", enumType);
		return enumManageMapper.selectByExample(example);
	}
	
}
