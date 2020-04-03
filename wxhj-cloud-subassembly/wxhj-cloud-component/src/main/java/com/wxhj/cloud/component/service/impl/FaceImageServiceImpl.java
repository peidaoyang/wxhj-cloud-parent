/** 
 * @fileName: FaceImageServiceImpl.java  
 * @author: pjf
 * @date: 2020年3月7日 下午3:56:38 
 */

package com.wxhj.cloud.component.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.FaceVerifyRequest;
import com.wxhj.cloud.baidu.model.BaiduLivenessDetectionResponseModel;
import com.wxhj.cloud.baidu.model.BaiduResponseModel;
import com.wxhj.cloud.component.service.FaceImageService;
import com.wxhj.cloud.core.utils.Base64Util;

/**
 * @className FaceImageServiceImpl.java
 * @author pjf
 * @date 2020年3月7日 下午3:56:38   
*/
/**
 * @className FaceImageServiceImpl.java
 * @author pjf
 * @date 2020年3月7日 下午3:56:38
 */
@Service
public class FaceImageServiceImpl implements FaceImageService {
	@Resource
	AipFace aipFace;

	private static double minFaceLiveness = 0.5;

	@Override
	public boolean faceMonitor(byte[] faceImage) {
		FaceVerifyRequest req = new FaceVerifyRequest(Base64Util.encode(faceImage), "BASE64");
		ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
		list.add(req);
		JSONObject faceverify = aipFace.faceverify(list);
		BaiduResponseModel baiduResponseModel = JSON.parseObject(faceverify.toString(), BaiduResponseModel.class);
		if (baiduResponseModel.getError_code() != 0) {
			return false;
		}

		BaiduLivenessDetectionResponseModel baiduLivenessDetectionResponseModel = JSON.toJavaObject(
				(com.alibaba.fastjson.JSONObject) baiduResponseModel.getResult(),
				BaiduLivenessDetectionResponseModel.class);
		return baiduLivenessDetectionResponseModel.getFace_liveness() > minFaceLiveness;
	}
}
