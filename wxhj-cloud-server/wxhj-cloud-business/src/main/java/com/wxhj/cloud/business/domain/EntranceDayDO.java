/**
 * @className EntranceDayDO.java
 * @admin jwl
 * @date 2020年1月10日 上午9:35:36
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @className EntranceDayDO.java
 * @admin jwl
 * @date 2020年1月10日 上午9:35:36
 */
@Data
@Table(name = "entrance_day")
public class EntranceDayDO {
	@Id
	private String id;//时间段编号
	private String fullName;//时间段名称
	private String organizeId;//组织编号
	private String timeDescribe;//时间描述
	private Integer matchType;//是否匹配
}
