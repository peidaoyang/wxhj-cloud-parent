/** 
 * @fileName: EntranceDayBO.java  
 * @author: pjf
 * @date: 2020年2月24日 下午1:11:38 
 */

package com.wxhj.cloud.business.bo;

import java.util.List;

import lombok.Data;

/**
 * @className EntranceDayBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:11:38   
*/
/**
 * @className EntranceDayBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:11:38
 */
@Data
public class EntranceDayBO {
	// 时间段编号
	private String id;
	// 时间段名称
	private String fullName;
	// 组织编号
	private String organizeId;
	// 时间描述
	private String timeDescribe;
	// 是否匹配
	private Integer matchType;

	private List<EntranceDayRecBO> entranceDayRecList;
}
