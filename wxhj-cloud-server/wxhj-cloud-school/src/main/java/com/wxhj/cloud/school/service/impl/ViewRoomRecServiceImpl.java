package com.wxhj.cloud.school.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.school.domain.RoomDO;
import com.wxhj.cloud.school.mapper.ViewRoomRecMapper;
import com.wxhj.cloud.school.service.ViewRoomRecService;
import com.wxhj.cloud.school.domain.view.ViewRoomRecDO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Service
public class ViewRoomRecServiceImpl implements ViewRoomRecService {
    @Resource
    ViewRoomRecMapper viewRoomRecMapper;


    @Override
    public IPageResponseModel listRoomRec(IPageRequestModel pageRequestModel, String nameValue, String dormitoryId) {
        Example example = new Example(ViewRoomRecDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dormitoryId",dormitoryId);

        Example.Criteria criteriaOr = example.createCriteria();
        if(!Strings.isNullOrEmpty(nameValue)) {
            criteriaOr.orLike("roomNumber", "%" + nameValue + "%").orLike("otherCode", "%" + nameValue + "%");
        }

        example.and(criteriaOr);
        PageInfo<ViewRoomRecDO> pageList = PageUtil.selectPageList(pageRequestModel,()->viewRoomRecMapper.selectByExample(example));
        PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
        pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageList,pageDefResponseModel, ViewRoomRecDO.class);
        return pageDefResponseModel;
    }

    @Override
    public int select(String otherCode,String dormitoryId) {
        Example example = new Example(ViewRoomRecDO.class);
        example.createCriteria().andEqualTo("otherCode",otherCode).andEqualTo("dormitoryId",dormitoryId);
        return viewRoomRecMapper.selectCountByExample(example);
    }

    @Override
    public List<ViewRoomRecDO> list(List<String> dormitoryId) {
        Example example = new Example(ViewRoomRecDO.class);
        example.createCriteria().andIn("dormitoryId",dormitoryId);
        return viewRoomRecMapper.selectByExample(example);
    }
}
