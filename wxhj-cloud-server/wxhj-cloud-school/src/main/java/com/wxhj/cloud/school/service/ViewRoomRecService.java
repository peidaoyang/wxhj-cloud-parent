package com.wxhj.cloud.school.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.school.domain.view.ViewDormitoryTotalDO;
import com.wxhj.cloud.school.domain.view.ViewRoomRecDO;

import java.util.List;

public interface ViewRoomRecService {
    PageInfo<ViewRoomRecDO> listRoomRec(IPageRequestModel pageRequestModel, String nameValue, String dormitoryId);

    /**
     * 根据其他编码和楼栋id查询入住人员数量
     * @param otherCode
     * @param organizeId
     * @return
     */
    int select(String otherCode,String organizeId);

    /**
     * 通过楼栋号查询已入住人数和空床位数
     * @param dormitoryId
     * @return
     */
    List<ViewRoomRecDO> list(List<String> dormitoryId);
}
