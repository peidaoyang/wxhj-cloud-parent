package com.wxhj.cloud.account.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wxhj.cloud.account.domain.MapAuthoritySceneDO;
import com.wxhj.cloud.account.service.MapAuthorityScenePlusService;
import com.wxhj.cloud.account.service.MapAuthoritySceneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapAuthorityScenePlusServiceImpl implements MapAuthorityScenePlusService {
    @Resource
    MapAuthoritySceneService mapAuthoritySceneService;

    @Override
    public void update(Integer submitType, List<String> mainId, String assistId) {
        List<MapAuthoritySceneDO> addMapAuthoritySceneList = new ArrayList<>();
        List<MapAuthoritySceneDO> deleteMapAuthoritySceneList = new ArrayList<>();

        List<MapAuthoritySceneDO> newMapAuthoritySceneList = new ArrayList<>();
        List<MapAuthoritySceneDO> oldMapAuthoritySceneList = new ArrayList<>();
        //
        MapAuthoritySceneDO mapAuthorityScene = new MapAuthoritySceneDO();
        if (submitType.equals(0)) {
            mapAuthorityScene.setSceneId(assistId);
            newMapAuthoritySceneList = mainId.stream().map(q -> new MapAuthoritySceneDO(null, q, assistId))
                    .collect(Collectors.toList());
        } else {
            mapAuthorityScene.setAuthorityGroupId(assistId);
            newMapAuthoritySceneList = mainId.stream()
                    .map(q -> new MapAuthoritySceneDO(null, assistId, q))
                    .collect(Collectors.toList());
        }

        //
        oldMapAuthoritySceneList = mapAuthoritySceneService.list(mapAuthorityScene);
        Sets.SetView<MapAuthoritySceneDO> differenceSetTemp = Sets.difference(Sets.newHashSet(newMapAuthoritySceneList),
                Sets.newHashSet(oldMapAuthoritySceneList));
        addMapAuthoritySceneList = Lists.newArrayList(differenceSetTemp);
        differenceSetTemp = Sets.difference(Sets.newHashSet(oldMapAuthoritySceneList),
                Sets.newHashSet(newMapAuthoritySceneList));
        deleteMapAuthoritySceneList = Lists.newArrayList(differenceSetTemp);
        addMapAuthoritySceneList.forEach(q -> {
            mapAuthoritySceneService.insertCascade(q);
        });
        deleteMapAuthoritySceneList.forEach(q -> {
            mapAuthoritySceneService.deleteCascade(q.getAuthorityGroupId(), q.getSceneId());
        });

    }

}
