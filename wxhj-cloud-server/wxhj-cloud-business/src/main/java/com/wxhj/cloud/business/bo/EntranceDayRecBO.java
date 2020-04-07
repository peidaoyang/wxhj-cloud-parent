/** 
 * @fileName: EntranceDayRecBO.java  
 * @author: pjf
 * @date: 2020年2月24日 下午1:12:24 
 */

package com.wxhj.cloud.business.bo;

import lombok.Data;

/**
 * @className EntranceDayRecBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:12:24   
*/
/**
 * @className EntranceDayRecBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:12:24
 */
@Data
public class EntranceDayRecBO {
	// 时间段编号
	private String entranceId;
	// 顺序
	private Integer sequence;
	// 开始时间(对应当日的分钟)
	private Integer beginTime;
	// 结束时间(对应当日的分钟)
	private Integer endTime;
}
