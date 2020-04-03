/** 
 * @fileName: CurrencyServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月3日 上午9:05:42 
 */

package com.wxhj.cloud.canal.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.canal.service.CurrencyService;
import com.wxhj.cloud.core.utils.HumpUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @className CurrencyServiceImpl.java
 * @author pjf
 * @date 2019年12月3日 上午9:05:42   
*/
/**
 * @className CurrencyServiceImpl.java
 * @author pjf
 * @date 2019年12月3日 上午9:05:42
 */
@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

	@Resource
	DataSource dataSource;

	private Statement getStatement() {
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			return statement;
		} catch (SQLException e) {
			log.error(" CurrencyService 无法获取Statement");
		}
		return null;
	}

	@Override
	public List<String> listTableKey(String dataSourceName, String tableName) {
		List<String> keyList = new ArrayList<String>();
		String sql = "SELECT TABLE_NAME,CONSTRAINT_SCHEMA,CONSTRAINT_NAME,COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE where CONSTRAINT_NAME='PRIMARY' and CONSTRAINT_SCHEMA=";
		sql = sql.concat("'").concat(dataSourceName).concat("'").concat(" and TABLE_NAME=");
		sql = sql.concat("'").concat(tableName).concat("'");
		try {
			ResultSet resultSet = getStatement().executeQuery(sql);
			while (resultSet.next()) {
				keyList.add(HumpUtil.lineToHump(resultSet.getObject("COLUMN_NAME").toString()));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return keyList;
	}

	@Override
	public List<Map<String, String>> listTableContent(String dataSourceName, String tableName) {
		List<Map<String, String>> tableValueList = new ArrayList<Map<String, String>>();
		String sql = "select * from ";
		sql = sql.concat(dataSourceName).concat(".").concat(tableName);
		try {
			ResultSet resultSet = getStatement().executeQuery(sql);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			while (resultSet.next()) {
				Map<String, String> rowMap = new HashMap<String, String>();
				for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
					Object objTemp = resultSet.getObject(i);
					if (objTemp != null) {
						String columnName = HumpUtil.lineToHump(resultSetMetaData.getColumnName(i));
						String columnValue = objTemp.toString();
						rowMap.put(columnName, columnValue);
					}
				}
				tableValueList.add(rowMap);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return tableValueList;
	}

}
