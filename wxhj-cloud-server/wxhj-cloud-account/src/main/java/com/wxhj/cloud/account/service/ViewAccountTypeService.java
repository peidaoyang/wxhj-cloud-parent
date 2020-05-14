package com.wxhj.cloud.account.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.view.ViewAccountTypeDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.util.List;

public interface ViewAccountTypeService {
    PageInfo<ViewAccountTypeDO> listByNameOrPhoneNumberAndChildOrgPage(String fullName, List<String> organizeId, String type,
                                                                       IPageRequestModel pageRequestModel);
}
