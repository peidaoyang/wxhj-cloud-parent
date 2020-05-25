package com.wxhj.cloud.account.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.view.ViewAccountTypeDO;
import com.wxhj.cloud.account.mapper.ViewAccountTypeMapper;
import com.wxhj.cloud.account.service.ViewAccountTypeService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Service
public class ViewAccountTypeServiceImpl implements ViewAccountTypeService {
    @Resource
    ViewAccountTypeMapper viewAccountTypeMapper;


    @Override
    public PageInfo<ViewAccountTypeDO> listByNameOrPhoneNumberAndChildOrgPage(String fullName, List<String> organizeId, String type, IPageRequestModel pageRequestModel) {
        Example example = new Example(ViewAccountTypeDO.class);
        example.createCriteria().andIn("childOrganizeId", organizeId).andLike(type, "%" + fullName + "%");
        PageInfo<ViewAccountTypeDO> accountInfoList = PageUtil.selectPageList(pageRequestModel,() -> viewAccountTypeMapper.selectByExample(example));
        return accountInfoList;
    }
}
