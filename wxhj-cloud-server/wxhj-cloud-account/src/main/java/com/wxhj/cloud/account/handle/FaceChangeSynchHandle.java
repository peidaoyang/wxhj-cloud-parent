package com.wxhj.cloud.account.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Strings;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.component.job.AbstractAsynJobHandle;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.request.AsyncMapListenListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ConfirmAsyncMapListenListRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.MapListenListVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FaceChangeSynchHandle extends AbstractAsynJobHandle {

    @Resource
    MapperClient mapperClient;
    @Resource
    FaceChangeRecService faceChangeRecService;
    @Resource
    AccountInfoService accountInfoService;

    @Override
    @Transactional
    public boolean execute() {
        WebApiReturnResultModel asyncMapListenList = mapperClient.asyncMapListenList(new AsyncMapListenListRequestDTO(50));
        if (asyncMapListenList.resultSuccess()) {
            String jsonString = JSON.toJSONString(asyncMapListenList.getData());
            PageDefResponseModel pageDefResponseModel = JSON.parseObject(jsonString, PageDefResponseModel.class);
            List<MapListenListVO> mapListenListList = JSONArray
                    .parseArray(JSON.toJSONString(pageDefResponseModel.getRows()), MapListenListVO.class);
            mapListenListList = mapListenListList.stream().filter(q -> !faceChangeRecService.existByMasterId(q.getId()))
                    .collect(Collectors.toList());
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
                if (Strings.isNullOrEmpty(faceAcountInfo.getName())) {
                    faceAcountInfo.setName("无");
                    faceAcountInfo.setPhoneNumber("0");
                    faceAcountInfo.setIdNumber("0");
                }

                faceChangeRec = new FaceChangeRecDO(q.getSceneId(), null, q.getId(), q.getAccountId(), url,
                        q.getOperateType(), faceAcountInfo.getIdNumber(), faceAcountInfo.getName(),
                        faceAcountInfo.getPhoneNumber());
                return faceChangeRec;
            }).collect(Collectors.toList());
            //测试暂时注释
//            faceChangeRecList = faceChangeRecList.stream()
//                    .filter(q -> q != null && (q.getOperateType() == 1 || q.getImageUrl() != null))
//                    .collect(Collectors.toList());
            if (faceChangeRecList.size() > 0) {
                faceChangeRecService.insertListCascade(faceChangeRecList);
                List<Long> idList = faceChangeRecList.stream().map(q -> q.getMasterId()).collect(Collectors.toList());
                // List<Long> idList = mapListenListList.stream().map(q ->
                // q.getId()).collect(Collectors.toList());
                ConfirmAsyncMapListenListRequestDTO confirmAsyncMapListenListRequest = new ConfirmAsyncMapListenListRequestDTO(
                        idList);
                mapperClient.confirmAsyncMapListenList(confirmAsyncMapListenListRequest);
            }
            if (!pageDefResponseModel.getRecords().equals(0)) {
               this.execute();
            }
        }
        return true;
    }

    @Override
    public void destroy() {

    }

}
