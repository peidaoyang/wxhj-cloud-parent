/**
 * @fileName: SysOrganizeService.java
 * @author: pjf
 * @date: 2019年10月14日 下午4:38:32
 */

package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.bo.SysOrgOptimizeBO;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;

/**
 * @author pjf
 * @className SysOrganizeService.java
 * @date 2019年10月14日 下午4:38:32
 */

public interface SysOrganizeService {
    // 向下查询不包含自己
    List<SysOrganizeDO> selectByParentIdRecursion(String parentId);

    // List<SysOrganizeDO> selectUp(String id);

    // 向上包含自己不包含maxId
    List<SysOrganizeDO> selectByIdRecursion(String id, String maxId);

    /*
     * 小于等于层级
     */
    List<SysOrganizeDO> listByLayers(String parentId, Integer layers);

    int selectCountByParentId(String parentId);

    SysOrganizeDO selectById(String id);

    // 假删除
    void shamDeleteCascade(String id, String userId);

    void update(SysOrganizeDO sysOrganizeDO, String userId);

    // List<SysOrganizeDO> select();

//	List<SysOrganizeDO> selectByFullNameAndOrgIdList(String fullName, List<String> organizeIdList,
//			SysOrganizeDO mainSysOrganize);

//	IPageResponseModel selectByFullNameAndParentIdPage(IPageRequestModel pageRequestModel, String fullName,
//			String parentid);

    // String insert(SysOrganizeDO sysOrganizeDO, String userid);

    String insertCascade(SysOrgOptimizeBO sysOrgOptDO, String userId);

    // List<String> listByOrgId(String organizeId);

    List<SysOrganizeDO> listByOrgIdList(List<String> organizeIdList);

    // List<SysOrganizeDO> listByIdMinToMax(String minId, String maxId);

}
