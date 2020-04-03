/**
 * @className EntranceDataDO.java
 * @author jwl
 * @date 2020年1月17日 下午1:01:01
 */
package com.wxhj.cloud.business.domain.view;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @className EntranceDataDO.java
 * @author jwl
 * @date 2020年1月17日 下午1:01:01
 */
@Data
@Table(name = "view_entrance_data")
public class ViewEntranceDataDO {
	@Id
	private String orderNumber;
	@ColumnType(jdbcType = JdbcType.DATE)
	private Date accessDate;
	private Integer accessTime;
	private String entranceId;
	private Integer entranceSequence;
	private Date recordDatetime;
	private String accountId;
	private String sceneId;
	private String deviceId;
	private String organizeId;
	private Long serialNumber;
	private Long recordTimeStamp;
	private String accountName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ColumnType(jdbcType = JdbcType.DATE)
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ColumnType(jdbcType = JdbcType.DATE)
	private Date endDate;
	private String deviceName;
	private String entranceName;
}
