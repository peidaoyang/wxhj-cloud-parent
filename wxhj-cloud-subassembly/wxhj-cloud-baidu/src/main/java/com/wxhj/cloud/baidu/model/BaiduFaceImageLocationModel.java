/** 
 * @fileName: BaiduFaceImageLocationModel.java  
 * @author: pjf
 * @date: 2020年3月7日 下午4:47:40 
 */

package com.wxhj.cloud.baidu.model;

import lombok.Data;

/**
 * @className BaiduFaceImageLocationModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:47:40   
*/
/** 
 * @className BaiduFaceImageLocationModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:47:40 
*/
@Data
public class BaiduFaceImageLocationModel {
	private Double left;
	private Double top;
	private Double width;
	
	private Double height;
	private Long rotation;
}
