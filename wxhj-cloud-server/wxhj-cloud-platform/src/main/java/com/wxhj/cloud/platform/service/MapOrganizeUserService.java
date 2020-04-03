package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;

public interface MapOrganizeUserService {
	void insert(MapOrganizeUserDO mapOrganizeUserDO);
	void insert(List<MapOrganizeUserDO> mapOrganizeList);
	//void update(MapOrganizeUserDO mapOrganizeUserDO);
	//void updateByUserId(MapOrganizeUserDO mapOrganizeUserDO);
	
	void deleteById(List<String> idList);
	//String selectRoleIdByUserId(String userId);
	void deleteByUserId(String userId);
	
	void deleteByOrganizeId(String organizeId);
	//List<MapOrgUserReponseDTO> selectByUserId(String userId);
	//List<MapOrgRoleResponseDTO> selectByRoleId(String userId,String organizeId);
	//void deleteMapOrganize(List<String> organizeIdList,String userId);
	//void update(List<MapOrganizeUserDO> mapOrganizeList);
}
