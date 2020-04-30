package com.wxhj.cloud.account.runnable;

import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.redis.domain.FaceChangeRecRedisDO;
import org.dozer.DozerBeanMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wxpjf
 * @date 2020/4/30 13:44
 */
@Component
public class FaceChangeCacheRunnable implements Runnable {

    @Resource
    RedisTemplate redisTemplate;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    FaceChangeService faceChangeService;
    @Resource
    FaceChangeRecService faceChangeRecService;

    //一下为载入内存
    public void loadCache() {
        List<FaceChangeDO> faceChangeList = faceChangeService.listAll();
        faceChangeList.forEach(q -> syncCache(q));
    }

    public void syncCacheRec(List<FaceChangeRecDO> faceChangeRecList) {

        for (FaceChangeRecDO faceChangeRecTemp : faceChangeRecList) {
            String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(faceChangeRecTemp.getId());
            FaceChangeRecRedisDO faceChangeRecRedis = dozerBeanMapper.map(faceChangeRecTemp,
                    FaceChangeRecRedisDO.class);
            redisTemplate.opsForZSet().add(redisKey, faceChangeRecRedis, faceChangeRecTemp.getCurrentIndex());
        }
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
        syncCacheRec(faceChangeRecList);
//        for (FaceChangeRecDO faceChangeRecTemp : faceChangeRecList) {
//            FaceChangeRecRedisDO faceChangeRecRedis = dozerBeanMapper.map(faceChangeRecTemp,
//                    FaceChangeRecRedisDO.class);
//            redisTemplate.opsForZSet().add(redisKey, faceChangeRecRedis, faceChangeRecTemp.getCurrentIndex());
//        }
    }

    @Override
    public void run() {
        loadCache();
    }
}
