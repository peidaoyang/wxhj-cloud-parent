package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.mapper.FaceChangeRecMapper;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FaceChangeRecServiceImpl implements FaceChangeRecService {
    @Resource
    FaceChangeRecMapper faceChangeRecMapper;

    @Override
    @Transactional
    public void insertListCascade(List<FaceChangeRecDO> faceChangeRecList) {
        for (FaceChangeRecDO faceChangeRecTemp : faceChangeRecList) {
            faceChangeRecMapper.insert(faceChangeRecTemp);
        }
    }

    @Override
    public Boolean existByMasterId(Long masterId) {
        Example example = new Example(FaceChangeRecDO.class);
        example.createCriteria().andEqualTo("masterId", masterId);
        int faceChangeRecCount = faceChangeRecMapper.selectCountByExample(example);
        return faceChangeRecCount > 0;
    }

    @Override
    public List<FaceChangeRecDO> listGreaterThanIndexAndId(String id, Long currentIndex) {
        Example example = new Example(FaceChangeRecDO.class);
        example.createCriteria().andEqualTo("id", id).andGreaterThan("currentIndex", currentIndex);
        example.setOrderByClause("current_index");
        return faceChangeRecMapper.selectByExample(example);
    }
}