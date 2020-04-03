/** 
 * @fileName: EntranceGroupBO.java  
 * @author: pjf
 * @date: 2020年2月24日 下午1:08:54 
 */

package com.wxhj.cloud.business.entrance;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @className EntranceGroupBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:08:54   
*/
/**
 * @className EntranceGroupBO.java
 * @author pjf
 * @date 2020年2月24日 下午1:08:54
 */
@Data
public class EntranceGroupBO {
	// 通行组编号
	private String id;
	// 通行组名称
	private String fullName;
	// 通行组类型 0 为周 1 为月
	private Integer groupType;
	// 组织编号
	private String organizeId;
	private List<EntranceGroupRecBO> entranceGroupRecList;
	//
	
	//通行单日规则对象
	private List<EntranceDayBO> entranceDayList;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	//应用时间
	private Date applyDate;
}
