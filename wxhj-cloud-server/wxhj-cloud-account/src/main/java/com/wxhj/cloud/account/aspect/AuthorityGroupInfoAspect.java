package com.wxhj.cloud.account.aspect;

import com.wxhj.cloud.account.domain.AuthorityGroupInfoDO;
import com.wxhj.cloud.account.domain.MapAuthoritySceneDO;
import com.wxhj.cloud.account.service.MapAccountAuthorityPlusService;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import com.wxhj.cloud.account.service.MapAuthorityScenePlusService;
import com.wxhj.cloud.account.service.MapAuthoritySceneService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class AuthorityGroupInfoAspect {
    @Resource
    MapAuthoritySceneService mapAuthoritySceneService;
	@Resource
	MapAuthorityScenePlusService mapAuthorityScenePlusService;
    @Resource
    MapAccountAuthorityService mapAccountAuthorityService;
    @Resource
	MapAccountAuthorityPlusService mapAccountAuthorityPlusService;

    @Pointcut("execution(public String com.wxhj.cloud.account.service.AuthorityGroupInfoService.insertCascade(..))")
    public void authorityGroupInfoInsertCut() {
    }

    @Pointcut("execution(public void com.wxhj.cloud.account.service.AuthorityGroupInfoService.updateCascade(..))")
    public void authorityGroupInfoUpdateCut() {
    }

    @AfterReturning(returning = "rObject", value = "authorityGroupInfoInsertCut()")
    public void insert(JoinPoint joinPoint, Object rObject) {
        Object[] args = joinPoint.getArgs();
        String id = (String) rObject;

        List<String> sceneIdList = (List<String>) args[1];
        if (args[2] != null) {
            List<String> accountIdList = (List<String>) args[2];
            mapAccountAuthorityPlusService.update(id, accountIdList);
        }

        List<MapAuthoritySceneDO> addMapAuthoritySceneList = sceneIdList.stream()
                .map(q -> new MapAuthoritySceneDO(null, id, q)).collect(Collectors.toList());
        addMapAuthoritySceneList.forEach(q -> {
            mapAuthoritySceneService.insertCascade(q);
        });
    }

    @After("authorityGroupInfoUpdateCut()")
    public void authorityGroupInfoUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        AuthorityGroupInfoDO authorityGroupInfo = (AuthorityGroupInfoDO) args[0];
        List<String> sceneIdList = (List<String>) args[1];
        if (args[2] != null) {
            List<String> accountIdList = (List<String>) args[2];
            mapAccountAuthorityPlusService.update(authorityGroupInfo.getId(), accountIdList);
        }

        mapAuthorityScenePlusService.update(1, sceneIdList, authorityGroupInfo.getId());

//		List<MapAuthoritySceneDO> addMapAuthoritySceneList;
//		List<MapAuthoritySceneDO> deleteMapAuthoritySceneList;
//		MapAuthoritySceneDO mapAuthorityScene = new MapAuthoritySceneDO();
//		mapAuthorityScene.setAuthorityGroupId(authorityGroupInfo.getId());
//		List<MapAuthoritySceneDO> oldMapAuthoritySceneList = mapAuthoritySceneService.listByAll(mapAuthorityScene);
//		List<MapAuthoritySceneDO> newMapAuthoritySceneList = sceneIdList.stream()
//				.map(q -> new MapAuthoritySceneDO(null, authorityGroupInfo.getId(), q)).collect(Collectors.toList());
//		deleteMapAuthoritySceneList = oldMapAuthoritySceneList.stream()
//				.filter(q -> !newMapAuthoritySceneList.contains(q)).collect(Collectors.toList());
//		addMapAuthoritySceneList = newMapAuthoritySceneList.stream().filter(q -> !oldMapAuthoritySceneList.contains(q))
//				.collect(Collectors.toList());
//		//
//		addMapAuthoritySceneList.forEach(q -> {
//			mapAuthoritySceneService.insertCascade(q);
//		});
//		deleteMapAuthoritySceneList.forEach(q -> {
//			mapAuthoritySceneService.deleteCascade(q.getAuthorityGroupId(), q.getSceneId());
//		});
    }
}
