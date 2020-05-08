package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.FaceChangeDO;

import java.util.List;

public interface FaceChangeService {

    Long selectCurrentIndex(String id);

    List<FaceChangeDO> listBySceneIdList(List<String> idList);

    FaceChangeDO selectBySceneId(String sceneId);

    List<FaceChangeDO> listAll();

    void delete(String id);
}
