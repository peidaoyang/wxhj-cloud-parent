package com.wxhj.cloud.school.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.school.domain.RoomDO;
import com.wxhj.cloud.school.domain.RoomRecDO;
import com.wxhj.cloud.school.mapper.RoomMapper;
import com.wxhj.cloud.school.mapper.RoomRecMapper;
import com.wxhj.cloud.school.service.RoomRecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@Service
public class RoomRecServiceImpl implements RoomRecService {
    @Resource
    RoomRecMapper roomRecMapper;

    @Override
    public List<RoomRecDO> listByRoomId(String roomId) {
        Example example = new Example(RoomRecDO.class);
        example.createCriteria().andEqualTo("roomId",roomId);
        return roomRecMapper.selectByExample(example);
    }

    @Override
    public RoomRecDO selectByAccountId(String accountId){
        Example example = new Example(RoomRecDO.class);
        example.createCriteria().andEqualTo("accountId",accountId);
        return roomRecMapper.selectOneByExample(example);
    }

    @Override
    @Transactional
    public void insertCascade(List<RoomRecDO> roomRecDOList) {
        roomRecDOList.forEach(q-> {
            q.setId(UUID.randomUUID().toString());
            roomRecMapper.insertSelective(q);
        });
    }

    @Override
    public void updateCascade(RoomRecDO roomRecDO) {
        roomRecMapper.updateByPrimaryKeySelective(roomRecDO);
    }

    @Override
    @Transactional
    public void deleteCascade(String id) {
        roomRecMapper.deleteByPrimaryKey(id);
    }

//    @Override
//    public void deleteByRoomId(String roomId) {
//        Example example = new Example(RoomRecDO.class);
//        example.createCriteria().andEqualTo("roomId",roomId);
//        roomRecMapper.deleteByExample(example);
//    }

    @Override
    public void deleteByRoomIdAndGreaterThanNumber(String roomId, Integer rooNum) {
        Example example = new Example(RoomRecDO.class);
        example.createCriteria().andEqualTo("roomId",roomId).andGreaterThan("number",rooNum);
        roomRecMapper.deleteByExample(example);
    }

    @Override
    public RoomRecDO select(String id) {
        return roomRecMapper.selectByPrimaryKey(id);
    }


}
