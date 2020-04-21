package com.wxhj.cloud.account.handle;

import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.domain.MapListenListDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.MapListenListService;
import com.wxhj.cloud.component.job.AbstractAsynJobHandle;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FaceChangeSynchHandle extends AbstractAsynJobHandle {

    @Resource
    MapListenListService mapListenListService;
    @Resource
    FaceChangeRecService faceChangeRecService;
    @Resource
    AccountInfoService accountInfoService;

    @Override
    @Transactional
    public boolean execute() {
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) asyncMapListenList(50);
        List<MapListenListDO> mapListenListList = (List<MapListenListDO>) pageDefResponseModel.getRows();
        Long maxId = mapListenListList.stream().mapToLong(q -> q.getId()).max().getAsLong();
        Long minId = mapListenListList.stream().mapToLong(q -> q.getId()).min().getAsLong();
        List<Long> faceChangeRecs =
                faceChangeRecService.listMaxIdAndMinId(maxId, minId).stream()
                        .map(q -> q.getMasterId()).collect(Collectors.toList());


        mapListenListList = mapListenListList.stream().filter(
                q -> !faceChangeRecs.contains(q.getId())
        ).collect(Collectors.toList());
        if (mapListenListList.size() <= 0) {
            return true;
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

//            faceChangeRec = new FaceChangeRecDO(q.getSceneId(), null, q.getId(), q.getAccountId(), url,
//                    q.getOperateType(), faceAcountInfo.getIdNumber(), faceAcountInfo.getName(),
//                    faceAcountInfo.getPhoneNumber());
            //.currentIndex(null)
            faceChangeRec = FaceChangeRecDO.builder().id(q.getSceneId())
                    .masterId(q.getId()).accountId(q.getAccountId())
                    .imageUrl(url).operateType(q.getOperateType())
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
        if (!pageDefResponseModel.getRecords().equals(0)) {
            this.execute();
        }
        return true;
    }


    private IPageResponseModel asyncMapListenList(Integer asyncCount) {
        return mapListenListService.selectByNoSync(asyncCount);
    }


    private int confirmAsyncMapListenList(
            List<Long> idList) {
        int updateByIdSetSync = mapListenListService.updateByIdSetSync(idList);
        return updateByIdSetSync;
    }

    @Override
    public void destroy() {
    }
}
