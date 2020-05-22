package com.wxhj.cloud.school.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.school.domain.RoomDO;
import com.wxhj.cloud.school.domain.RoomRecDO;

import java.util.List;

public interface RoomRecService {
    List<RoomRecDO> listByRoomId(String roomId);

    void insertCascade(List<RoomRecDO> roomRecDOList);

    void updateCascade(RoomRecDO roomRecDO);

    void deleteCascade(String id);

//    void deleteByRoomId(String roomId);

    /**
     * 根据房间编号,删除床位号大于roomNumd的床位信息(调用此接口需要确认床位下没有入住学生才能删除)
     * @param roomId
     * @param rooNum
     */
    void deleteByRoomIdAndGreaterThanNumber(String roomId,Integer rooNum);

    RoomRecDO select(String id);

    RoomRecDO selectByAccountId(String accountId);
}
