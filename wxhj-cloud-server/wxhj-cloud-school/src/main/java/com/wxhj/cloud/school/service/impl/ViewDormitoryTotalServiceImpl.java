package com.wxhj.cloud.school.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.school.domain.DormitoryDO;
import com.wxhj.cloud.school.domain.view.ViewDormitoryTotalDO;
import com.wxhj.cloud.school.mapper.ViewDormitoryTotalMapper;
import com.wxhj.cloud.school.service.ViewDormitoryTotalService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class ViewDormitoryTotalServiceImpl implements ViewDormitoryTotalService {
    @Resource
    ViewDormitoryTotalMapper viewDormitoryTotalMapper;


    @Override
    public PageInfo<ViewDormitoryTotalDO> list(IPageRequestModel pageRequestModel, String nameValue, String organizeId) {
        Example example = new Example(DormitoryDO.class);
        example.createCriteria().andLike("number", "%"+nameValue+"%").andEqualTo("organizeId",organizeId);
        PageInfo<ViewDormitoryTotalDO> pageList = PageUtil.selectPageList(pageRequestModel,()->viewDormitoryTotalMapper.selectByExample(example));
        return pageList;
    }
}
