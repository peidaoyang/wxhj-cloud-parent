package com.wxhj.cloud.platform.service;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;

import java.util.List;

public interface SysOrganizeServiceCached {
    List<SysOrganizeDO> selectByParentIdRecursion(String parentid);

    List<SysOrganizeDO> selectByParentId(String parentid);
    int selectCountByParentId(String parentid);
    SysOrganizeDO selectById(String id);

    void shamDeleteByKey(String id,String userid);
    void update(SysOrganizeDO sysOrganizeDO, String userid);
    List<SysOrganizeDO> select();

    List<SysOrganizeDO> selectByFullNameAndOrgIdList(String fullName, List<String> organizeIdList,SysOrganizeDO mainSysOrganize);
    IPageResponseModel selectByFullNameAndParentIdPage(IPageRequestModel pageRequestModel, String fullName, String parentid);

    String insert(SysOrganizeDO sysOrganizeDO, String userid);

    List<String> listByOrgId(String organizeId);

    List<SysOrganizeDO>  listByOrgIdList(List<String> organizeIdList);
}
