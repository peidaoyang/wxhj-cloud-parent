package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.FaceChangeDO;

import java.util.List;

public interface FaceChangeService {

    Long selectCurrentIndex(String id);

    List<FaceChangeDO> listBySceneId(List<String> idList);

    FaceChangeDO selectBySceneId(String sceneId);

    List<FaceChangeDO> listAll();
}
