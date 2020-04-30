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
    public List<FaceChangeRecDO> insertListCascade(List<FaceChangeRecDO> faceChangeRecList) {
        for (FaceChangeRecDO faceChangeRecTemp : faceChangeRecList) {
           faceChangeRecMapper.insert(faceChangeRecTemp);
        }
        return faceChangeRecList;
    }

    @Override
    public void deleteByAccountIdAndOperateType(String accountId, Integer operateType) {
        Example example = new Example(FaceChangeRecDO.class);
        example.createCriteria().andEqualTo("accountId",accountId)
                .andEqualTo("operateType",operateType);
        faceChangeRecMapper.deleteByExample(example);
    }

    @Override
    public List<FaceChangeRecDO> listMaxIdAndMinId(Long maxId, Long minId) {
        Example example = new Example(FaceChangeRecDO.class);
        example.createCriteria().andGreaterThanOrEqualTo("masterId", minId)
                .andLessThanOrEqualTo("masterId", maxId);

        return faceChangeRecMapper.selectByExample(example);
    }

//    @Override
//    public Boolean existByMasterId(Long masterId) {
//        Example example = new Example(FaceChangeRecDO.class);
//        example.createCriteria().andEqualTo("masterId", masterId);
//        int faceChangeRecCount = faceChangeRecMapper.selectCountByExample(example);
//        return faceChangeRecCount > 0;
//    }

    @Override
    public List<FaceChangeRecDO> listGreaterThanIndexAndId(String id, Long currentIndex) {
        Example example = new Example(FaceChangeRecDO.class);
        example.createCriteria().andEqualTo("id", id)
                .andGreaterThan("currentIndex", currentIndex);
        example.setOrderByClause("current_index");
        return faceChangeRecMapper.selectByExample(example);
    }
}
