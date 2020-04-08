package com.wxhj.cloud.account.handle;

import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.component.job.AbstractAsynJobHandle;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.redis.domain.FaceChangeRecRedisDO;
import org.dozer.DozerBeanMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class CacheSyncFaceChangeHandle extends AbstractAsynJobHandle {

    @Resource
    RedisTemplate redisTemplate;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    FaceChangeService faceChangeService;
    @Resource
    FaceChangeRecService faceChangeRecService;

    @Override
    public boolean execute() {
        List<FaceChangeDO> faceChangeList = faceChangeService.listAll();
        faceChangeList.forEach(q -> syncCache(q));
        return true;
    }

    @Override
    public void destroy() {
    }

    @SuppressWarnings("unchecked")
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
