/** 
 * @fileName: BaiduResponseModel.java  
 * @author: pjf
 * @date: 2020年3月7日 下午4:35:51 
 */

package com.wxhj.cloud.baidu.model;

import lombok.Data;

/**
 * @className BaiduResponseModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:35:51   
*/
/**
 * @className BaiduResponseModel.java
 * @author pjf
 * @date 2020年3月7日 下午4:35:51
 */
@Data
public class BaiduResponseModel {
	private Object result;

	private Long log_id;
	private String error_msg;
	private Integer cached;
	private Integer error_code;
	private Long timestamp;
}
