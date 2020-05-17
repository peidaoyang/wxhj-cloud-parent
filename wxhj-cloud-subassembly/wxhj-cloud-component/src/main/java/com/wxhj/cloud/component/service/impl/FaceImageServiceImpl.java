/**
 * @fileName: FaceImageServiceImpl.java
 * @author: pjf
 * @date: 2020年3月7日 下午3:56:38
 */

package com.wxhj.cloud.component.service.impl;

import com.wxhj.cloud.core.utils.JSON;
import com.baidu.aip.face.AipFace;
import com.google.common.collect.Maps;
import com.google.common.io.BaseEncoding;
import com.wxhj.cloud.baidu.model.BaiduDetectFaceInfoModel;
import com.wxhj.cloud.baidu.model.BaiduDetectModel;
import com.wxhj.cloud.baidu.model.BaiduResponseModel;
import com.wxhj.cloud.component.service.FaceImageService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author pjf
 * @className FaceImageServiceImpl.java
 * @date 2020年3月7日 下午3:56:38
 */


@Service
public class FaceImageServiceImpl implements FaceImageService {
    @Resource
    AipFace aipFace;

    private static final double MIN_FACE_LIVENESS = 0.3;

    @Override
    public boolean faceMonitor(byte[] faceImage) {
        //BASE64
        //
        HashMap<String, String> options = Maps.newHashMap();
        options.put("max_face_num", "5");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "NONE");
        options.put("face_field", "quality");
        JSONObject faceverify = aipFace.detect(BaseEncoding.base64().encode(faceImage), "BASE64", options);
       System.out.println(faceverify.toString());

        BaiduResponseModel baiduResponseModel = JSON.parseObject(faceverify.toString(), BaiduResponseModel.class);
        if (baiduResponseModel.getError_code() != 0) {
            return false;
        }
        BaiduDetectModel baiduDetectModel =
                JSON.toJavaObject( baiduResponseModel.getResult(), BaiduDetectModel.class);
        if (!baiduDetectModel.getFace_num().equals(1)) {
            return false;
        }
        BaiduDetectFaceInfoModel baiduDetectFaceInfoModel = baiduDetectModel.getFace_list().get(0);
        boolean faceLegal = true;
        faceLegal = faceLegal && baiduDetectFaceInfoModel.getQuality().getBlur().equals(0.0);
        faceLegal = faceLegal && baiduDetectFaceInfoModel.getQuality().getIllumination() > 40;
        faceLegal = faceLegal && baiduDetectFaceInfoModel.getQuality().getCompleteness().equals(1.0);
        faceLegal = faceLegal && Math.abs(baiduDetectFaceInfoModel.getAngle().getRoll()) < 20.0;
        faceLegal = faceLegal && Math.abs(baiduDetectFaceInfoModel.getAngle().getPitch()) < 20.0;
        faceLegal = faceLegal && Math.abs(baiduDetectFaceInfoModel.getAngle().getYaw()) < 20.0;

        return faceLegal;

        // return true;
//        FaceVerifyRequest req = new FaceVerifyRequest(BaseEncoding.base64().encode(faceImage), "BASE64");
//        ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
//        list.add(req);
//        JSONObject faceverify = aipFace.faceverify(list);
//        BaiduResponseModel baiduResponseModel = JSON.parseObject(faceverify.toString(), BaiduResponseModel.class);
//        if (baiduResponseModel.getError_code() != 0) {
//            return false;
//        }
//
//        BaiduLivenessDetectionResponseModel baiduLivenessDetectionResponseModel = JSON.toJavaObject(
//                (com.alibaba.fastjson.JSONObject) baiduResponseModel.getResult(),
//                BaiduLivenessDetectionResponseModel.class);
//        return baiduLivenessDetectionResponseModel.getFace_liveness() >= MIN_FACE_LIVENESS;
    }
}
