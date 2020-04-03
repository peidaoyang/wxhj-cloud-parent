/** 
 * @fileName: BaiduFaceThresholdsModel.java  
 * @author: pjf
 * @date: 2020年3月7日 下午4:46:12 
 */

package com.wxhj.cloud.baidu.model;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @className BaiduFaceThresholdsModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:46:12   
*/
/**
 * @className BaiduFaceThresholdsModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:46:12
 */
@Data
public class BaiduFaceThresholdsModel {
	@JSONField(name = "frr_1e-3")
	private Double frr_1e3;
	@JSONField(name = "frr_1e-2")
	private Double frr_1e2;
	@JSONField(name = "frr_1e-4")
	private Double frr_1e4;
}
