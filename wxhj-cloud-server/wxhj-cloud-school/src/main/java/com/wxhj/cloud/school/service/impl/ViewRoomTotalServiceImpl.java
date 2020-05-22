package com.wxhj.cloud.school.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.school.domain.DormitoryDO;
import com.wxhj.cloud.school.domain.view.ViewRoomTotalDO;
import com.wxhj.cloud.school.mapper.ViewRoomTotalMapper;
import com.wxhj.cloud.school.service.ViewRoomTotalService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@Service
public class ViewRoomTotalServiceImpl implements ViewRoomTotalService {
    @Resource
    ViewRoomTotalMapper viewRoomTotalMapper;


    @Override
    public PageInfo<ViewRoomTotalDO> list(IPageRequestModel pageRequestModel, String nameValue, String organizeId,Integer type,String dormitoryId) {
        Example example = new Example(ViewRoomTotalDO.class);
        example.createCriteria().andEqualTo("dormitoryId",dormitoryId).andEqualTo("type",type).andEqualTo("organizeId",organizeId).andLike("number", "%"+nameValue+"%");
        PageInfo<ViewRoomTotalDO> pageList = PageUtil.selectPageList(pageRequestModel,()->viewRoomTotalMapper.selectByExample(example));
        return pageList;
    }
}
