/**
 * @className EntranceGroupRecDO.java
 * @author jwl
 * @date 2020年1月10日 下午3:29:43
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @className EntranceGroupRecDO.java
 * @author jwl
 * @date 2020年1月10日 下午3:29:43
 */
@Data
@Table(name = "entrance_group_rec")
public class EntranceGroupRecDO {
	@Id
	private String entranceGroupId;//通行组编号
	@Id
	private Integer serialNumber;//顺序
	private String entranceDayId;//通行时间段编号
}
