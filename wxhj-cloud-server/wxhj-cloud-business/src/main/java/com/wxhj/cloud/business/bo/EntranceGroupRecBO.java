/** 
 * @fileName: EntranceGroupRecBO.java  
 * @author: pjf
 * @date: 2020年2月24日 下午1:10:02 
 */

package com.wxhj.cloud.business.bo;

import lombok.Data;

/**
 * @className EntranceGroupRecBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:10:02   
*/
/**
 * @className EntranceGroupRecBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:10:02
 */
@Data
public class EntranceGroupRecBO {
	// 通行组编号
	private String entranceGroupId;
	// 顺序
	private Integer serialNumber;
	// 通行时间段编号
	private String entranceDayId;
}
