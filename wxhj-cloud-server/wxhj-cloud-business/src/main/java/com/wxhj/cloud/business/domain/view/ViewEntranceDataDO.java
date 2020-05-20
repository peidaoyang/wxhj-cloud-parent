/**
 * @className EntranceDataDO.java
 * @author jwl
 * @date 2020年1月17日 下午1:01:01
 */
package com.wxhj.cloud.business.domain.view;



import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDate accessDate;
	private Integer accessTime;
	private String entranceId;
	private Integer entranceSequence;
	private LocalDateTime recordDatetime;
	private String accountId;
	private String sceneId;
	private String deviceId;
	private String organizeId;
	private Long serialNumber;
	private Long recordTimeStamp;
	private String accountName;
	private Double temperature;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ColumnType(jdbcType = JdbcType.DATE)
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ColumnType(jdbcType = JdbcType.DATE)
	private LocalDate endDate;
	private String deviceName;
	private String entranceName;
}
