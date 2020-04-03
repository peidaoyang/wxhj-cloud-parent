/** 
 * @fileName: BaiduFaceInfoModel.java  
 * @author: pjf
 * @date: 2020年3月7日 下午4:43:57 
 */

package com.wxhj.cloud.baidu.model;

import lombok.Data;

/**
 * @className BaiduFaceInfoModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:43:57   
*/
/**
 * @className BaiduFaceInfoModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:43:57
 */
@Data
public class BaiduFaceInfoModel {
	private BaiduFaceLivenessModel liveness;
	private BaiduFaceAngleModel angle;

	private String face_token;

	private BaiduFaceImageLocationModel location;

	private Double face_probability;
}
