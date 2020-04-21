/**
 * @fileName: MapListenAcpect.java
 * @author: pjf
 * @date: 2019年10月31日 下午4:54:47
 */

package com.wxhj.cloud.account.aspect;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.domain.MapAuthoritySceneDO;
import com.wxhj.cloud.account.domain.MapListenListDO;
import com.wxhj.cloud.account.domain.MapSceneAccountDO;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import com.wxhj.cloud.account.service.MapAuthoritySceneService;
import com.wxhj.cloud.account.service.MapListenListService;
import com.wxhj.cloud.account.service.MapSceneAccountService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pjf
 * @className MapListenAcpect.java
 * @date 2019年10月31日 下午4:54:47
 */

@Aspect
@Component
public class MapListenAspect {
    @Resource
    MapListenListService mapListenListService;
    @Resource
    MapAuthoritySceneService mapAuthoritySceneService;
    @Resource
    MapAccountAuthorityService mapAccountAuthorityService;
    @Resource
    MapSceneAccountService mapSceneAccountService;

    /**
     * @author pjf
     * @date 2019年11月5日 下午4:47:20
     */
    @Pointcut("execution(public String com.wxhj.cloud.account.service.MapAccountAuthorityService.insertCascade(..))")
    public void mapAccountAuthorityInsertCut() {
    }

    /**
     * @author pjf
     * @date 2019年11月5日 下午4:47:16
     */
    // deleteByAuthorityGroupIdAndAccountId
    @Pointcut("execution(public int com.wxhj.cloud.account.service.MapAccountAuthorityService.deleteCascade(..))")
    public void mapAccountAuthorityDeleteCut() {
    }

    /**
     * @author pjf
     * @date 2019年11月5日 下午4:47:13
     */
    @Pointcut("execution(public String com.wxhj.cloud.account.service.MapAuthoritySceneService.insertCascade(..))")
    public void mapAuthoritySceneInsertCut() {
    }

    /**
     * @author pjf
     * @date 2019年11月5日 下午4:47:10
     */
    @Pointcut("execution(public int com.wxhj.cloud.account.service.MapAuthoritySceneService.deleteCascade(..))")
    public void mapAuthoritySceneDeleteCut() {
    }

    @Pointcut("execution(public void com.wxhj.cloud.account.service.MapListenListService.insertListCascade(..))")
    public void mapListenInsertListCut() {

    }

    @Around("mapListenInsertListCut()")
    public Object mapListenInsertList(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        List<MapListenListDO> mapListenListList = (List<MapListenListDO>) args[0];

        Long addCount = mapListenListList.stream().filter(q -> q.getOperateType() == 0).count();
        if (mapListenListList.size() != addCount && addCount != 0L) {
            throw new Throwable("只可同时增或者同时减");
        }
        mapListenListList = Lists.newArrayList(Sets.newHashSet(mapListenListList));
        List<MapListenListDO> returnMapListenListList = new ArrayList<>();
        for (MapListenListDO mapListenListTemp : mapListenListList) {
            MapSceneAccountDO mapSceneAccount = mapSceneAccountService
                    .selectBySceneIdAndAccountId(mapListenListTemp.getSceneId(), mapListenListTemp.getAccountId());
            // 增减
            if (mapListenListTemp.getOperateType() == 0) {
                if (mapSceneAccount.getTotalCount() == 0) {
                    returnMapListenListList.add(mapListenListTemp);
                }
                mapSceneAccountService.addTotalCount(mapSceneAccount.getId());
            }
            // 删除
            else {
                if (mapSceneAccount.getTotalCount() == 0) {
                    throw new Throwable("请确认系统数据的一致性,map_scene_account可能存在负值,id=" + mapSceneAccount.getId());
                }
                if (mapSceneAccount.getTotalCount() == 1) {
                    returnMapListenListList.add(mapListenListTemp);
                }
                mapSceneAccountService.subtractTotalCount(mapSceneAccount.getId());
            }
        }
        args[0] = returnMapListenListList;
        Object retVal = pjp.proceed(args);
        return retVal;
    }

    //
    @Resource(name = "faceChangeEventBus")
    EventBus faceChangeEventBus;

    //
    @Before("mapListenInsertListCut()")
    public void mapListenInsertListBefore(JoinPoint joinPoint) {
        faceChangeEventBus.post(50);
    }

    /**
     * @param joinPoint
     * @author pjf
     * @date 2019年11月5日 下午4:47:03
     */
    @Before("mapAccountAuthorityInsertCut()")
    public void mapAccountAuthorityInsert(JoinPoint joinPoint) {
        MapAccountAuthorityDO mapAccountAuthorityDO = null;
        Object[] args = joinPoint.getArgs();
        mapAccountAuthorityDO = (MapAccountAuthorityDO) args[0];
        insertForMapAuthorityScene(0, mapAccountAuthorityDO.getAccountId(),
                mapAccountAuthorityDO.getAuthorityGroupId());

    }

    /**
     * @param joinPoint
     * @author pjf
     * @date 2019年11月5日 下午4:46:59
     */
    @Before("mapAccountAuthorityDeleteCut()")
    public void mapAccountAuthorityDelete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String authorityGroupId = null;
        String accountId = null;
        MapAccountAuthorityDO mapAccountAuthority = new MapAccountAuthorityDO();
        if (args[0] != null && args[1] != null) {
            authorityGroupId = (String) args[0];
            accountId = (String) args[1];
            insertForMapAuthorityScene(1, accountId, authorityGroupId);
            return;
        } else if (args[0] != null) {
            authorityGroupId = (String) args[0];
            mapAccountAuthority.setAuthorityGroupId(authorityGroupId);
        } else {
            accountId = (String) args[1];
            mapAccountAuthority.setAccountId(accountId);
        }
        List<MapAccountAuthorityDO> mapAccountAuthorityList = mapAccountAuthorityService
                .list(mapAccountAuthority);
        mapAccountAuthorityList.forEach(q -> {
            insertForMapAuthorityScene(1, q.getAccountId(), q.getAuthorityGroupId());
        });
    }

    /**
     * @param joinPoint
     * @author pjf
     * @date 2019年11月5日 下午4:46:55
     */
    @Before("mapAuthoritySceneInsertCut()")
    public void mapAuthoritySceneInsert(JoinPoint joinPoint) {
        MapAuthoritySceneDO mapAuthoritySceneDO = null;
        Object[] args = joinPoint.getArgs();
        mapAuthoritySceneDO = (MapAuthoritySceneDO) args[0];
        insertForMapAccountAuthority(0, mapAuthoritySceneDO.getSceneId(), mapAuthoritySceneDO.getAuthorityGroupId());
    }

    @Before("mapAuthoritySceneDeleteCut()")
    public void mapAuthoritySceneDelete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MapAuthoritySceneDO mapAuthorityScene = new MapAuthoritySceneDO();
        String authorityGroupId = null;
        String sceneId = null;
        if (args[0] != null && args[1] != null) {
            authorityGroupId = (String) args[0];
            sceneId = (String) args[1];
            insertForMapAccountAuthority(1, sceneId, authorityGroupId);
            return;
        } else if (args[0] != null) {
            authorityGroupId = (String) args[0];
            mapAuthorityScene.setAuthorityGroupId(authorityGroupId);
        } else {
            sceneId = (String) args[1];
            mapAuthorityScene.setSceneId(sceneId);
        }

        List<MapAuthoritySceneDO> mapAuthoritySceneList = mapAuthoritySceneService.list(mapAuthorityScene);
        mapAuthoritySceneList.forEach(q -> {
            insertForMapAccountAuthority(1, q.getSceneId(), q.getAuthorityGroupId());
        });
    }

    private void insertForMapAccountAuthority(int operateType, String sceneId, String authorityId) {
        List<MapAccountAuthorityDO> mapAccountAuthorityList = mapAccountAuthorityService.listByAuthorityId(authorityId);
        List<MapListenListDO> mapListenListList = mapAccountAuthorityList.stream()
                .map(q -> new MapListenListDO(null, operateType, sceneId, q.getAccountId(), 0))
                .collect(Collectors.toList());
        mapListenListService.insertListCascade(mapListenListList);
    }

    private void insertForMapAuthorityScene(int operateType, String accountId, String authorityId) {
        List<MapAuthoritySceneDO> mapAuthoritySceneList = mapAuthoritySceneService.listByAuthorityId(authorityId);

        List<MapListenListDO> mapListenListList = mapAuthoritySceneList.stream()
                .map(q -> new MapListenListDO(null, operateType, q.getSceneId(), accountId, 0))
                .collect(Collectors.toList());
        mapListenListService.insertListCascade(mapListenListList);

    }
}
