package com.wxhj.cloud.school.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.school.domain.view.ViewRoomTotalDO;

public interface ViewRoomTotalService {
    PageInfo<ViewRoomTotalDO> list(IPageRequestModel pageRequestModel, String nameValue, String organizeId,Integer type,String dormitoryId);
}
