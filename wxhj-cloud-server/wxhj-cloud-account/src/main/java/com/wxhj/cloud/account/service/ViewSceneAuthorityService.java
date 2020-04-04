package com.wxhj.cloud.account.service;

import java.util.List;

import com.wxhj.cloud.account.domain.view.ViewSceneAuthorityDO;

public interface ViewSceneAuthorityService {
	List<ViewSceneAuthorityDO> list(List<String> sceneId);
}
