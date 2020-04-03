/**
 * @className EntranceDayRecDO.java
 * @admin jwl
 * @date 2020年1月10日 上午9:38:15
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @className EntranceDayRecDO.java
 * @admin jwl
 * @date 2020年1月10日 上午9:38:15
 */
@Data
@Table(name = "entrance_day_rec")
public class EntranceDayRecDO {
	@Id
	private String entranceId;//时间段编号
	@Id
	private Integer sequence;//顺序
	private Integer beginTime;//开始时间
	private Integer endTime;//结束时间
}
