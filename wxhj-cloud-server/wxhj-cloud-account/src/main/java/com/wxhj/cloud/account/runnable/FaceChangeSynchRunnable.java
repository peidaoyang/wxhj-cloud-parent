package com.wxhj.cloud.account.runnable;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.domain.MapListenListDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.account.service.MapListenListService;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.redis.domain.FaceChangeRecRedisDO;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FaceChangeSynchRunnable implements Runnable {

    private static Integer ASYNC_COUNT = 50;

    @Resource
    MapListenListService mapListenListService;
    @Resource
    FaceChangeRecService faceChangeRecService;
    @Resource
    AccountInfoService accountInfoService;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    FaceChangeService faceChangeService;


    private void faceSynch(Integer asyncCount) {
//        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) asyncMapListenList(asyncCount);
//        PageInfo<MapListenListDO> mapListenListDOPageInfo = mapListenListService.selectByNoSync(asyncCount);

        PageInfo<MapListenListDO> mapListenListPageInfo = mapListenListService.selectByNoSync(asyncCount);
        List<MapListenListDO> mapListenListList = mapListenListPageInfo.getList();
        if (mapListenListList.size() <= 0) {
            return;
        }
        Long maxId = mapListenListList.stream().mapToLong(q -> q.getId()).max().getAsLong();
        Long minId = mapListenListList.stream().mapToLong(q -> q.getId()).min().getAsLong();
        List<Long> faceChangeRecs =
                faceChangeRecService.listMaxIdAndMinId(maxId, minId).stream()
                        .map(q -> q.getMasterId()).collect(Collectors.toList());
        mapListenListList = mapListenListList.stream().filter(
                q -> !faceChangeRecs.contains(q.getId())
        ).collect(Collectors.toList());
        if (mapListenListList.size() <= 0) {
            return;
        }
        //
        List<FaceChangeRecDO> faceChangeRecList = mapListenListList.stream().map(q -> {
            FaceChangeRecDO faceChangeRec;
            AccountInfoDO faceAcountInfo = accountInfoService.selectByAccountId(q.getAccountId());
            String url = q.getOperateType() == 0 ? (faceAcountInfo == null ? null : faceAcountInfo.getImageName())
                    : null;
            if (faceAcountInfo == null) {
                return null;
            }

            faceChangeRec = FaceChangeRecDO.builder().id(q.getSceneId())
                    .masterId(q.getId()).accountId(q.getAccountId())
                    .imageName(url).operateType(q.getOperateType())
                    .idNumber(faceAcountInfo.getIdNumber())
                    .name(faceAcountInfo.getName())
                    .phoneNumber(faceAcountInfo.getPhoneNumber()).build();

            return faceChangeRec;
        }).collect(Collectors.toList());

        if (faceChangeRecList.size() > 0) {
            faceChangeRecService.insertListCascade(faceChangeRecList);
            List<Long> idList = faceChangeRecList.stream().map(q -> q.getMasterId()).collect(Collectors.toList());
            confirmAsyncMapListenList(idList);
        }
        if (!mapListenListPageInfo.isIsLastPage()) {
            this.faceSynch(asyncCount);
        }
    }


    private int confirmAsyncMapListenList(
            List<Long> idList) {
        int updateByIdSetSync = mapListenListService.updateByIdSetSync(idList);
        return updateByIdSetSync;
    }

    //以上为人脸同步
    @Override
    public void run() {
        faceSynch(ASYNC_COUNT);
        loadCache();
        log.info("人脸同步完成");
    }


    //一下为载入内存
    // @Subscribe
    private void loadCache() {
        List<FaceChangeDO> faceChangeList = faceChangeService.listAll();
        faceChangeList.forEach(q -> syncCache(q));
    }


    private void syncCache(FaceChangeDO faceChange) {
        String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(faceChange.getId());
        Long minIndex = faceChange.getMinIndex();
        Long maxIndex = faceChange.getMaxIndex();
        if (redisTemplate.hasKey(redisKey)) {

            Set<FaceChangeRecRedisDO> faceChangeRecSet = (LinkedHashSet<FaceChangeRecRedisDO>) redisTemplate
                    .opsForZSet().reverseRangeByScore(redisKey, minIndex, maxIndex, 0, 1);
            minIndex = faceChangeRecSet.iterator().next().getCurrentIndex();

        } else {
            minIndex = faceChange.getMinIndex() - 1;
        }

        List<FaceChangeRecDO> faceChangeRecList = faceChangeRecService.listGreaterThanIndexAndId(faceChange.getId(),
                minIndex);
        for (FaceChangeRecDO faceChangeRecTemp : faceChangeRecList) {
            FaceChangeRecRedisDO faceChangeRecRedis = dozerBeanMapper.map(faceChangeRecTemp,
                    FaceChangeRecRedisDO.class);
            redisTemplate.opsForZSet().add(redisKey, faceChangeRecRedis, faceChangeRecTemp.getCurrentIndex());
        }
    }


}
