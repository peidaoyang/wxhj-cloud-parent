/** 
 * @fileName: SysModuleService.java  
 * @author: pjf
 * @date: 2019年10月16日 上午10:41:45 
 */

package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.domain.SysModuleDO;

/**
 * @className SysModuleService.java
 * @author pjf
 * @date 2019年10月16日 上午10:41:45
 */

public interface SysModuleService {

	List<SysModuleDO> selectByidList(List<String> idList);

	List<SysModuleDO> select();

	String insertCascade(SysModuleDO sysModuleDO, String userid);

	void update(SysModuleDO sysModuleDO, String userid);

	void deleteCascade(String id);

	List<SysModuleDO> selectByFullName(String fullName);

	List<SysModuleDO> listByLayersAndMType(Integer layers,List<String> moduleList);
}
