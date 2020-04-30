package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.FaceChangeRecDO;

import java.util.List;

public interface FaceChangeRecService {
    void insertListCascade(List<FaceChangeRecDO> faceChangeRecList);

    //Boolean existByMasterId(Long masterId);
    void deleteByAccountIdAndOperateType(String accountId,Integer operateType);

    List<FaceChangeRecDO> listMaxIdAndMinId(Long maxId,Long minId);

    List<FaceChangeRecDO> listGreaterThanIndexAndId(String id,Long currentIndex);
}
