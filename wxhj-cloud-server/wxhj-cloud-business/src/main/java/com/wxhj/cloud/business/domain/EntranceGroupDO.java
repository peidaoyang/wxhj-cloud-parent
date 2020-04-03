/**
 * @className EntranceGroupDO.java
 * @author jwl
 * @date 2020年1月10日 下午3:28:00
 */
package com.wxhj.cloud.business.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @className EntranceGroupDO.java
 * @author jwl
 * @date 2020年1月10日 下午3:28:00
 */
@Data
@Table(name = "entrance_group")
public class EntranceGroupDO {
	@Id
	private String id;// 通行组编号
	private String fullName;// 通行组名称
	private Integer groupType;// 通行组类型 0 为周 1 为月
	private String organizeId;// 组织编号

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date applyDate;
	private String parameterId;
}
