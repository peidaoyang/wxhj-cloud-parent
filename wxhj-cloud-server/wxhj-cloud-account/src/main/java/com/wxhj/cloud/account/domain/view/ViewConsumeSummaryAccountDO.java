/** 
 * @fileName: viewConsumeSummaryAccount.java  
 * @author: pjf
 * @date: 2020年2月5日 下午1:45:30 
 */

package com.wxhj.cloud.account.domain.view;



import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import java.time.LocalDate;

/**
 * @className viewConsumeSummaryAccount.java
 * @author pjf
 * @date 2020年2月5日 下午1:45:30
 */
@Data
@Table(name = "view_consume_summary_account")
public class ViewConsumeSummaryAccountDO {
	@Id
	//用户id
	private String accountId;
	@Id
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ColumnType(jdbcType = JdbcType.DATE)
	//消费日期
	private LocalDate consumeDate;
	//消费金融
	private Integer consumeMoney;
	//组织id
	private String organizeId;
	//姓名
	private String name;
}
