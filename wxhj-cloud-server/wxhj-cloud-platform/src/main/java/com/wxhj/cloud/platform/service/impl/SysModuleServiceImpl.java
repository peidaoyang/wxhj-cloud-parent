/** 
 * @fileName: SysModuleServiceImpl.java  
 * @author: pjf
 * @date: 2019年10月16日 上午10:43:29 
 */

package com.wxhj.cloud.platform.service.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.platform.domain.SysModuleDO;
import com.wxhj.cloud.platform.mapper.SysModuleMapper;
import com.wxhj.cloud.platform.service.SysModuleService;
import com.wxhj.cloud.platform.util.ViewControlUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className SysModuleServiceImpl.java
 * @author pjf
 * @date 2019年10月16日 上午10:43:29
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

	@Resource
	SysModuleMapper sysModuleMapper;

	@Override
	public List<SysModuleDO> select() {
		Example example = new Example(SysModuleDO.class);
		example.createCriteria().andEqualTo("isDeleteMark", 0);

		example.orderBy("layers").orderBy("sortCode");
		return sysModuleMapper.selectByExample(example);
	}

	@Override
	public List<SysModuleDO> selectByidList(List<String> idList) {
		List<SysModuleDO> sysModuleList = select();

		return ViewControlUtil.treeElementFilterRecursion(sysModuleList, new Predicate<SysModuleDO>() {
			@Override
			public boolean test(SysModuleDO t) {
				return idList.contains(t.getId());
			}
		}, "0");

	}

	@Override
	public String insertCascade(SysModuleDO sysModuleDO, String userid) {
		sysModuleDO.initialization();
		sysModuleDO.create(userid);

		sysModuleMapper.insert(sysModuleDO);
		return sysModuleDO.getId();
	}

	@Override
	public void update(SysModuleDO sysModuleDO, String userid) {
		sysModuleMapper.updateByPrimaryKeySelective(sysModuleDO);
		return;
	}

	@Override
	public void deleteCascade(String id) {
		sysModuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SysModuleDO> selectByFullName(String nameValue) {
		List<SysModuleDO> sysModuleList = select();

		return ViewControlUtil.treeElementFilterRecursion(sysModuleList, new Predicate<SysModuleDO>() {
			@Override
			public boolean test(SysModuleDO t) {
				return t.getFullName().indexOf(nameValue) > -1;
			}
		}, "0");
	}

	@Override
	public List<SysModuleDO> listByLayersAndMType(Integer layers,List<String> moduleList) {
		moduleList = selectByidList(moduleList).stream().map(q->q.getId()).collect(Collectors.toList());
		Example example = new Example(SysModuleDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("layers", layers).andEqualTo("isDeleteMark", 0).andIn("id", moduleList);
		return sysModuleMapper.selectByExample(example);
	}

}
