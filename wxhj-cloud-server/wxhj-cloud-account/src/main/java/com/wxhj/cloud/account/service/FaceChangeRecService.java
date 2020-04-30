package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.FaceChangeRecDO;

import java.util.List;

public interface FaceChangeRecService {
    List<FaceChangeRecDO>  insertListCascade(List<FaceChangeRecDO> faceChangeRecList);

    void deleteByAccountIdAndOperateType(String accountId,Integer operateType);

    List<FaceChangeRecDO> listMaxIdAndMinId(Long maxId,Long minId);

    List<FaceChangeRecDO> listGreaterThanIndexAndId(String id,Long currentIndex);
}
