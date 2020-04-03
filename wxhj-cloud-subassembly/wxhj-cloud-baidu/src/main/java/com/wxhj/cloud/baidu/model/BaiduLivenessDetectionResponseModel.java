/** 
 * @fileName: BaiduLivenessDetectionResponseModel.java  
 * @author: pjf
 * @date: 2020年3月7日 下午4:45:00 
 */

package com.wxhj.cloud.baidu.model;

import java.util.List;

import lombok.Data;

/**
 * @className BaiduLivenessDetectionResponseModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:45:00   
*/
/** 
 * @className BaiduLivenessDetectionResponseModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:45:00 
*/
@Data
public class BaiduLivenessDetectionResponseModel {
	private BaiduFaceThresholdsModel thresholds;
	private Double face_liveness;

	private List<BaiduFaceInfoModel> face_list;
}
