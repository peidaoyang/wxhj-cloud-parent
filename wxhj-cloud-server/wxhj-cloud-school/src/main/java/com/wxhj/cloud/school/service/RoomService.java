package com.wxhj.cloud.school.service;

import com.wxhj.cloud.school.domain.RoomDO;

import java.util.List;


public interface RoomService {
    String insertCascade(RoomDO roomDO);

    /**
     * 批量添加房间,同时生成对应的床位(房间的主键需由接口调用者自己生成)
     * @param roomDOList
     */
    void insertListCascade(List<RoomDO> roomDOList);

    void updateCascade(RoomDO roomDO);

    void deleteCascade(String id);

    List<RoomDO> list(String dormitoryId, Integer floor, Integer type);
}
