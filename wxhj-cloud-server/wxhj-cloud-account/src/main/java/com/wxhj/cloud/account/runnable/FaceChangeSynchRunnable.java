package com.wxhj.cloud.account.runnable;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.domain.MapListenListDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.MapListenListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FaceChangeSynchRunnable implements Runnable {

    private final static Integer ASYNC_COUNT = 50;

    @Resource
    MapListenListService mapListenListService;
    @Resource
    FaceChangeRecService faceChangeRecService;
    @Resource
    AccountInfoService accountInfoService;


    public void faceSynch() {
        PageInfo<MapListenListDO> mapListenListPageInfo = mapListenListService.selectByNoSync(ASYNC_COUNT);
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
                    .phoneNumber(faceAcountInfo.getPhoneNumber())
                    .cardNumber(faceAcountInfo.getCardNumber())
                    .build();

            return faceChangeRec;
        }).filter(q -> q != null).collect(Collectors.toList());


        if (faceChangeRecList.size() > 0) {
            faceChangeRecService.insertListCascade(faceChangeRecList);
            List<Long> idList = faceChangeRecList.stream().map(q -> q.getMasterId()).collect(Collectors.toList());
            confirmAsyncMapListenList(idList);
        }
        if (!mapListenListPageInfo.isIsLastPage()) {
            this.faceSynch();
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
        faceSynch();
        //loadCache();
        log.info("人脸同步完成");
    }
}
