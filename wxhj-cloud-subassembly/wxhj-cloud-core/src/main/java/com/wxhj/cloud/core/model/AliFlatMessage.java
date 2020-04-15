/** 
 * @fileName: AliFlatMessage.java  
 * @author: pjf
 * @date: 2019年12月3日 上午8:41:30 
 */

package com.wxhj.cloud.core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.wxhj.cloud.core.statics.CaseFormatStaticClass;
import lombok.Data;
import lombok.ToString;

/**
 * @className AliFlatMessage.java
 * @author pjf
 * @date 2019年12月3日 上午8:41:30   
*/
/**
 * @className AliFlatMessage.java
 * @author pjf
 * @date 2019年12月3日 上午8:41:30
 */
@Data
@ToString
@SuppressWarnings("unchecked")
public class AliFlatMessage {
	private long id;
	/*
	 * 数据库名称
	 */
	private String database;
	/*
	 * 表名
	 */
	private String table;
	/*
	 * 主键列表
	 */
	private List<String> pkNames;
	private Boolean isDdl;
	private String type;
	// binlog执行时间
	private Long es;
	// dml build 时间戳
	private Long ts;
	/*
	 * sql语句
	 */
	private String sql;
	/*
	 * 字段对应sql类型
	 */
	private Map<String, Integer> sqlType;
	/*
	 * 字段对应mysql类型
	 */
	private Map<String, String> mysqlType;
	/*
	 * 当前数据键值对
	 */
	private List<Map<String, String>> data;
	/*
	 * 旧数据键值对
	 */
	private List<Map<String, String>> old;

	public void filterLine() {
		if (pkNames != null) {
			pkNames = pkNames.stream().map(q ->
					//HumpUtil.lineToHump(q)
					CaseFormatStaticClass.UNDERSCORE_TO_CAMEL.convert(q)
			).collect(Collectors.toList());
		}
		if (sqlType != null) {
			sqlType = formateMapLine(sqlType);
		}
		if (mysqlType != null) {
			mysqlType = formateMapLine(mysqlType);
		}
		if (data != null) {
			data = data.stream().map(q -> formateMapLine(q)).collect(Collectors.toList());
		}
		if (old != null) {
			old = old.stream().map(q -> formateMapLine(q)).collect(Collectors.toList());
		}
	}

	private <T> Map<String, T> formateMapLine(Map<String, T> map) {
		Map<String, T> returnMap = new HashMap();
		for (Entry entryTemp : map.entrySet()) {
			returnMap.put(
					CaseFormatStaticClass.UNDERSCORE_TO_CAMEL.convert(entryTemp.getKey().toString())
					//HumpUtil.lineToHump(entryTemp.getKey().toString())

					, (T) entryTemp.getValue());
		}
		return returnMap;
	}
}
