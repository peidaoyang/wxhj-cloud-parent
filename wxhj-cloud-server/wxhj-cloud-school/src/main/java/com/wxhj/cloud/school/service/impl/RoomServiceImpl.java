package com.wxhj.cloud.school.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.school.domain.DormitoryDO;
import com.wxhj.cloud.school.domain.RoomDO;
import com.wxhj.cloud.school.domain.RoomRecDO;
import com.wxhj.cloud.school.mapper.RoomMapper;
import com.wxhj.cloud.school.service.RoomService;
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
public class RoomServiceImpl implements RoomService {
    @Resource
    RoomMapper roomMapper;

    @Override
    @Transactional
    public String insertCascade(RoomDO roomDO) {
        roomDO.setId(UUID.randomUUID().toString());
        roomMapper.insertSelective(roomDO);
        return roomDO.getId();
    }

    @Override
    @Transactional
    public void insertListCascade(List<RoomDO> roomDOList) {
        roomDOList.forEach(q->roomMapper.insert(q));
    }

    @Override
    @Transactional
    public void updateCascade(RoomDO roomDO) {
        roomMapper.updateByPrimaryKeySelective(roomDO);
    }

    @Override
    @Transactional
    public void deleteCascade(String id) {
        roomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<RoomDO> list(String dormitoryId, Integer floor,Integer type) {
        Example example = new Example(RoomDO.class);
        example.createCriteria().andEqualTo("dormitoryId",dormitoryId).andEqualTo("floorNumber",floor).andEqualTo("type",type);
        return roomMapper.selectByExample(example);
    }
}
